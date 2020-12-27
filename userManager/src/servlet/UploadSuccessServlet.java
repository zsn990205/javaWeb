package servlet;

import dao.MusicDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
上传到数据库中
 */
@WebServlet("/uploadsucess")
public class UploadSuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //获取上传的歌曲的信息
        String singer = req.getParameter("singer");
        String fileName = (String)req.getSession().getAttribute("fileName");
        //文件名是歌名.mp3 歌曲的名字应该去除.mp3
        String[] result = fileName.split("\\.");
        String title = result[0];
        //获取时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        //获取user_id
        User user = (User)req.getSession().getAttribute("user");
        int user_id = user.getId();
        //获取url
        String url = "music//"+title;

        MusicDao musicDao = new MusicDao();
        int ret = musicDao.Insert(title,singer,time,url,user_id);
        if (ret == 1) {
            resp.sendRedirect("list.html");
        }
    }
}
