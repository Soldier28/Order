package com.vince.orders.vo;

import java.util.Date;

/**
 * @author 兵哥哥
 * @create 2018-09-30 10:46
 * @desc 使用者
 **/
public class User {

    private Integer UserId;
    private String UserName;
    private String PassWord;
    private Date CreatedDate;
    private Date LastmodifyDate;

    public User() {
        super();
    }

    public User(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    public User(Integer userId, String userName, String passWord, Date createdDate, Date lastmodifyDate) {
        UserId = userId;
        UserName = userName;
        PassWord = passWord;
        CreatedDate = createdDate;
        LastmodifyDate = lastmodifyDate;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getLastmodifyDate() {
        return LastmodifyDate;
    }

    public void setLastmodifyDate(Date lastmodifyDate) {
        LastmodifyDate = lastmodifyDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                ", CreatedDate=" + CreatedDate +
                ", LastmodifyDate=" + LastmodifyDate +
                '}';
    }
}
