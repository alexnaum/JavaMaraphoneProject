package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.UserDBAction;
import service.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "SearchByNameAndEmail", value = "/SearchByNameAndEmail")
public class SearchByNameAndEmail extends HttpServlet {

    private UserDBAction con;
    @Override
    public void init() {
        con = new UserDBAction();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        response.setContentType("text/html");
        List<User> user = new ArrayList<>();
        try {
            user = con.searchUser(name, email);
            final String json = new ObjectMapper().writeValueAsString(user);
            response.getWriter().println(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
