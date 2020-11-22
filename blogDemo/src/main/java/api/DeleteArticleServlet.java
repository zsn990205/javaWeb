package api;

import model.Article;
import model.ArticleDao;
import model.User;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1.验证用户的登陆状态 未登录不能删除
        HttpSession httpSession = req.getSession(false);
        if (httpSession == null) {
            String html = HtmlGenerator.getMessagePage("您还尚未登陆","login.html");
            resp.getWriter().write(html);
            return;
        }
        User user = (User) httpSession.getAttribute("user");

    //2.读取请求内容 获取删除的文章的id
        String articleIdStr = req.getParameter("articleId");
        if (articleIdStr == null || "".equals(articleIdStr)) {
            String html = HtmlGenerator.getMessagePage("您要删除文章的id有误","article");
            resp.getWriter().write(html);
            return;
        }

    //3.根据id找到文章的作者 只有作者有权删除文章
        ArticleDao articleDao = new ArticleDao();
        Article article =articleDao.selectById(Integer.parseInt(articleIdStr));
        if (article.getUserId() != user.getUserId()) {
            //判断文章的作者信息和登陆者的信息是否匹配
            //自己不可以删除别人的文章
            String html = HtmlGenerator.getMessagePage("您不能删除别人的文章",
                    "article");
            resp.getWriter().write(html);
            return;
        }

    //4.执行数据库删除操作
         articleDao.delete(Integer.parseInt(articleIdStr));
    //5.删除完成后 返回 一个页面信息
        String html = HtmlGenerator.getMessagePage("删除成功","article");
        resp.getWriter().write(html);
    }
}
