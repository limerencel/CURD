package service;

import dao.UserDao;
import entity.User;

public class UserService {
    public User findUserByUsername(String username) {
        return UserDao.findUserByUsername(username);
    }

    public User findUserByEmail(String email) {
        return UserDao.findUserByEmail(email);
    }

    public void register(User user) {
        UserDao.addUser(user);
    }
}
