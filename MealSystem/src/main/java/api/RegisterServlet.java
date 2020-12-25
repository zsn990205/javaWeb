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
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    static class Request {
       public String name;
       public String password;
    }

    static class Response {
       public int ok;
       public String reason;
    }

    //try中是正常逻辑 catch是错误异常
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("utf-8");
            Response response = new Response();
        try {
            //1.读取body数据
            String body = OrderSystemUtil.readBody(req);
            //2.把body数据解析成request对象(Gson)
            Request request = gson.fromJson(body,Request.class);
            //3.查询数据库,查找当前的用户名是否存在,若存在说明此人已经注册过了无需注册
            UserDao userDao = new UserDao();
            User exitUser = userDao.selectByName(request.name);
            if (exitUser != null) {
                throw new OderSystemException("当前用户名已经存在,请重新输入");
            }
            //4.把提交的用户名和密码构造成一个User对象 插入数据库
            User user = new User();
            user.setName(request.name);
            user.setPassword(request.password);
            user.setIsAdmin(0);
            userDao.add(user);
            response.ok = 1;
            response.reason="";
        } catch (OderSystemException e) {
            e.printStackTrace();
            response.ok = 0;
            //getMessage得到的信息就是上文抛出的异常的文字
            response.reason = e.getMessage();
        } finally {
            //5.构造响应数据
            String jsonString = gson.toJson(response);
            //固定写法 顺序不可颠倒
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }

    }
}
