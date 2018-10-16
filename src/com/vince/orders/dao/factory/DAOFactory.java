package com.vince.orders.dao.factory;

import com.vince.orders.dao.IOrderDAO;
import com.vince.orders.dao.IOrderItemDAO;
import com.vince.orders.dao.IUserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 兵哥哥
 * @create 2018-10-03 8:19
 * @desc DAO工厂类
 **/
public class DAOFactory {
    //属性文件
    private static Properties config = null;
    static {
        //通过流的方式读取配置文件，这样子方便维护
        InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/vince/orders/dao/factory/dao.properties");
        config = new Properties();
        try {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回订单DAO对象
     */
    public static IOrderDAO getOrderDAO() throws Exception {
        //读取配置文件中的一个key，从而获取相应的value
        String instance = config.getProperty("com.vince.orders.dao.IOrderDAO");
        IOrderDAO orderDAO = null;
        try {
            //发射获得impl
            orderDAO = (IOrderDAO) Class.forName(instance).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDAO;

    }

    /**
     * 返回用户DAO对象
     * @return
     * @throws Exception
     */
    public static IUserDAO getUserDAO() throws Exception {
        String instance = config.getProperty("com.vince.orders.dao.IUserDAO");
        IUserDAO userDAO = null;
        try {
            //发射
            userDAO = (IUserDAO) Class.forName(instance).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDAO;
    }

    /**
     * 返回订单明细DAO对象
     * @return
     * @throws Exception
     */
    public static IOrderItemDAO getOrderItemDAO() throws Exception {
        String instance = config.getProperty("com.vince.orders.dao.IOrderItemDAO");
        IOrderItemDAO orderItemDAO = null;
        try {
            //发射
            orderItemDAO = (IOrderItemDAO) Class.forName(instance).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemDAO;
    }
}
