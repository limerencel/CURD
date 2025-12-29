package service;

import dao.UserDao;
import entity.User;

import java.util.List;

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

    public List<User> findAllUsers() {
        return UserDao.findAllUsers();
    }

    public List<User> searchUserByKeyword(String keyword) {
        return UserDao.searchUserByKeyword(keyword);
    }

    public void deleteUserById(Integer id) {
        UserDao.deleteUserById(id);
    }

    public User findUserById(Integer id) {
        return UserDao.findUserById(id);
    }

    public void updateUser(User user) {
        UserDao.updateUser(user);
    }
}
