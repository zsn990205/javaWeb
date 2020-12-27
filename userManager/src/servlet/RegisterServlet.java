package servlet;

import dao.UserDao;
import entity.User;
import service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
关于注册的所有代码还没写完 还有问题
 */

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //1.获取前端数据并进行校验是否合法
        //2.拿着数据去数据库查找是否存在
        //3.构造User对象插入数据库
        //4.返回登陆页面
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        if (name == null || "".equals(name) || password == null
                || password.equals("") ) {
            resp.sendRedirect("register.html");
        }

        User exitUser = new User();
        exitUser.setUsername(name);
        exitUser.setPassword(password);

        UserDao userDao = new UserDao();
        exitUser = userDao.login(exitUser);
        if (exitUser != null) {
            resp.sendRedirect("register.html");
        }

        req.getSession().setAttribute("user",exitUser);

    }
}
