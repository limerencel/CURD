package filter;

import entity.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lscl
 * @version 1.0
 * @date 2021/1/30 21:24
 * @intro: 用户登录权限过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    private UserService userService = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取 URI
        String requestURI = request.getRequestURI();

        // 2. 白名单检查 (建议使用 contains 判断，防止项目路径 ContextPath 导致 equals 匹配失败)
        if (requestURI.contains("/login.jsp") ||
                requestURI.contains("/register.jsp") ||
                requestURI.contains("/css/") ||
                requestURI.contains("/js/") ||
                requestURI.contains("/images/") ||
                requestURI.contains("/login") ||      // 登录 Servlet
                requestURI.contains("/checkCode")     // 验证码等
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 检查 Session (第一道防线)
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4. 检查 Cookie (第二道防线：自动登录)
        Cookie[] cookies = request.getCookies();
        String cookieUserId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    cookieUserId = cookie.getValue();
                    break;
                }
            }
        }

        // 如果找到了 userId cookie，尝试去数据库查人
        if (cookieUserId != null) {
            try {
                User user = userService.findUserById(Integer.parseInt(cookieUserId));
                if (user != null) {
                    // 自动登录成功：存入 Session
                    session.setAttribute("loginUser", user);
                    // 放行
                    filterChain.doFilter(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 防止 Cookie 被篡改导致转换数字报错
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    @Override
    public void destroy() {}
}