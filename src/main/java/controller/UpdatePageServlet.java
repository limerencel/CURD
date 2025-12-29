package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/update")
public class UpdatePageServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user id
        Integer id = Integer.parseInt(req.getParameter("id"));

        // select user
        User user = userService.findUserById(id);

        // store user in request scope
        req.setAttribute("selectedUser", user);

        // forward to register.jsp
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        // get user's submitted data
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String birthdayStr = req.getParameter("birthday");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Integer gender = Integer.parseInt(req.getParameter("gender"));
        String picPath = req.getParameter("pic");

        System.out.println("IMG Path is: " + picPath);

        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setGender(gender);
        user.setEmail(email);
        user.setId(Integer.parseInt(idStr));
        user.setPic(picPath);

        if(birthdayStr != null && !birthdayStr.isEmpty()) {
            LocalDate birthday = LocalDate.parse(birthdayStr);
            user.setBirthday(birthday);
        }

        // update database
        userService.updateUser(user);

        // redirect to list page
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
