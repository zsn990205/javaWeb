package model;

import util.OderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//操作菜品
//1.新增菜品
//2.根据id删除菜品
//3.查询所有菜品
//4.查询指定菜品
//也可修改菜品信息(改价格)
public class DishDao {
    public void add(Dish dish) throws OderSystemException {
    //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
    //2.拼装sql语句
        String sql = "insert into dishes values(null,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,dish.getName());
            statement.setInt(2,dish.getPrice());
    //3.执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new OderSystemException("新增菜品失败");
            }
            System.out.println("新增菜品成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("新增菜品失败");
        } finally {
    //4.关闭连接
            DBUtil.close(connection,statement,null);
        }
    }

    public void delete (int dishId) throws OderSystemException {
    //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
    //2.拼装sql语句
        String sql = "delete from dishes where dishId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,dishId);
     //3.执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new OderSystemException("根据id删除菜品失败");
            }
            System.out.println("删除菜品成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("根据id删除菜品失败");
        } finally {
            //4.关闭连接
            DBUtil.close(connection,statement,null);
        }
    }

    public List<Dish> selectAll() throws OderSystemException {
        List<Dish> dishes = new ArrayList<>();
    //1.建立数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
    //2.拼装sql语句
        String sql = "select * from dishes";
        try {
            statement = connection.prepareStatement(sql);
    //3.执行sql语句
            resultSet = statement.executeQuery();
    //4.遍历结果集
        while (resultSet.next()) {
            Dish dish = new Dish();
            dish.setDishId(resultSet.getInt("dishId"));
            dish.setName(resultSet.getString("name"));
            dish.setPrice(resultSet.getInt("price"));
            dishes.add(dish);
        }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("查找所有菜品失败");
        } finally {
    //5.关闭连接
            DBUtil.close(connection,statement,resultSet);
        }
        return dishes;
    }

    public Dish selectById(int dishId) throws OderSystemException {
    //1.建立数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
    //2.拼装sql语句
        String sql = "select * from dishes where dishId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,dishId);
    //3.执行sql语句
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishId(resultSet.getInt("dishId"));
                dish.setName(resultSet.getString("name"));
                dish.setPrice(resultSet.getInt("price"));
                return dish;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("按照id查找菜品失败");
        } finally {
            //4.关闭连接
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) throws OderSystemException {
        DishDao dishDao = new DishDao();
        //测试新增
//         Dish dish = new Dish();
//         dish.setName("毛血旺");
//         dish.setPrice(66000);
//         dishDao.add(dish);
        //测试查看所有菜品
//         List<Dish> list = dishDao.selectAll();
//         System.out.println("ALL");
//         System.out.println(list);
//         //测试按id查找菜品
//         Dish dish = dishDao.selectById(1);
//         System.out.println("ONE");
//         System.out.println(dish);
        //测试删除菜品
        dishDao.delete(1);
    }
}
