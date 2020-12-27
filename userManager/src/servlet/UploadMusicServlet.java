package servlet;


import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*
一:
上传音乐到服务器目录下
 */
@WebServlet("/upload")
public class UploadMusicServlet extends HttpServlet {
    private final String savePath = "E:\\java\\userManager\\web\\music";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //登陆的人上传的音乐
        User user = (User)req.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("您尚未登陆,请登录后操作");
            resp.getWriter().write("<h2>您尚未登陆,请登录后操作</h2>");
            return;
        } else {
            /*
            上传文件固定流程
             */
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> fileItems = null;

            try {
                fileItems = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("fileItems: " + fileItems);
            FileItem fileItem = fileItems.get(0);
            System.out.println("fileItem: " + fileItem);

            String fileName = fileItem.getName(); //获取文件名
            req.getSession().setAttribute("fileName",fileName);
            try {
                fileItem.write(new File(savePath,fileName));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            //已经在服务器上传成功 接下来需要上传到数据库中
            //先跳转到输入歌手姓名的页面
            resp.sendRedirect("uploadsucess.html");
        }
    }
}
