package utils;

import jakarta.servlet.http.HttpServletRequest;

public class Utils {
    private static boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String email = req.getParameter("email");

        return name != null && name.length() > 0 &&
                email != null && email.length() > 0 &&
                email.matches("[+]?\\d+");
    }
    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null &&
                (id.length() > 0) &&
                id.matches("[+]?\\d+");
    }
}
