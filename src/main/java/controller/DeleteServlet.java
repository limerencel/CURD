package controller;

import com.sun.net.httpserver.HttpServer;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // delete item by ID
        Integer id = Integer.parseInt(req.getParameter("id"));

        userService.deleteUserById(id);

        resp.sendRedirect("/list");
    }
}
