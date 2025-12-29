package dao;

import entity.User;
import utils.DataSourceUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static User findUserByUsername(String un) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, un);
            ResultSet rs = ps.executeQuery();

            User user = new User();
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String pic = rs.getString("pic");
                String email = rs.getString("email");
                Integer gender = rs.getInt("gender");
                LocalDate birthday =
                        rs.getTimestamp("birthday")
                                .toLocalDateTime()
                                .toLocalDate();
                String address = rs.getString("address");
                return new User(id, un, password, name, pic, email, gender, birthday, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static User findUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            User user = new User();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String pic = rs.getString("pic");
                Integer gender = rs.getInt("gender");
                LocalDate birthday =
                        rs.getTimestamp("birthday")
                                .toLocalDateTime()
                                .toLocalDate();
                String address = rs.getString("address");
                return new User(id, username, password, name, pic, email, gender, birthday, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void addUser(User user) {
        String sql = "INSERT INTO user (username, password, name, pic, email, gender, birthday, address) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPic());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getGender());
            ps.setDate(7, java.sql.Date.valueOf(user.getBirthday()));
            ps.setString(8, user.getAddress());

            ps.executeUpdate(); // return int, the number of affected columns

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> findAllUsers() {
        String sql = "SELECT * FROM user";
        try (Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String pic = rs.getString("pic");
                String email = rs.getString("email");
                Integer gender = rs.getInt("gender");
                LocalDate birthday =
                        rs.getTimestamp("birthday")
                                .toLocalDateTime()
                                .toLocalDate();
                String address = rs.getString("address");
                users.add(new User(id, username, password, name, pic, email, gender, birthday, address));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> searchUserByKeyword(String keyword) {
        String sql = "SELECT * FROM user WHERE username LIKE ?";
        try (Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String pic = rs.getString("pic");
                String email = rs.getString("email");
                Integer gender = rs.getInt("gender");
                LocalDate birthday =
                        rs.getTimestamp("birthday")
                                .toLocalDateTime()
                                .toLocalDate();
                String address = rs.getString("address");
                users.add(new User(id, username, password, name, pic, email, gender, birthday, address));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUserById(Integer id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try (Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate(); // return int, the number of affected columns

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
