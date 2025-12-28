package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthToolBarUI;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set utf-8
        req.setCharacterEncoding("utf-8");

        // get login credentials
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        // check if checkCode is correct
        HttpSession session = req.getSession();
        // getAttribute will return an Object,
        String safeCode = (String) session.getAttribute("sessionCode");

        // find user by username
        User user = userService.findUserByUsername(username);

        // 返回 JSON 格式
        if (!checkCode.equals(safeCode)) {
            resp.getWriter().write("{\"success\": false, \"message\": \"safe code error\"}");
        } else if (user == null || !password.equals(user.getPassword())) {
            resp.getWriter().write("{\"success\": false, \"message\": \"username or password is wrong\"}");
        } else {
            session.setAttribute("name", user.getName());
            resp.getWriter().write("{\"success\": true, \"message\": \"login successfully\"}");
        }

    }
}
