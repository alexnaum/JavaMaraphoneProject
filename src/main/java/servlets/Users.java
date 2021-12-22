package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.DBConnection;
import service.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

//@WebServlet("/users")
public class Users extends HttpServlet {
    private User user;
    @Override
    public void init(){
        user = new User(1, "Ivan", "ivan@test.ua");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        final String json = new ObjectMapper().writeValueAsString(user);
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello");
    }

    @Override
    protected  void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Hello");
    }

    private boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String email = req.getParameter("email");

        return name != null && name.length() > 0 &&
                email != null && email.length() > 0 &&
                email.matches("[+]?\\d+");
    }
}
