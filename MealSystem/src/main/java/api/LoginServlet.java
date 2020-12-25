package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import model.UserDao;
import util.OderSystemException;
import util.OrderSystemUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class Request {
       public String name;
       public String password;
    }

    static class Response {
       public int ok;
       public String reason;
       public String name;
       public int isAdmin;
    }

    //登陆功能api
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Response response = new Response();
       req.setCharacterEncoding("utf-8");
        try {
            //1.读取body数据
            String body = OrderSystemUtil.readBody(req);
            //2.将读取的数据解析成对象
            Request request = gson.fromJson(body,Request.class);
            //3.按照用户名进行查找并校验密码
            UserDao userDao = new UserDao();
            User user = userDao.selectByName(request.name);
            if (user == null || !user.getPassword().equals(request.password)) {
                throw new OderSystemException("用户名或密码错误");
            }
            //5.登陆成功创建session对象[重要]
            //没有的话就创建一个新的session对象
            HttpSession session = req.getSession(true);
            //将user对象存进去
            session.setAttribute("user",user);
            response.ok = 1;
            response.reason = "";
            response.name = user.getName();
            response.isAdmin = user.getIsAdmin();
        } catch (OderSystemException e) {
            e.printStackTrace();
            //4.登陆失败返回错误提示
            response.ok = 0;
            response.reason = e.getMessage();
        } finally {
            //6.结果写回客户端
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }

    //检测登陆状态api
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Response response = new Response();
      req.setCharacterEncoding("utf-8");
        try {
            //1.获取用户当前的session,如果session不存在则认为当前是未登录状态
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("当前您尚未登陆,请先登录");
            }
            //2.从session中获取user对象
            User user = (User)session.getAttribute("user");
            if (user == null) {
                throw new OderSystemException("当前您尚未登陆,请先登录");
            }
            //3.把user中的信息填进返回值结果中
            response.ok = 1;
            response.reason = "";
            response.name = user.getName();
            response.isAdmin = user.getIsAdmin();
        } catch (OderSystemException e) {
            e.printStackTrace();
            response.ok = 1;
            response.reason = e.getMessage();
        } finally {
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }
}
