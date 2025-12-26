package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get login credentials
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        //TODO check if checkCode is correct

        //TODO check user's login credentials: user if exists, password validation

        //TODO if everything is correct, redirect user to /list page

    }
}
