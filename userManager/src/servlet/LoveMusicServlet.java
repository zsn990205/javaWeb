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

/*
添加喜欢的音乐到数据库中
 */
@WebServlet("/loveMusicServlet")
public class LoveMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        MusicDao musicDao = new MusicDao();
        Map<String,Object> return_map = new HashMap<>();

        String strId = req.getParameter("id");
        int musicId = Integer.parseInt(strId);

        User user = (User) req.getSession().getAttribute("user");
        int user_id = user.getId();

        //将音乐添加到我喜欢的音乐前提得知道音乐的两大基本信息
        //其次重要的一点是 去查看音乐之前是否被添加过了 如果已经添加无需再次添加
       boolean ret = musicDao.findMusicByMusicId(user_id,musicId);
       if (ret) {
           //已经被添加了
           return_map.put("msg",false);
       } else {
          boolean rets = musicDao.insertLoveMusic(user_id,musicId);
          if (rets) {
             return_map.put("msg",true);
          } else {
              return_map.put("msg",false);
          }
       }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);
}
    }

