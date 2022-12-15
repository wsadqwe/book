package com.bb.booksproject.filter;

import com.bb.booksproject.util.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * //拦截所有的请求，判断当前是否为登录状态，如果是就访问，如果不是就跳转到登录界面
 */
@WebFilter(filterName = "authenticationFilter",urlPatterns = "/*")
public class AuthenticationFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求的地址
        String requestURI = request.getRequestURI();

        if(requestURI.contains("login.jsp") || requestURI.contains("loginServlet")){
            //访问登录页面直接放过
            filterChain.doFilter(servletRequest,servletResponse);

        }else{
            //其他情况我们就需要判断当前是否是登录的状态
            HttpSession session = request.getSession();
            Object attribute = session.getAttribute(Constant.SESSION_LOGIN_USER);

            if(attribute != null){
                //说明我们已经登录过了，直接放过
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                //说明没有登录，需要跳转到登录页面
                session.setAttribute("msg","请先登录");
                response.sendRedirect("/login.jsp");
            }
        }

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
