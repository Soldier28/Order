package com.vince.orders.servlet;

import com.vince.orders.dao.factory.DAOFactory;
import com.vince.orders.vo.Order;
import com.vince.orders.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet" , value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if ("list".equals(method)) {
            list(request,response);
        } else if ("add".equals(method)) {
            add(request,response);
        } else if ("search".equals(method)) {
            try {
                search(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(method)) {
            delete(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * 查询订单列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/view/share/login/login.jsp");
        } else {
            List<Order> orderList = null;
            try {
                orderList = (List<Order>) DAOFactory.getOrderDAO().findByBuyer(user.getUserName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("orderList",orderList);
            //请求转发,是服务器端跳转
            request.getRequestDispatcher("/view/orders/orderManager/list.jsp").forward(request,response);
        }
    }

    /**
     * 添加订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从客户端获取请求数据
        String orderCode = request.getParameter("orderCode");
        String deliveryMethod = request.getParameter("deliveryMethod");

        //封装请求数据
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setDeliveryMethod(deliveryMethod);
        User user = (User) request.getSession().getAttribute("user");
        order.setBuyer(user.getUserName());
        order.setSum(0.0);
        order.setCreatedDate(new Date());
        order.setLastmodifyDate(new Date());
        try {
            DAOFactory.getOrderDAO().save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新定向,是客户端跳转
        response.sendRedirect(request.getContextPath()+"/OrderServlet?method=list");
    }

    /**
     * 查询，根据订单编号、订单金额和配送方式
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void search(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //从search.jsp过来的值
        User user = (User) request.getSession().getAttribute("user");
        String buyer = user.getUserName();
        String orderCode = request.getParameter("orderCode");
        String deliveryMethod = request.getParameter("deliveryMethod");
        Double sum = null;
        if (sum != null && !"".equals(sum)) {
            sum = Double.parseDouble(request.getParameter("sum"));
        }
        List<Order> orderList = null;
        try {
            orderList = DAOFactory.getOrderDAO().search(buyer,orderCode,sum,deliveryMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("orderList",orderList);
        //转发，能传值
        request.getRequestDispatcher("/view/orders/orderManager/list.jsp").forward(request,response);
    }

    /**
     * 删除操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        try {
            DAOFactory.getOrderDAO().deleteById(Integer.valueOf(orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //转发
        request.getRequestDispatcher("/OrderServlet?method=list").forward(request,response);
    }
}
