package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get all users
        List<User> users = userService.findAllUsers();

        // store users into request scope
        req.setAttribute("users", users);

        // forward user (still in /list but give user /list.jsp content)
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
