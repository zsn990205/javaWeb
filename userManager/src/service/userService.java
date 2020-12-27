package service;

import dao.UserDao;
import entity.User;

/*

增强代码的可扩展性
写一个注册页面
 */
public class userService {
    public User login(User userLogin) {
        UserDao userDao = new UserDao();
        User user = userDao.login(userLogin);
        return user;
    }

}
