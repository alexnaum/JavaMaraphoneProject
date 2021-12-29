package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.UserDBAction;
import service.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetAllUsers extends HttpServlet {
    private UserDBAction con;
    @Override
    public void init() {
        con = new UserDBAction();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        List<User> userList = null;
     try {
            userList = con.getAllUsers();
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
