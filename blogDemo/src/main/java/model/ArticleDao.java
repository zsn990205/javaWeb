package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    //1.新增文章(发布内容)
    public void add(Article article)  {
      // 1)获取数据库连接
        Connection connection = DBUtil.getConnection();
      // 2)拼装sql语句
        String sql = "insert into article values (null, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getContent());
            statement.setInt(3,article.getUserId());
       // 3)执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("插入文章失败");
                return;
            }
            System.out.println("插入文章成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
       // 4)释放连接
            DBUtil.close(connection,statement,null);
        }
    }


    //2.查看文章列表(查找所有文章信息)
    public List<Article> selectAll() {
        List<Article> articles = new ArrayList<>();
        // 1)与数据库建立连接
        Connection connection = DBUtil.getConnection();
        // 2)拼装sql语句
        String sql = "select articleId,userId,title from article";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
        // 3)执行sql语句
            resultSet = statement.executeQuery();
        // 4)遍历结果集
         while (resultSet.next())  {
             Article article = new Article();
             article.setArticleId(resultSet.getInt("articleId"));
             article.setUserId(resultSet.getInt("userId"));
             article.setTitle(resultSet.getString("title"));
             articles.add(article);
         }
         return articles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5)释放连接
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    //3.查看指定文章详情(要查看正文)
    public Article selectById(int articleId) {
        // 1)与数据库建立连接
        Connection connection = DBUtil.getConnection();
        // 2)拼装sql语句
        String sql = "select * from article where articleId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,articleId);
        // 3)执行sql语句
            resultSet = statement.executeQuery();
        // 4)遍历结果集
            if(resultSet.next()) {
                Article article = new Article();
                article.setUserId(resultSet.getInt("articleId"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(resultSet.getInt("userId"));
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5)释放连接
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    //4.删除文章(给定id删除)
    public void delete(int articleId) {
      // 1)建立连接
        Connection connection = DBUtil.getConnection();
      // 2)拼装sql语句
        String sql = "delete from article where articleId = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,articleId);
      // 3)执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("删除文章失败");
                return;
            }
            System.out.println("删除文章成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4)释放连接
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) {
       ArticleDao articleDao = new ArticleDao();
       Article article = new Article();
       //1.测试新增文章
        /*
       article.setTitle("这是俺的blog");
       article.setContent("blog正文blog正文blog");
       article.setUserId(1);
       articleDao.add(article);
       */

       //2.查看文章列表
        /*
        List<Article> articles = articleDao.selectAll();
        System.out.println(articles);

         */

        //3.查看文章正文
        /*
        Article article1 = articleDao.selectById(2);
        System.out.println(article1);
         */

        //4.给定id删除文章
        articleDao.delete(1);
    }
}
