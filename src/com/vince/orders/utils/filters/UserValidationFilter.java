package com.vince.orders.utils.filters;

import com.vince.orders.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录验证的过滤器
 */
@javax.servlet.annotation.WebFilter(filterName = "UserValidationFilter")
public class UserValidationFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain)
            throws javax.servlet.ServletException, IOException {

        //用户登录验证
        //这样子转是因为父接口拿不到session，子接口可以
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = (User) request.getSession().getAttribute("#user#");

        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/view/share/login/login.jsp");
        } else {
            //执行下一个过滤器
            chain.doFilter(req, resp);
        }

    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
