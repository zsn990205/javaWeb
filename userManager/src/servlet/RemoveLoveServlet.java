package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/removeLoveServlet")
public class RemoveLoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        MusicDao musicDao = new MusicDao();
        Map<String,Object> return_map = new HashMap<>();

        //删除的时候只删除数据库的 不删除服务器的数据
        //方法需要两个参数 userId musicId
        String idStr = req.getParameter("id");
        int musicId = Integer.parseInt(idStr);

        User user = (User)req.getSession().getAttribute("user");
        int userId = user.getId();

        int ret = musicDao.removeLoveMusic(userId,musicId);
        if (ret == 1) {
          return_map.put("msg",true);
        } else {
            return_map.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);

    }
}
