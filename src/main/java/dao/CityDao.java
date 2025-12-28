package dao;

import entity.City;
import entity.Province;
import utils.DataSourceUtils;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    public static List<City> findAllCities() {
        String sql = "SELECT * FROM city";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            List<City> cities = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String provinceCode = rs.getString("provinceCode");
                cities.add(new City(id, code, name, provinceCode));
            }
            return cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<City> findCitiesByProvinceId(String provinceCode) {
        String sql = "SELECT * FROM city WHERE provincecode = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, provinceCode);
            ResultSet rs = ps.executeQuery();

            List<City> cities = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                cities.add(new City(id, code, name, provinceCode));
            }
            return cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getNameByCode(String cityId) {
        String sql = "SELECT * FROM city WHERE code = ?";
        try(Connection conn = DataSourceUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cityId);
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
