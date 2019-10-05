package service;

import dao.ArticleDao;
import model.Article;

/**
 * Created by Administrator on 2019/8/25.
 */
public class ArticleService {
    Article article = null;

    public Article findArticleById(int ariticleId) {
        ArticleDao articleDao = new ArticleDao();
        article = articleDao.findArticleById(ariticleId);

        return article;
    }
    public void addArticle(Article article) {
        ArticleDao articleDao = new ArticleDao();
        articleDao.addArticle(article);
    }
}
