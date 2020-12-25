package model;

import util.OderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//操作订单
//1.新增订单 [!好好看看!]
//2.查看所有订单(管理员->老板)
//3.查看指定用户的订单(普通用户->顾客)
//4.查看指定订单的详细信息 [!好好看看!]
//5.修改订单状态(订单是否完成)
public class OrderDao {
    public void add(Order order) throws OderSystemException {
        //订单是两个表关联的
        //第一个表order_User(先操作)
        //第二个表order_dish
        //一个订单会涉及多个菜品 所以也需要这个表一次性插入多条数据记录

        //如果第一步执行成功第二步失败 整体还是失败
        //因此需要用事物的原子性来保证整体的统一性(但是代码比较麻烦)
        //使用回滚 当第二次失败了进行回滚直接删除第一步order_User的表的记录
        addOrderUser(order);
        addOrderDish(order);
    }

    //把菜品信息插入到order_dish中
    //要执行这个代码 得知道orderId 但是在前面addOrderUser中orderId是数据库根据
    //自增功能生成的 数据库可以查出这个orderId是几但是Order对象无法获取这个orderId
    //为了解决这个问题 就要在数据库插入记录的同时获取自增主键orderId的值
    private void addOrderDish(Order order) throws OderSystemException {
        //1.建立数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        //构造一个sql使用多个value值构造进去
        String sql = "insert into order_dish values(?,?)";
        try {
            //3.关闭自动提交 [默认调用executeXXX是自动提交的 在此场景下应该先关闭然后进行手动提交]
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            //由于一个订单包含多个菜品 就需要遍历Order中包含的菜品数组,把每个记录都取出来
            //4.遍历dishes给SQL添加多个values的值
            List<Dish> dishes = order.getDishes();
            for (Dish dish : dishes) {
                statement.setInt(1, order.getOrderId());
                statement.setInt(2, dish.getDishId());
                //addBatch是增加许多括号 把多个语句合并成一个sql语句
                statement.addBatch();
            }
            //5.执行sql语句(非真正的执行)
            statement.executeBatch();
            //6.真正发送给服务器执行 commit可以执行多个sql一次调用commit统一发送给服务器
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //如果上面的代码出现异常则认为整体的新增失败 使用回滚将order_User中新增的记录删除
            deleteOrderUser(order.getOrderId());
        } finally {
            //7.关闭连接
            DBUtil.close(connection, statement, null);
        }
    }

    private void deleteOrderUser(int orderId) throws OderSystemException {
        //1.获取连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "delete from order_User where orderId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            //3.执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new OderSystemException("回滚失败");
            }
            System.out.println("回滚成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("回滚失败");
        } finally {
            //4.关闭连接
            DBUtil.close(connection, statement, null);
        }

    }

    private void addOrderUser(Order order) throws OderSystemException {
        //1.建立数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "insert into order_User values(null,?,now(),0)";
        try {
            //插入完成的同时返回自增主键的值
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getUserId());
            //3.执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new OderSystemException("插入订单失败");
            }
            //把自增主键的值读取出来
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                //自增主键可以存在多列 1表示获取第一个自增列生成的值
                order.setOrderId(resultSet.getInt(1));
            }
            System.out.println("插入订单第一步成功!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("插入订单第一步失败");
        } finally {
            //4.关闭连接
            DBUtil.close(connection, statement, resultSet);
        }
    }

    //获取所有订单信息
    //在order_user可以查到orderId userId这些属性
    //详细信息需要找到order_dish这张表 获取dishId然后根据dishId去dishes表中查找
    //这里不需要查的那么详细 获取基本信息即可(不包含dishes信息)
    public List<Order> selectAll() {
        List<Order> orders = new ArrayList<>();
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "select * from order_User";
        try {
            statement = connection.prepareStatement(sql);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DBUtil.close(connection, statement, resultSet);
        }
        return orders;
    }

    public List<Order> selectByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "select * from order_User where userId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            while (resultSet.next()) {
                //一个用户可以有多个订单
                Order order = new Order();
                order.setUserId(resultSet.getInt("userId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DBUtil.close(connection, statement, resultSet);
        }
        return orders;
    }

    public Order selectById(int orderId) throws OderSystemException {
        //1.根据orderId构造一个order对象
        Order order = buildOrder(orderId);
        //2.根据orderId得到该orderId对应的dishId的列表
        List<Integer> dishIds = selectDishIds(orderId);
        //3.根据id列表查询dishes列表 获取菜品详情
        order = getDishDetail(order, dishIds);
        return order;
    }

    //根据orderId来查询对应的order对象的基本信息
    private Order buildOrder(int orderId) {
        Order order = new Order();
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "select * from order_User where orderId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            if (resultSet.next()) {
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setIsDone(resultSet.getInt("isDone"));
                order.setTime(resultSet.getTimestamp("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DBUtil.close(connection, statement, resultSet);
        }
        return order;
    }

    private List<Integer> selectDishIds(int orderId) {
        List<Integer> dishIds = new ArrayList<>();
        //1.获取数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "select * from order_dish where orderId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            while (resultSet.next()) {
                dishIds.add(resultSet.getInt("dishId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DBUtil.close(connection, statement, resultSet);
        }
        return dishIds;
    }

    //根据order和dishIds查询详细的dishes表中的内容
    private Order getDishDetail(Order order, List<Integer> dishIds) throws OderSystemException {
        //1.准备好盛放的结果集
        List<Dish> dishes = new ArrayList<>();
        //2.遍历dishIds在dishes中查找
        DishDao dishDao = new DishDao();
        for (Integer dishId : dishIds) {
            Dish dish = dishDao.selectById(dishId);
            dishes.add(dish);
        }
        //3.把dishes设置到order对象中
        order.setDishes(dishes);
        return order;
    }

    //修改订单状态
    public void changeState(int orderId,int isDone) throws OderSystemException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();
        String sql = "update order_User set isDone = ? where orderId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,isDone);
            statement.setInt(2,orderId);
            int ret = statement.executeUpdate();
            if (ret != 1) {
               throw new OderSystemException("修改订单状态失败!");
            }
            System.out.println("修改订单状态成功!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OderSystemException("修改订单状态失败!");
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
}
