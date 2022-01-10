package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.UserRepository;
import service.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllUsers extends HttpServlet {
    private UserRepository con;
    Connection connect;
    @Override
    public void init() {
        con = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        List<User> userList = null;
     try {
            userList = con.getAllUsers(connect);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(User users: userList){
            final String json = new ObjectMapper().writeValueAsString(users);
            response.getWriter().println(json);
        }
    }
    @Override
    public void destroy(){
        con = null;
    }
}
