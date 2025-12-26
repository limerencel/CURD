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
}
