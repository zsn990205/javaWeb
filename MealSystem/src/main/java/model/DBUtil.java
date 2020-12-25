package model;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DBUtil本质上是一个管理了单例的DataSource的类
public class DBUtil {
   private static String URL = "jdbc:mysql://127.0.0.1:3306/MealSystem?characterEncoding=utf-8&useSSL=true";
   private static String USERNAME = "root";
   private static String PASSWORD = "";

   volatile private static DataSource dataSource = null;

   public static DataSource getDataSource() {
       if (dataSource == null) {
           synchronized (DBUtil.class) {
               if (dataSource == null) {
                   dataSource = new MysqlDataSource();
                   ((MysqlDataSource)dataSource).setURL(URL);
                   ((MysqlDataSource)dataSource).setUser(USERNAME);
                   ((MysqlDataSource)dataSource).setPassword(PASSWORD);
               }
           }
       }
          return dataSource;
   }

   //数据库连接失败
   public static Connection getConnection()  {
       try {
           return getDataSource().getConnection();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       System.out.println("数据库连接失败,请检查数据库连接!");
       return null;
   }

   public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
           try {
               if (resultSet != null) {
                   resultSet.close();
               }
               if (statement != null) {
                   statement.close();
               }
               if (connection != null) {
                   connection.close();
               }
           }
           catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }

