package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.BasicConnectionPool;
import repository.UserRepository;
import service.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.LogManager;

public class CreateUserServlet extends HttpServlet {
    //private Map<Integer, User> users;
    protected UserRepository con;
    Logger log = LoggerFactory.getLogger(CreateUserServlet.class);
    Connection cone;
    @Override
    public void init(){
        //log.("");
        Properties pr = new Properties();
        FileInputStream file = null;
        try{
          file = new FileInputStream("src/main/resources/db.properties");
          pr.load(file);
          String url = pr.getProperty("url");
          String user = pr.getProperty("user");
          String password = pr.getProperty("password");
          cone = BasicConnectionPool.create(url, user, password).getConnection();
        }
        catch(IOException e){
            log.error("File "+ file +" not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //con = new UserRepository();

       /* final Object users = getServletContext().getAttribute("users");
        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("You're repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }*/
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        try{
            if(!con.checkExistEmail(email)){
                con.createUser(cone, user);
                final String json = new ObjectMapper().writeValueAsString(user);
                response.getWriter().write(json);
            } else
            {
                //response.setStatus(404);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //response.
        //DBActions.createUser(con, user.createUser());
    }
}
