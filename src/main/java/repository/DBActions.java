package repository;

import service.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBActions {

    public static User searchUser(Connection con, String name, String email) throws SQLException {
     String sql = "select u.name, u.email from users u where u.name = ? and u.email=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            return user;
        }
        return null;
    }

    public static User searchId(Connection con, int id) throws SQLException{
        String sql = "select u.name, u.email from users u where u.id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(id);
            return user;
        }
        return null;
    }

    public static void createUser(Connection con, User user) throws SQLException{
      String sql = "insert into user(id,name,email) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, user.getId());
        ps.setString(2,user.getName());
        ps.setString(3, user.getEmail());
        ps.executeUpdate();
    }
}
