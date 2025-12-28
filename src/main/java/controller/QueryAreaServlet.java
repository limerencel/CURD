package controller;

import com.alibaba.fastjson.JSON;
import entity.City;
import entity.Province;
import service.AreaService;
import service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/queryArea")
public class QueryAreaServlet extends HttpServlet {
    private AreaService areaService = new AreaService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get city ID
        String id = req.getParameter("cityCode");

        // return area list
        List<City> area = areaService.findAreaByCityId(id);

        // set response type as JSON
        resp.setContentType("application/json;charset=utf-8");

        // convert to JSON
        String provinceJsonList = JSON.toJSONString(area);

        // return data back
        resp.getWriter().write(provinceJsonList);

    }
}
