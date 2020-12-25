package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Dish;
import model.DishDao;
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
import java.util.List;

@WebServlet("/dish")
public class DishServlet extends HttpServlet {
    public Gson gson = new GsonBuilder().create();
    static class Request {
        public String name;
        public int price;
    }
    static class Response {
        public int ok;
        public String reason;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        try {
            //1.检查用户的登陆状态
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您还未登陆请先登陆");
            }
            User user = (User)session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("您还未登陆请先登陆");
            }
            //2.检查用户是否是管理员
            if (user.getIsAdmin() == 0) {
                throw new OderSystemException("您不是管理员");
            }
            //3.读取请求body
            String body = OrderSystemUtil.readBody(req);
            //4.把body转成request对象
            Request request = gson.fromJson(body,Request.class);
            //5.构造dish对象插入数据库
            DishDao dishDao = new DishDao();
            Dish dish = new Dish();
            dish.setName(request.name);
            dish.setPrice(request.price);
            dishDao.add(dish);
            response.ok = 1;
            response.reason = "";

        } catch (OderSystemException e) {
            response.ok = 0;
            response.reason = e.getMessage();
        } finally {
            //6.返回结果给客户端
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }

    //删除菜品
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        try {
            //1.检查是否登陆
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您还尚未登陆");
            }
            User user = (User)session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("您还尚未登陆");
            }
            //2.检查用户是否是管理员
            if (user.getIsAdmin() == 0) {
                throw new OderSystemException("您不是管理员,无权操作");
            }
            //3.读取到dishId
            String dishIdStr = req.getParameter("dishId");
            if (dishIdStr == null) {
                throw new OderSystemException("dishId参数不正确");
            }
            int dishId = Integer.parseInt(dishIdStr);
            //4.删除数据库中的对应结果
            DishDao dishDao = new DishDao();
            dishDao.delete(dishId);
            response.ok = 1;
            response.reason = "";
        } catch (OderSystemException e) {
            response.ok = 0;
            response.reason = e.getMessage();
        } finally {
            //5.返回响应结果
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }

    //菜品查看
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        try {
            //1.检查是否登陆
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您还尚未登陆");
            }
            User user = (User)session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("您还尚未登陆");
            }
            //2.从数据库中读取记录
            DishDao dishDao = new DishDao();
            List<Dish> dishes = dishDao.selectAll();
            //3.将结果返回到页面
            //使用gson 将结果写为数组形式
            String jsonString = gson.toJson(dishes);
            resp.getWriter().write(jsonString);
            response.ok = 1;
            response.reason = "";
        } catch (OderSystemException e) {
            response.ok = 1;
            response.reason = e.getMessage();
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}
