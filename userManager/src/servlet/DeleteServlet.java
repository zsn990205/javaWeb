package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
删除某一个音乐
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private final String savePath = "E:\\java\\userManager\\web\\";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String strId = req.getParameter("id");
        int id = Integer.parseInt(strId);

        MusicDao musicDao = new MusicDao();
        //在删除音乐之前先判断是否存在这个音乐
        Music music = musicDao.findMusicById(id);
        if (music == null)  return;
         else {
            //只能保证删除的是数据库中的音乐
            int delete = musicDao.deleteMusicById(id);
            Map<String,Object> return_map = new HashMap<>();
            if (delete == 1) {
                //数据库中音乐的路径是:音乐的路径.MP3
                File file = new File(savePath+music.getUrl()+".mp3");
                if (file.delete()) {
                //IO流返回的是true/false
                //表示删除成功
                    return_map.put("msg",true);
                    System.out.println("服务器删除成功!");
                } else {
                    return_map.put("msg",false);
                    System.out.println("服务器删除失败!");
                }
            } else {
                return_map.put("msg",false);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(),return_map);
        }

    }
}
