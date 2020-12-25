package model;

import util.OderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//实现三个功能
//1.插入用户       注册的时候使用
//2.按名字查找用户  登陆的时候使用
//3.按照用户Id查找  展示信息时使用
public class UserDao {
    public void add(User user) throws OderSystemException {
       //[接下来是JDBC编程]
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
       //1.先获取数据库连接
        connection = DBUtil.getConnection();
       //2.拼装sql语句
        String sql = "insert into user values(null,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
            statement.setInt(3,user.getIsAdmin());
        //3.执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
               throw new OderSystemException("插入用户失败");
            }
            System.out.println("插入用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("插入用户失败");
        } finally {
            //4.关闭连接
            DBUtil.close(connection,statement,null);
        }

    }

    public User selectByName(String name) throws OderSystemException {
        //1.连接数据库
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.构造sql语句
        String sql = "select * from user where name = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
        //3.执行sql语句
            resultSet = statement.executeQuery();
        //4.遍历结果集
            if (resultSet.next()) {
        //按照姓名查找用户的信息
        //在数据库中得到用户的id name password等
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("按照姓名查找用户失败");
        } finally {
            //5.关闭连接
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public User selectById(int userId) throws OderSystemException {
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "select * from user where userId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
        //3.执行sql语句
            resultSet = statement.executeQuery();
        //4.遍历结果集
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("使用id查找用户失败");
        } finally {
        //5.关闭资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) throws OderSystemException {
        //进行UserDao的单元测试
          UserDao userDao = new UserDao();

//        User user = new User();
//        user.setName("zsn");
//        user.setPassword("123");
//        user.setIsAdmin(0);
//        userDao.add(user);

        //按照名字查找
        User user = userDao.selectByName("zsn");
        System.out.println("按照姓名查找");
        System.out.println(user);
        //按照Id查找
        User user2 = userDao.selectById(1);
        System.out.println("按照id查找");
        System.out.println(user2);
    }
}
