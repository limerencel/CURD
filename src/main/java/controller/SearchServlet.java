package controller;

import com.alibaba.fastjson.JSON;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String keyword = req.getParameter("keyword");
        List<User> users;

        if (keyword != null && !keyword.trim().isEmpty()) {
            users = userService.searchUserByKeyword(keyword);
        } else {
            users = userService.findAllUsers();
        }

        String usersJson = JSON.toJSONStringWithDateFormat(users, "yyyy-MM-dd");

        resp.getWriter().write(usersJson);
    }
}
