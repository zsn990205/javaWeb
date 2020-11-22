package model;


import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//管理数据库连接
//建立 & 断开
//使用DataSource来管理连接
//DBUtil相当于给DataSource在包装一层
//DataSource每个应用程序只有一个实例
//DBUtil本质上就是单例模式 管理了唯一的实例
//使用懒汉模式
public class DBUtil {
    //懒汉模式本质上是线程不安全的
    //在操作的时候我们要注意三部曲
    //双重if判null  加锁   volatile
    volatile  private static DataSource dataSource = null;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/blogDemo?characterEncoding=utf-8&useSSL=true";
    private static final String name = "root";
    //linux上MySQL没密码
    private static final String password = "";
    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    //还需给DataSource一些设置
                    ((MysqlDataSource)dataSource).setURL(url);
                    ((MysqlDataSource)dataSource).setPassword(password);
                    ((MysqlDataSource)dataSource).setUser(name);
                }
            }
        }
        return dataSource;
    }

    //通过这个方法来获取连接
    //Connection使用java.sql版本的
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过这个方法来断开连接
    public static void close(Connection connection, PreparedStatement preparedStatement,
                             ResultSet resultSet) {
           try {
               if (resultSet != null) {
                   resultSet.close();
               }
               if (preparedStatement != null) {
                   preparedStatement.close();
               }
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }

