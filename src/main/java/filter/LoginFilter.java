package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // http://localhost:8080/bootstrap/js/bootstrap.min.js  --->  /bootstrap/js/bootstrap.min.js
        String requestURI = request.getRequestURI();

        if (
            // 资源放行
                requestURI.equals("/checkCode.jsp") ||
                        requestURI.equals("/login.jsp") ||
                        requestURI.equals("/register.jsp") ||
                        requestURI.equals("/checkUsername") ||
                        requestURI.equals("/checkEmail") ||
                        requestURI.equals("/login") ||
                        requestURI.equals("/register") ||
                        requestURI.equals("/queryProvince") ||
                        requestURI.equals("/queryCity") ||
                        requestURI.equals("/queryArea") ||
                        requestURI.equals("/favicon.ico") ||

                        // 目录放行
                        requestURI.startsWith("/bootstrap") ||
                        requestURI.startsWith("/font") ||
                        requestURI.startsWith("/images") ||
                        requestURI.startsWith("/js") ||
                        requestURI.startsWith("/upload")
        ) {

            // 如果是访问上述资源直接放行(不管是否登录)
            filterChain.doFilter(request,response);
            return;
        }

        // 代码到这里说明是受限资源

        HttpSession session = request.getSession();

        User loginUser = (User) session.getAttribute("loginUser");

        if(loginUser != null){

            // 虽然是受限资源,但是用户登录了,还是放行
            filterChain.doFilter(request,response);
            return;
        }

        // 是受限资源，而且还没有登录
        response.sendRedirect("/login.jsp");
    }

    @Override
    public void destroy() {

    }
}