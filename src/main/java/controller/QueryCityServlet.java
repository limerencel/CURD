package controller;

import com.alibaba.fastjson.JSON;
import entity.City;
import entity.Province;
import service.CityService;
import service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/queryCity")
public class QueryCityServlet extends HttpServlet {
    private CityService cityService = new CityService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get province ID
        String id = req.getParameter("province");

        // return cities back to frontend
        List<City> cities = cityService.findAllCities();

        // Set JSON response type
        resp.setContentType("application/json;charset=utf-8");

        // convert to JSON
        String cityJsonList = JSON.toJSONString(cities);

        // return to frontend
        resp.getWriter().write(cityJsonList);
    }
}
