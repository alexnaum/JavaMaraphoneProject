package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import repository.UserRepository;
import service.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchByNameAndEmail extends HttpServlet {

    private UserRepository con;
    Connection connect;
    @Override
    public void init() {
        con = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        response.setContentType("text/html");
        List<User> user = new ArrayList<>();
        try {
            user = con.searchUser(connect, name, email);
            final String json = new ObjectMapper().writeValueAsString(user);
            response.getWriter().println(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
