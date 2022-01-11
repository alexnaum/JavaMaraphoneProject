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

public class SearchUserServlet extends HttpServlet {
    private UserRepository con;
    Connection connect;
    @Override
    public void init() {
        con = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html");
        User user = new User();
        try {
            user = con.searchById(connect, Integer.parseInt(id));
            final String json = new ObjectMapper().writeValueAsString(user);
            response.getWriter().println(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
