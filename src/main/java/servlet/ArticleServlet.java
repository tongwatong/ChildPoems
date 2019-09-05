package servlet;

import model.Article;
import model.CurrentCount;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/8/25.
 */
@WebServlet("/article_servlet")
public class ArticleServlet extends HttpServlet {
    Article article = null;
    ArticleService articleService = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

//        System.out.println("article_servlet success");
        this.getArticleList(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        this.doPost(request, response);
    }

    public void getArticleList(HttpServletRequest request, HttpServletResponse response) {
        Integer count = 1;
        String action = request.getParameter("action");
        if (action != null) {
            Cookie articleCount = new Cookie("articleCount", count.toString());
            response.addCookie(articleCount);
            Cookie[] cookies = request.getCookies();
            for (Cookie foo : cookies) {
                if ("articleCount".equals(foo.getName())) {
                    count = Integer.parseInt(foo.getValue());
                    switch (action) {
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

        if (article != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String foo : article.getArticleContent().split("\n")) {
                stringBuilder.append("<p>");
                stringBuilder.append(foo + "<br />" + "</p>");
            }
            article.setArticleContent(String.valueOf(stringBuilder));
        }

        try {
            request.getSession().setAttribute("article_info", article);

//            response.sendRedirect("/index.jsp");

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
