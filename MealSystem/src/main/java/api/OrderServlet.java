package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Dish;
import model.Order;
import model.OrderDao;
import model.User;
import util.OderSystemException;
import util.OrderSystemUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    public Gson gson = new GsonBuilder().create();
    static class Response {
        public int ok;
        public String reason;
    }

    //规定只有普通用户可以新增管理员用户不可以
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        try {
            //1.检查是否登陆
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您还尚未登陆");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("您还尚未登陆");
            }
            //2.检查是否是管理员用户
            if (user.getIsAdmin() == 1) {
                throw new OderSystemException("您是管理员无法新增订单");
            }
            //3.读取Body中的数据进行解析
            String body = OrderSystemUtil.readBody(req);
            //4.按照gson格式进行解析
              //这是转成整型数组的写法 字符-->数字
            Integer[] dishIds = gson.fromJson(body,Integer[].class);

               //这是转成List的写法 字节码中没有List<Integer>.class
               //List<Integer> dishId = gson.fromJson(body,new TypeToken<List<Integer>>() {}.getType());

            //5.构造订单对象(得到的数组中是一串数字,往订单中插入菜品的id)
            Order order = new Order();
            //得到用户是谁才可以让这个用户进行新增订单
            order.setUserId(user.getUserId());
            //dishes中存储的是一系列dishId
            List<Dish> dishes = new ArrayList<>();
            //遍历数组的各个菜品id
            for (Integer dishId : dishIds) {
                Dish dish = new Dish();
                dish.setDishId(dishId);
                dishes.add(dish);
            }
            order.setDishes(dishes);
            //6.插入数据库
            OrderDao orderDao = new OrderDao();
            orderDao.add(order);
            response.ok = 1;
            response.reason = "";
        } catch (OderSystemException e) {
            response.ok = 0;
            response.reason = e.getMessage();
        } finally {
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }

    //查看订单
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        Response response = new Response();
        List<Order> orders = new ArrayList<>();
        try {
            // 1. 验证用户登陆状态.
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您尚未登陆");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("您尚未登陆");
            }
            // 2. 读取 orderId 字段, 看该字段是否存在.
            OrderDao orderDao = new OrderDao();
            String orderIdStr = req.getParameter("orderId");
            if (orderIdStr == null) {
                // 3. 查找数据库, 查找所有订单
                // 判断用户是管理员还是普通用户
                if (user.getIsAdmin() == 0) {
                    // 普通用户, 只查看自己的订单
                    orders = orderDao.selectByUserId(user.getUserId());
                } else {
                    // 管理员, 查看所有订单
                    orders = orderDao.selectAll();
                }
                // 4. 构造响应结果
                String jsonString = gson.toJson(orders);
                resp.getWriter().write(jsonString);
            } else {
                // 3. 查找数据库, 查找指定订单.
                int orderId = Integer.parseInt(orderIdStr);
                Order order = orderDao.selectById(orderId);
                // [此处还可以有个小小的改进]
                // 如果是普通用户, 查找时发现自身的 userId 和订单的 userId 不相符,
                // 这种就返回一个出错数据.
                // 如果是管理员, 才允许查看所有用户的订单
                if (user.getIsAdmin() == 0
                        && order.getUserId() != user.getUserId()) {
                    throw new OderSystemException("当年您无权查看其他人的订单");
                }
                // 4. 构造响应结果
                String jsonString = gson.toJson(order);
                resp.getWriter().write(jsonString);
            }
        } catch (OderSystemException e) {
            // 5. 处理异常情况
            String jsonString = gson.toJson(orders);
            resp.getWriter().write(jsonString);
        }
    }

    //修改订单状态
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        try {
            //1.检查是否登陆
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您尚未登陆");
            }
            User user = (User)session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("您尚未登陆");
            }
            //2.检查是否是管理员(只有管理员才可以登陆)
            if (user.getIsAdmin() == 0) {
                throw new OderSystemException("您不是管理员无权修改");
            }
            //3.读取orderId和isDone字段并进行修改
            String orderIdStr = req.getParameter("orderId");
            String isDoneStr = req.getParameter("isDone");
            if (orderIdStr == null || isDoneStr == null) {
                throw new OderSystemException("参数有误");
            }
            //4.修改数据库
            int orderId = Integer.parseInt(orderIdStr);
            int isDone = Integer.parseInt(isDoneStr);
            OrderDao orderDao = new OrderDao();
            orderDao.changeState(orderId,isDone);
            //5.返回页面
            response.ok = 1;
            response.reason = "";
        } catch (OderSystemException e) {
            response.ok = 1;
            response.reason = e.getMessage();
        } finally {
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}
