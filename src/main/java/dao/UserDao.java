package dao;

import model.User;
import service.UserService;
import servlet.UserServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2019/8/22.
 */
public class UserDao {
    User userReturn = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    Connection connection = null;

    public User findUserByLoginNum(String loginNum) {

        try {
            connection = Utils.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from user WHERE user_num = ?");
            preparedStatement.setString(1, loginNum);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                resultSet.beforeFirst();
                userReturn = new User();
                while (resultSet.next()) {
                    userReturn.setId(resultSet.getInt("id"));
                    userReturn.setUserNum(resultSet.getString("user_num"));
                    userReturn.setUserName(resultSet.getString("user_name"));
                    userReturn.setUserPwd(resultSet.getString("user_pwd"));
                    userReturn.setCreateDate(resultSet.getDate("create_date"));
                    userReturn.setGender(resultSet.getString("gender"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.close(resultSet, preparedStatement, connection);
        }

        return userReturn;

    }
    public void addUser(User user) {

        try {
            connection = Utils.getConnection();
            String sql = "INSERT INTO user(user_num, user_name, user_pwd, gender) " +
                    "VALUES (?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserNum());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserPwd());
            preparedStatement.setString(4, user.getGender());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.close(preparedStatement, connection);
        }

    }
}
