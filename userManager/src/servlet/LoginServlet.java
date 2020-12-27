package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");
       resp.setContentType("application/json;charset=utf-8");

       String name = req.getParameter("username");
       String password = req.getParameter("password");

       User loginUser = new User();
       loginUser.setUsername(name);
       loginUser.setPassword(password);

        userService userService = new userService();
        User user = userService.login(loginUser);

        Map<String ,Object> return_map = new HashMap<>();
        if (user != null) {
            System.out.println("登陆成功!");
            return_map.put("msg",true);
            //登陆成功 将登陆者的session记录下
            req.getSession().setAttribute("user",user);
        } else {
            System.out.println("登陆失败!");
            return_map.put("msg",false);
        }

        //将return_map返回给前端的方法
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);

    }
}
