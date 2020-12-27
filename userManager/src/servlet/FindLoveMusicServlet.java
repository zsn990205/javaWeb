package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.Music;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
查找我喜欢的音乐 查不到就返回所有我喜欢的音乐
 */
@WebServlet("/findLoveMusic")
public class FindLoveMusicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        MusicDao musicDao = new MusicDao();
        List<Music> musicList = new ArrayList<>();
        Map<String,Object> return_map = new HashMap<>();

        String loveMusicName = req.getParameter("loveMusicName");
        User user = (User)req.getSession().getAttribute("user");
        int user_id = user.getId();
        if (loveMusicName != null) {
            musicList = musicDao.ifMusicLove(loveMusicName,user_id);
        } else {
            //如果要查的音乐为空
            musicList = musicDao.findLoveMusic(user_id);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),musicList);

    }
}
