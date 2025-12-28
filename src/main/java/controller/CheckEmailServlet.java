package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkEmail")
public class CheckEmailServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get email to validate
        String email = req.getParameter("email");
        System.out.println("email: "+email);

        User user = userService.findUserByEmail(email);

        if (user == null) {
            resp.getWriter().write("0"); // user doesn't exist
        } else {
            resp.getWriter().write("1");

        }
    }
}