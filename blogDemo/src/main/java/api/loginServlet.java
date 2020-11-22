package api;

import model.User;
import model.UserDao;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
       //1.获取到用户名和密码并进行简单的校验
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (name == null || "".equals(name) ||
        password == null || "".equals(password)) {
            String html = HtmlGenerator.getMessagePage("您输入的用户名或密码为空请重新输入",
                    "login.html");
            resp.getWriter().write(html);
            return;
        }

       //2.在数据库中查找是否存在
       //3.对比密码是否正确
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(name);
        //下面这行代码表示输入的密码和用户设置的密码不匹配
        if (user == null || !password.equals(user.getPassword())) {
          //这是登陆 为空说明用户不存在
            String html = HtmlGenerator.getMessagePage("用户名或密码错误请重新输入",
                    "login.html");
            resp.getWriter().write(html);
            return;
        }

       //4.如果正确则登陆成功 创建一个session
        //true表示session不存在就创建
        HttpSession httpSession = req.getSession(true);
        //后续可以根据用户的信息查找session是否存在
        httpSession.setAttribute("user",user);

       //5.返回一个登陆成功的页面
        String html = HtmlGenerator.getMessagePage("登陆成功","article");
        resp.getWriter().write(html);
    }
}
