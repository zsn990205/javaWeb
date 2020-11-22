package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    //1.用户的注册(增)
    //把一个user对象插入到数据库中
    public void add(User user) {
        // 1).获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2).拼装sql语句
        String sql = "insert into user values (null, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
        // 3).执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("插入新用户失败");
                return;
            }
            System.out.println("插入新用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        // 4).释放连接
           DBUtil.close(connection,statement,null);
        }
    }


    //2.用户登陆(查)
    public User selectByName(String name) {
      // 1)获取数据库连接
        Connection connection = DBUtil.getConnection();
      // 2)拼装sql语句
        String sql = "select * from user where name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
       // 3)执行sql语句
            resultSet = statement.executeQuery();
       // 4)遍历结果集
         //因为我们在创建数据库表的时候 设置的是name是独一无二的
         //所以有就是有 没就是没
         if (resultSet.next())  {
             User user = new User();
             user.setUserId(resultSet.getInt("userId"));
             user.setName(resultSet.getString("name"));
             user.setPassword(resultSet.getString("password"));
             return user;
         }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5)释放资源
            DBUtil.close(connection,statement,resultSet);
        }
      return null;
    }

    public User selectById(int userId) {
        //1.获取数据库连接
       Connection connection = DBUtil.getConnection();
       //2.拼装sql语句
        String sql = "select * from user where userId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setUserId(resultSet.getInt("userId"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        //1.先测试add方法的正确
        User user = new User();
        //user.setName("zsn");
        //user.setUserId(1);
        //2.测试刚刚插入的用户是否存在
        user = userDao.selectByName("zsn");
        System.out.println(user);
    }

}
