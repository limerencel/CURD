package dao;

import entity.City;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {
    public static List<City> findAreaByCityId(String cityCode) {
        String sql = "SELECT * FROM area WHERE citycode = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cityCode);
            ResultSet rs = ps.executeQuery();

            List<City> area = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                area.add(new City(id, code, name, cityCode));
            }
            return area;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNameByCode(String areaId) {
        String sql = "SELECT * FROM area WHERE code = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, areaId);
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
