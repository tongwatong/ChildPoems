package dao;

import model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 * @date 2019/8/25
 */
public class ArticleDao {
    Article articleReturn = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Article findArticleById(int articleId) {
        try {
            connection = Utils.getConnection();
            String sql = "SELECT * FROM article WHERE article_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                resultSet.beforeFirst();
                articleReturn = new Article();
                while (resultSet.next()) {
                    articleReturn.setArticleTitle(resultSet.getString("article_title"));
                    articleReturn.setArticleWriter(resultSet.getString("article_writer"));
                    articleReturn.setArticleContent(resultSet.getString("article_content"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.close(resultSet, preparedStatement, connection);
        }

        return articleReturn;
    }
    public void addArticle(Article article) {
        try {
            connection = Utils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into article(article_title, article_writer, article_content)" +
                "values (?, ?, ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getArticleTitle());
            preparedStatement.setString(2, article.getArticleWriter());
            preparedStatement.setString(3, article.getArticleContent());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.close(preparedStatement, connection);
        }


    }
}
