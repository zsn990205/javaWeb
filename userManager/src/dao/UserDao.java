package dao;

import entity.User;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    //登陆
    public User login(User loginUser) {
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement("select*from user where username=? and password=?");
            ps.setString(1, loginUser.getUsername());
            ps.setString(2, loginUser.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection, ps, rs);
        }
        return user;
    }

    //注册
    public void register(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "insert into user values(null,?,?)";
        connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
    }

    public static void main(String[] args) {
        User user = new User();
        //user.setUsername("bit");
        //user.setPassword("123");

        //User loginUser = login(user);
        //System.out.println(loginUser);
    }
}
