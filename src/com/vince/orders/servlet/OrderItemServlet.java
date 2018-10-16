package com.vince.orders.servlet;

import com.vince.orders.dao.factory.DAOFactory;
import com.vince.orders.vo.DiscountOrderItem;
import com.vince.orders.vo.Order;
import com.vince.orders.vo.OrderItem;
import com.vince.orders.vo.PromotionOrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderItemServlet" ,  value ="/OrderItemServlet" )
public class OrderItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if ("item".equals(method)) {
            item(request,response);
        } else if ("add".equals(method)) {
            add(request,response);
        } else if ("delete".equals(method)) {
            delete(request,response);
        } else if ("view".equals(method)) {
            view(request,response);
        } else if ("update".equals(method)) {
            update(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //查看订单详情
    private void item(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        try {
            Order order = DAOFactory.getOrderDAO().findById(Integer.valueOf(orderId));
            List<OrderItem> orderItems = DAOFactory.getOrderItemDAO().findByOrderId(Integer.valueOf(orderId));
            order.setOrderItems(orderItems);
            request.setAttribute("order",order);
            //这样子跳转可以用request.setAttribute来传值
            request.getRequestDispatcher("/view/orders/orderItemManager/list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //添加订单明细
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String discountType = request.getParameter("discountType");
        String unitNum = request.getParameter("unitNum");
        String unitPrice = request.getParameter("unitPrice");
        String sum = request.getParameter("sum");
        String discount = request.getParameter("discount");
        String promotionPrice = request.getParameter("promotionPrice");
        String promotionNum = request.getParameter("promotionNum");
        String orderId = request.getParameter("orderId");

        OrderItem orderItem = null;
        //有折扣
        if ("2".equals(discountType)) {
            orderItem = new DiscountOrderItem();
            DiscountOrderItem dItem = (DiscountOrderItem) orderItem;
            dItem.setOrderId(Integer.valueOf(orderId));
            dItem.setName(name);
            dItem.setDiscountType(Integer.valueOf(discountType));
            dItem.setSum(Double.valueOf(sum));
            dItem.setUnitNum(Integer.valueOf(unitNum));
            dItem.setUnitPrice(Double.valueOf(unitPrice));
            dItem.setDiscount(Double.valueOf(discount));
        }
        //促销
        else if ("3".equals(discountType)) {
            orderItem = new PromotionOrderItem();
            PromotionOrderItem pItem = (PromotionOrderItem) orderItem;
            pItem.setOrderId(Integer.valueOf(orderId));
            pItem.setName(name);
            pItem.setDiscountType(Integer.valueOf(discountType));
            pItem.setSum(Double.valueOf(sum));
            pItem.setUnitNum(Integer.valueOf(unitNum));
            pItem.setUnitPrice(Double.valueOf(unitPrice));
            pItem.setPromotionNum(Integer.valueOf(promotionNum));
            pItem.setPromotionPrice(Double.valueOf(promotionPrice));
        }
        //无折扣
        else{
            orderItem = new OrderItem();
            orderItem.setOrderId(Integer.valueOf(orderId));
            orderItem.setName(name);
            orderItem.setDiscountType(Integer.valueOf(discountType));
            orderItem.setSum(Double.valueOf(sum));
            orderItem.setUnitNum(Integer.valueOf(unitNum));
            orderItem.setUnitPrice(Double.valueOf(unitPrice));
        }

        try {
            //添加订单明细
            DAOFactory.getOrderItemDAO().save(orderItem);
            //更新订单总金额
            DAOFactory.getOrderDAO().updateSum(Integer.valueOf(orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/OrderItemServlet?method=item").forward(request,response);
    }

    //删除订单明细
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        String orderId = request.getParameter("orderId");
        try {
            DAOFactory.getOrderItemDAO().delete(Integer.valueOf(itemId));
            DAOFactory.getOrderDAO().updateSum(Integer.valueOf(orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/OrderItemServlet?method=item").forward(request,response);

    }

    //查看订单明细
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        OrderItem orderItem;
        try {
            orderItem = DAOFactory.getOrderItemDAO().findByItemId(Integer.valueOf(itemId));
            request.setAttribute("orderItem",orderItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/orders/orderItemManager/orderItem.jsp").forward(request,response);
    }

    //更新订单明细
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        String name = request.getParameter("name");
        String discountType = request.getParameter("discountType");
        String unitNum = request.getParameter("unitNum");
        String unitPrice = request.getParameter("unitPrice");
        String sum = request.getParameter("sum");
        String discount = request.getParameter("discount");
        String promotionPrice = request.getParameter("promotionPrice");
        String promotionNum = request.getParameter("promotionNum");
        String orderId = request.getParameter("orderId");

        OrderItem orderItem = null;
        if ("2".equals(discountType)) {  //有折扣
            orderItem = new DiscountOrderItem();
            DiscountOrderItem dItem = (DiscountOrderItem) orderItem;
            dItem.setItemId(Integer.valueOf(itemId));
            dItem.setOrderId(Integer.valueOf(orderId));
            dItem.setName(name);
            dItem.setDiscountType(Integer.valueOf(discountType));
            dItem.setUnitNum(Integer.valueOf(unitNum));
            dItem.setUnitPrice(Double.valueOf(unitPrice));
            dItem.setSum(Double.valueOf(sum));
            dItem.setDiscount(Double.valueOf(discount));
        } else if ("3".equals(discountType)) {  //促销
            orderItem = new PromotionOrderItem();
            PromotionOrderItem pItem = (PromotionOrderItem) orderItem;
            pItem.setItemId(Integer.valueOf(itemId));
            pItem.setOrderId(Integer.valueOf(orderId));
            pItem.setName(name);
            pItem.setDiscountType(Integer.valueOf(discountType));
            pItem.setUnitNum(Integer.valueOf(unitNum));
            pItem.setUnitPrice(Double.valueOf(unitPrice));
            pItem.setSum(Double.valueOf(sum));
            pItem.setPromotionNum(Integer.valueOf(promotionNum));
            pItem.setPromotionPrice(Double.valueOf(promotionPrice));
        } else {  //无折扣
            orderItem = new OrderItem();
            orderItem.setItemId(Integer.valueOf(itemId));
            orderItem.setOrderId(Integer.valueOf(orderId));
            orderItem.setName(name);
            orderItem.setDiscountType(Integer.valueOf(discountType));
            orderItem.setUnitNum(Integer.valueOf(unitNum));
            orderItem.setUnitPrice(Double.valueOf(unitPrice));
            orderItem.setSum(Double.valueOf(sum));
        }
        try {
            //修改 更新订单明细
            DAOFactory.getOrderItemDAO().update(orderItem);
            //修改总金额
            DAOFactory.getOrderDAO().updateSum(Integer.valueOf(orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/OrderItemServlet?method=item").forward(request,response);
    }
}
