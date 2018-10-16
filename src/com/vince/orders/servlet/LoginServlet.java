package com.vince.orders.servlet;

import com.vince.orders.dao.factory.DAOFactory;
import com.vince.orders.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = DAOFactory.getUserDAO().login(username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user == null) {
            //返回登录页面
            response.sendRedirect(request.getContextPath()+"/view/share/error/loginerror.jsp");
        }else {
            request.getSession().setAttribute("user", user);
//            response.sendRedirect("/OrderServlet?method=list");
            request.getRequestDispatcher("OrderServlet?method=list").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
