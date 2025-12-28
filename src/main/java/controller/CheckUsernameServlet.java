package controller;

import entity.User;
import service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get username to validate
        String username = req.getParameter("username");

        //find user by username
        User user = userService.findUserByUsername(username);

        if (user == null) {
            resp.getWriter().write("0"); // username doesn't exist
        } else {
            resp.getWriter().write("1");

        }
    }
}
