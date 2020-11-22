package api;

import model.Article;
import model.ArticleDao;
import model.User;
import model.UserDao;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1.验证用户是否登陆 如果未登录就提示用户先去登陆
        HttpSession httpSession = req.getSession(false);
        if (httpSession == null) {
            //用户尚未登陆 让他去登陆
            String html = HtmlGenerator.getMessagePage("您尚未登陆,请先去登陆",
                    "login.html");
            resp.getWriter().write(html);
            return;
        }
            //用户登陆成功 获取User对象
            //之前登陆的参数都在session中保存过了
            //现在只需要去session中查找uer即可
        User user = (User) httpSession.getAttribute("user");

        //2.判断请求中是否存在articleId参数
        String articleStr = req.getParameter("articleId");
        if (articleStr == null) {
            //a>没有这个参数就去执行获取文章列表操作
            getAllArticle(user,resp);
        } else {
            //b>有这个参数就去获取文章详情(就是某一篇文章的内容)
            getOneArticle(Integer.parseInt(articleStr),user,resp);
        }

    }

    private void getOneArticle(int articleId, User user, HttpServletResponse resp) throws IOException {
        //1.查找数据库
        ArticleDao articleDao = new ArticleDao();
        Article article = articleDao.selectById(articleId);
        if (article == null) {
            // 文章未找到
            String html = HtmlGenerator.getMessagePage("文章不存在",
                    "article");
            resp.getWriter().write(html);
            return;
        }
        //2.根据作者id找到作者信息 进一步得到作者姓名
        UserDao userDao = new UserDao();
        User author = userDao.selectById(article.getUserId());
        //3.构造页面
        String html = HtmlGenerator.getArticleDetailPage(article,user,author);
        resp.getWriter().write(html);
    }


    private void getAllArticle(User user, HttpServletResponse resp) throws IOException {
        // 1.查找数据库
        ArticleDao articleDao = new ArticleDao();
        List<Article> articles = articleDao.selectAll();
        // 2.构造页面
        String html = HtmlGenerator.getArticleListPage(articles,user);
        resp.getWriter().write(html);
    }

    //实现新增文章
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
    //1.判定用户的登陆状态 如果用户尚未登陆提醒用户登陆
        HttpSession httpSession = req.getSession(false);
          if (httpSession == null) {
            String html =  HtmlGenerator.getMessagePage("您尚未登陆","login");
            resp.getWriter().write(html);
            return;
        }
        User user = (User) httpSession.getAttribute("user");

    //2.从请求读取浏览器的数据(title content进行校验)
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || "".equals(title) ||
           content == null || "".equals(content)) {
           String html = HtmlGenerator.getMessagePage("您提交的标题或正文为空",
                   "article");
           resp.getWriter().write(html);
           return;
        }

    //3.将数据插入数据库中
        ArticleDao articleDao = new ArticleDao();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(user.getUserId());
        articleDao.add(article);
    //4.返回一个插入成功的页面
        String html = HtmlGenerator.getMessagePage("发布成功!",
                "article");
        resp.getWriter().write(html);
        return;
    }
}
