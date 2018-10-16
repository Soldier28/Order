package com.vince.orders.dao;

import com.vince.orders.vo.User;

/**
 * @author 兵哥哥
 * @create 2018-10-03 10:17
 * @desc 用户的dao层
 **/
public interface IUserDAO {

    //用户注册
    public void register(User user) throws Exception;

    //用户登录
    public User login(String username, String password) throws Exception;

    //修改用户信息
    public void update(User user) throws Exception;
}
