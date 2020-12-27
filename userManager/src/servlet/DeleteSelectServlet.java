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

@WebServlet("/deleteSelMusicServlet")
public class DeleteSelectServlet extends HttpServlet {
    private final String savePath = "E:\\java\\userManager\\web\\";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //数组用来存放所有要删除的id的集合
        String[] idStr = req.getParameterValues("id[]");
        MusicDao musicDao = new MusicDao();
        int sum = 0;
        Map<String,Object> return_map = new HashMap<>();
        for (int i = 0; i < idStr.length; i++) {
            //将每一个id字符串转换成整数型id
            int id = Integer.parseInt(idStr[i]);
            Music music = musicDao.findMusicById(id);
            if (music == null) return;
            else {
                int delete = musicDao.deleteMusicById(id);
                if (delete == 1) {
                  //数据库删除成功 服务器也得进行对等的删除
                    File file = new File(savePath+music.getUrl()+".mp3");
                  if (file.delete()) {
                      sum += delete;
                  } else {
                      System.out.println("服务器删除失败");
                      return_map.put("msg",false);
                  }
                } else {
                    System.out.println("数据库删除失败");
                    return_map.put("msg",false);
                }
            }

        }
        if (sum == idStr.length) {
            //将选中的歌曲全部删除才算删除成功!
            return_map.put("msg",true);
        } else {
            return_map.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);
    }
}
