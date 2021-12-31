package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import repository.DBConnection;
import service.User;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
    public class ContextListener implements ServletContextListener {

        private Map<Integer, User> users;
        protected DBConnection con;

        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {

            final ServletContext servletContext =
                    servletContextEvent.getServletContext();

            users = new ConcurrentHashMap<>();

            servletContext.setAttribute("users", users);
        }
        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            try {
                con.getMySQLConnection().close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
}
