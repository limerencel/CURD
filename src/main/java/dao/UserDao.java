package dao;

import entity.City;
import entity.User;
import utils.DataSourceUtils;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    public static User findUserByUsername(String un) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
                return new User(id,un,password,name,pic,email,gender,birthday,address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static User findUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
                return new User(id,username,password,name,pic,email,gender,birthday,address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
