package controller;

import com.alibaba.fastjson.JSON;
import entity.Province;
import service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/queryProvince")
public class QueryProvinceServlet extends HttpServlet {
    private ProvinceService provinceService = new ProvinceService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get province list
        List<Province> provinceList =  provinceService.getProvinceList();

        // set response type as json
        resp.setContentType("application/json;charset=utf-8");

        // convert to JSON
        String provinceJsonList = JSON.toJSONString(provinceList);

        // return data back
        resp.getWriter().write(provinceJsonList);

    }
}
