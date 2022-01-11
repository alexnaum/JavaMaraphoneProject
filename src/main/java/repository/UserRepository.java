package repository;

import service.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UserRepository {
    public void createUser(Connection con, User user) throws SQLException {
        String sql = "INSERT INTO users(name,email) VALUES (?,?)";
        //Connection con = DBConnection.getMySQLConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.executeUpdate();
    }

    public List<User> getAllUsers(Connection con) throws ClassNotFoundException, SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users";
        //Connection con = DBConnection.getMySQLConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            userList.add(new User(id,name,email));
        }
        return userList;
    }
    public User searchById(Connection con, int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from users where id = ?";
        User user = new User();
        //Connection con = DBConnection.getMySQLConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
          int idw = rs.getInt("id");
          String name = rs.getString("name");
          String email = rs.getString("email");
          user = new User(idw,name,email);
        }
        return user;
    }

    public List<User> searchUser(Connection con, String name, String email) throws SQLException, ClassNotFoundException {
        String sql = "select * from users u where u.name = ? and u.email=?";
        //Connection con = DBConnection.getMySQLConnection();
        List<User> user = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String foundName = rs.getString("name");
            String foundEmail = rs.getString("email");
            user.add(new User(id, foundName, foundEmail));
        }
        return user;
    }

    public boolean checkExistEmail(String email) throws SQLException, ClassNotFoundException {
        boolean col = false;
        String sql = "select email from users u where u.email = ?";
        Connection con = DBConnection.getMySQLConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            if(rs.getRow()>1) col = true;
            else col = false;
        }
        return col;
    }
}
