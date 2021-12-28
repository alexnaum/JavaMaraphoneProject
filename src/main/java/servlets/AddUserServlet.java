package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.DBConnection;
import repository.UserDBAction;
import service.User;
import utils.Utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AddUserServlet extends HttpServlet {
    private Map<Integer, User> users;
    protected UserDBAction con;
    @Override
    public void init(){
        con = new UserDBAction();
        final Object users = getServletContext().getAttribute("users");
        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("You're repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
        //id = new AtomicInteger(2);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        /*String errorMsg = null;
        if (email == null || email.equals("")) {
            errorMsg = "User Email can't be null or empty";
        }
        if (name == null || name.equals("")) {
            errorMsg = "Name can't be null or empty";
        }*/
        User user = new User();
        user.setName(name);
        user.setEmail(email);


        try{
            if(!con.checkExistEmail(email)){
                con.createUser(user);
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
