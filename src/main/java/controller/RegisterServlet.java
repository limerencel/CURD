package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          try {
              // get user's register credentials
              String username = req.getParameter("username");
              String name = req.getParameter("name");
              String password = req.getParameter("password");
              String email = req.getParameter("email");
              String gender = req.getParameter("gender"); // 0 for man, 1 for woman， 2 for hidden
              String province = req.getParameter("province");
              String city = req.getParameter("city");
              String area = req.getParameter("area");
              String dateStr = req.getParameter("birthday");
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              Date birthday = sdf.parse(dateStr);

              //TODO check if username exists

              //TODO check if email exists

              //TODO let register service to process registering

              //TODO redirect user to login page if everything is fine

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
