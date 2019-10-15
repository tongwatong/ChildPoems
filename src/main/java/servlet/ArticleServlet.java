package servlet;

import model.Article;
import mybatis.Mybatis;
import service.ArticleAction;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Administrator
 * @date 2019/8/25
 */
@WebServlet("/article_servlet")
public class ArticleServlet extends HttpServlet {
    Article article = null;
    ArticleService articleService = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

//        String content = request.getParameter("articleContent");
//        System.out.println(content);
        System.out.println(ArticleAction.action);
        String action = ArticleAction.action;
//        System.out.println(action);

        switch (action) {
            case "article_list":
                this.getArticleList(request, response);
                break;
            case "article_add":
                this.getArticleAdd(request, response);
                break;
            case "article_search":
                this.findArticleByTitle(request, response);
        }
//        System.out.println("article_servlet success");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        this.doPost(request, response);
    }

    public void getArticleList(HttpServletRequest request, HttpServletResponse response) {
        Integer count = 1;
        String flag = request.getParameter("flag");
        if (flag != null) {
            Cookie articleCount = new Cookie("articleCount", count.toString());
            response.addCookie(articleCount);
            Cookie[] cookies = request.getCookies();
            for (Cookie foo : cookies) {
                if ("articleCount".equals(foo.getName())) {
                    count = Integer.parseInt(foo.getValue());
                    switch (flag) {
                        case "last":
                            count--;
                            response.addCookie(new Cookie("articleCount", count.toString()));
                            break;
                        case "next":
                            count++;
                            System.out.println(count);
                            response.addCookie(new Cookie("articleCount", count.toString()));
                            break;
                    }
                    break;
                }
            }
        }
        articleService = new ArticleService();
        article = articleService.findArticleById(count);
        System.out.println(count);

        try {
            request.getSession().setAttribute("article_info", article);

//            response.sendRedirect("/index.jsp");

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getArticleAdd(HttpServletRequest request, HttpServletResponse response) {
        String articleTitle = request.getParameter("articleTitle");
        String articleWriter = request.getParameter("articleWriter");
        String articleContent = request.getParameter("articleContent");
        System.out.println(articleTitle);
        System.out.println(articleWriter);

        Article article = new Article();
        ArticleService articleService = new ArticleService();

        article.setArticleTitle(articleTitle);
        article.setArticleWriter(articleWriter);
        article.setArticleContent(articleContent);

        articleService.addArticle(article);


        try {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
//            out.print("<script language='javascript'>alert('上传成功，跳转到主页。');</script>");
//            out.print("<script>window.location.href='/index.jsp'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
//            try {
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


    }

    public void findArticleByTitle(HttpServletRequest request, HttpServletResponse response) {
        String articleTitle = request.getParameter("articleTitle");

//        System.out.println(articleTitle);

        article = new Mybatis().findArticleByTitle(articleTitle);
//        System.out.println(article.getArticleContent());
        request.getSession().setAttribute("article_info", article);
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
