package dao;

import model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2019/8/25.
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
}
