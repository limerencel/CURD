package controller;

import entity.User;
import service.AreaService;
import service.CityService;
import service.ProvinceService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    private ProvinceService provinceService = new ProvinceService();
    private CityService cityService = new CityService();
    private AreaService areaService = new AreaService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");

            User user = new User();

            user.setUsername(req.getParameter("username"));
            user.setName(req.getParameter("name"));
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));
            user.setGender(Integer.parseInt(req.getParameter("gender")));

            //get actual address info by id
            String provinceName = provinceService.getNameByCode(req.getParameter("province"));
            String cityName = cityService.getNameByCode(req.getParameter("city"));
            String areaName = areaService.getNameByCode(req.getParameter("area"));

            String address = provinceName+cityName+areaName;
            System.out.println(address);
            user.setAddress(address);

            LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
            user.setBirthday(birthday);

            // 交给 Service
            userService.register(user);

            // registered, and redirect to login
            resp.sendRedirect(req.getContextPath() + "/login.jsp");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
