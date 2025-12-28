package service;

import dao.userDao;
import entity.User;

public class userService {
    public static User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public static User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
