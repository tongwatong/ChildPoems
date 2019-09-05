package service;

import dao.UserDao;
import model.User;
import servlet.UserServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/8/22.
 */
public class UserService {
    User user = null;

    public User findUserByLoginNum(String loginNum) {
        UserDao userDao = new UserDao();
        user = userDao.findUserByLoginNum(loginNum);

        return user;
    }
    public void addUser(User user) {

        UserDao userDao = new UserDao();
        userDao.addUser(user);
    }
}
