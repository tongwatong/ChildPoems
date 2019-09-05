package servlet;

import dao.UserDao;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2019/8/23.
 */
@WebServlet("/user_servlet")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        System.out.println("dopost success");

        this.getUserAdd(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("doget success");
        this.doPost(request, response);
    }

    public void getUserAdd(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("addUser success");

        String userNum = request.getParameter("userNum");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String userPwd = request.getParameter("userPwd");
        String reUserPwd = request.getParameter("reUserPwd");

        User user = null;
        if (userNum != "" && userName != "" && gender != ""
                && userPwd != "" && reUserPwd != "") {
            UserService userService = new UserService();
            if (userService.findUserByLoginNum(userNum) == null) {
                if (userPwd.equals(reUserPwd)) {
                    user = new User();

                    user.setUserNum(userNum);
                    user.setUserName(userName);
                    user.setGender(gender);
                    user.setUserPwd(userPwd);

                    userService = new UserService();
                    userService.addUser(user);

                    try {
                        response.setCharacterEncoding("GBK");
                        PrintWriter out = response.getWriter();
                        out.print("<script language='javascript'>alert('注册成功，跳转到登陆页面。');</script>");
                        out.print("<script>window.location.href='/login.jsp'</script>");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        response.setCharacterEncoding("GBK");
                        PrintWriter out = null;
                        out = response.getWriter();
                        out.print("<script language='javascript'>alert('注册失败，请重新注册。');</script>");
                        out.print("<script>window.location.href='/register.jsp'</script>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                try {
                    response.setCharacterEncoding("GBK");
                    PrintWriter out = null;
                    out = response.getWriter();
                    out.print("<script language='javascript'>alert('用户名已存在。');</script>");
                    out.print("<script>window.location.href='/register.jsp'</script>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                response.setCharacterEncoding("GBK");
                PrintWriter out = null;
                out = response.getWriter();
                out.print("<script language='javascript'>alert('注册失败，请重新注册。');</script>");
                out.print("<script>window.location.href='/register.jsp'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
