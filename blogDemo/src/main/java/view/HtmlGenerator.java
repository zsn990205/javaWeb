package view;

import model.Article;
import model.User;

import java.util.List;

public class HtmlGenerator {
    //通过字符串拼接 构造出一个html页面来
    public static String getMessagePage(String message, String nextUrl) {
        //这个方法表示 返回的错误页面信息以及接下来要跳转到哪个页面
     StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("meta charset=\"utf-8\"");
        stringBuilder.append("<title>提示页面</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");

        stringBuilder.append("<h3>");
        stringBuilder.append(message);
        stringBuilder.append("</h3>");

        stringBuilder.append(String.format("<a href = \"%s\"> 点击这里进行跳转</a>",
                nextUrl));

        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }

    //按照字符串拼接的方式 生成html
    public static String getArticleListPage(List<Article> articles, User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("meta charset=\"utf-8\"");
        stringBuilder.append("<title>提示页面</title>");
        stringBuilder.append("<style>");

        // style 标签内部就是写 CSS 的逻辑
        stringBuilder.append(".article {" +
                "color: #333;" +
                "text-decoration: none;" +
           //     "display: inline-block;" +
                "width: 200px;" +
                "height: 50px;" +
                "}");
        stringBuilder.append(".article:hover {" +
                "color: white;" +
                "background-color: orange;" +
                "}");
        stringBuilder.append("body {" +
                "background-repeat: none;" +
                "background-position: 0 center;" +
                "}");
        stringBuilder.append("</style>");

        stringBuilder.append("</head>");
        stringBuilder.append("<body>");

        stringBuilder.append("<h3> 欢迎您!" + user.getName()+ "</h3>");
        //hr表示分隔符
        stringBuilder.append("<hr>");
        for (Article article : articles) {
            //a href是要跳转到那个(%d)页面
            //%s是文章内容
            stringBuilder.append(String.format("<div style=\"width: 200px; height: 50px; line-height: 50px\"> <a href=\"article?articleId=%d\"> %s </a>" +
                            "<a href=\"deleteArticle?articleId=%d\">删除</a></div>",
                    article.getArticleId(), article.getTitle(), article.getArticleId()));
        }
        stringBuilder.append("<hr>");
        stringBuilder.append(String.format("<div>当前共有博客 %d 篇</div>",articles.size()));

        //在这里新增发布文章的区域
        stringBuilder.append("<div>发布文章</div>");
        stringBuilder.append("<div>");
        stringBuilder.append("<form method=\"post\" action=\"article\">");
        stringBuilder.append("<input type=\"text\" style=\"width: 500px; margin-bottom: 5px;\" name=\"title\" placeholder=\"请输入标题\">");
        stringBuilder.append("<br />");
        //这个表示多行输入框(特大的输入框)
        stringBuilder.append("<textarea name=\"content\" style=\"width: 500px; height: 300px;\" ></textarea>");
        stringBuilder.append("<br />");
        stringBuilder.append("<input type=\"submit\" value=\"发布文章\">");
        stringBuilder.append("</form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }

    public static String getArticleDetailPage(Article article, User user, User author) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"utf-8\">");
        stringBuilder.append("<title>提示页面</title>");
        stringBuilder.append("<style>");
        // style 标签内部就是写 CSS 的逻辑
        stringBuilder.append("a {" +
                "color: #333;" +
                "text-decoration: none;" +
                "display: inline-block;" +
                "width: 200px;" +
                "height: 50px;" +
                "}");
        stringBuilder.append("a:hover {" +
                "color: white;" +
                "background-color: orange;" +
                "}");
        stringBuilder.append("body {" +
                "background-repeat: none;" +
                "background-position: 0 center;" +
                "}");
        stringBuilder.append("</style>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<h3> 欢迎您! " + user.getName() + "</h3>");
        stringBuilder.append("<hr>");

        stringBuilder.append(String.format("<h1>%s</h1>", article.getTitle()));
        stringBuilder.append(String.format("<h4>作者: %s</h4>", author.getName()));
        // 构造正文的地方.
        // HTML 中本来就不是用 \n 表示换行的.
        stringBuilder.append(String.format("<div>%s</div>", article.getContent()).replace("\n","<br>"));

        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
}
