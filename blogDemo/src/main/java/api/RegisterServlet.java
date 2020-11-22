package api;

import model.User;
import model.UserDao;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1.获取到前端提交的数据(用户名,密码)并校验是否合法
        //参数中的"name"和"password"早已经在前端中约定好的
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (name == null || "".equals(name) || password == null
        || "".equals(password)) {
        //用户提交的数据有误 返回错误页面
            String html = HtmlGenerator.getMessagePage("用户名或者密码为空","register.html");
            resp.getWriter().write(html);
            return;
        }

       //2.拿数据去数据库查找用户是否存在 若存在则注册失败
        UserDao userDao = new UserDao();
        User exitUser = userDao.selectByName(name);
        if (exitUser != null) {
         //用户名存在
            String html = HtmlGenerator.getMessagePage("用户名已经存在请输入新的用户名",
                 "register.html");
            resp.getWriter().write(html);
            return;
        }

        //3.构造User对象 插入数据库中
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.add(user);

        //4.返回注册成功的页面
        String html = HtmlGenerator.getMessagePage("恭喜您注册成功,点击跳转即刻登陆",
                "login.html");
        resp.getWriter().write(html);
    }
}
