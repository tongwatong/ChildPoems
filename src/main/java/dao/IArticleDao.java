package dao;

import model.Article;

/**
 * 用户的持久化层
 * @author 童啊童
 */
public interface IArticleDao {
    /**
     * 根据文章名字查找
     */
    Article findArticleByTitle(String articleTitle);
}
