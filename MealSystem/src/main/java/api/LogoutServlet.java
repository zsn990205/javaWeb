package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.OderSystemException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
        public Gson gson = new GsonBuilder().create();

        static class Response {
            public int ok;
            public String reason;
        }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        try {
            //1.根据sessionId找到对应的session对象
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OderSystemException("您尚未登陆");
            }
            //2.把session对象中存的user信息删除掉即可(直接删除session中对应的键值对也行)
            session.removeAttribute("user");
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
}
