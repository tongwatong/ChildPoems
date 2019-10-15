package mybatis;

import dao.IArticleDao;
import model.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 童啊童
 */
public class Mybatis {
    private InputStream in;
    private SqlSession sqlSession;
    private IArticleDao articleDao;

    public void init() {
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        articleDao = sqlSession.getMapper(IArticleDao.class);
    }
    public void destory() {
        sqlSession.commit();
        sqlSession.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Article findArticleByTitle(String articleTitle) {
        init();
        Article article = new Article();
        if (articleDao.findArticleByTitle("%"+articleTitle+"%") != null) {
            article = articleDao.findArticleByTitle("%"+articleTitle+"%");
        }else {
            article.setArticleContent("未找到该文章");
        }
//        System.out.println(article);

        destory();

        return article;
    }
}
