package com.vince.orders.servlet;

import com.vince.orders.dao.factory.DAOFactory;
import com.vince.orders.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet" ,value = "/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("update_pass".equals(method)) {
            update_pass(request,response);
        } else if ("register".equals(method)) {
            register(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //用户注册
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        try {
            DAOFactory.getUserDAO().register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("../../view/share/login/login.jsp").forward(request,response);
    }

    //修改密码
    private void update_pass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //如果用户为空
        if(user == null) {
            //返回登录错误页面
            response.sendRedirect(request.getContextPath()+"/view/share/error/loginerror.jsp");
        }else {
            String old_pass = request.getParameter("old_pass");
            String new_pass = request.getParameter("new_pass");
            //如果密码不匹配
            if (! old_pass.equals(user.getPassWord())) {
                out.println("<script>");
                out.println("alert('旧密码不正确！')");
                out.println("window.location.href='../../../view/orders/userManager/set_pass.jsp'");
                out.println("</script>");
            } else {
                user.setPassWord(new_pass);
                try {
                    DAOFactory.getUserDAO().update(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out.println("<script>");
                out.println("alert('密码修改成功！')");
                out.println("window.location.href='../../../view/orders/orderManager/list.jsp'");
                out.println("</script>");
            }
        }
    }
}
