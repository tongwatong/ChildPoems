package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Administrator
 * @date 2019/8/22
 */
@WebServlet("/login_servlet")
public class LoginServlet extends HttpServlet {
    User user = null;
    UserService userService = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("dopost success");

        String loginNum = request.getParameter("loginNum");
        String loginPwd = request.getParameter("loginPwd");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println(loginNum);
        userService = new UserService();
        user = userService.findUserByLoginNum(loginNum);
         if (user == null) {
//             System.out.println("用户不存在");

             request.setAttribute("errMsg", "用户不存在");
//             response.sendRedirect("/login.jsp");
             request.getRequestDispatcher("/login.jsp").forward(request, response);
         }else {
             if (!loginPwd.equals(user.getUserPwd())) {
//                 System.out.println("密码不正确");

                 request.setAttribute("errMsg", "密码不正确");
//                 response.sendRedirect("/login.jsp");
                 request.getRequestDispatcher("/login.jsp").forward(request, response);
             }else {
//                 System.out.println("登陆成功");
                 if (rememberMe != null && rememberMe.equals("yes")) {
                     Cookie userNumCookie = new Cookie("userNum", loginNum);
                     userNumCookie.setMaxAge(60*60*24);
                     Cookie userPwdCookie = new Cookie("userPwd", loginPwd);
                     userNumCookie.setMaxAge(60*60*24);
                     response.addCookie(userNumCookie);
                     response.addCookie(userPwdCookie);
                 }

                 request.getSession().setAttribute("user_info", user);
//                 response.sendRedirect("/article_servlet");
                 request.getRequestDispatcher("/article_servlet").forward(request, response);
             }
         }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("doget success");

        this.doPost(request, response);
    }
}
