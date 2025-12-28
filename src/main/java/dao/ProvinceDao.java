package dao;

import entity.Province;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDao {

    public static List<Province> getProvinceList() {

        String sql = "SELECT * FROM province";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            List<Province> province = new ArrayList<>();
            while (rs.next()) {
                Province p = new Province();
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                province.add(p);
            }
            return province;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNameByCode(String provinceId) {
        String sql = "SELECT * FROM province WHERE code = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, provinceId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
