package servlet;

import service.ArticleAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主要进行行为改变，以及参数的中间传递
 * @author 童啊童
 */
@WebServlet("/search_servlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ArticleAction.action = "article_search";
//        request.getSession().setAttribute("articleTitle", request.getParameter("articleTitle"));
        request.getRequestDispatcher("/article_servlet").forward(request, response);
//        System.out.println(articleName);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doPost(request, response);
    }
}
