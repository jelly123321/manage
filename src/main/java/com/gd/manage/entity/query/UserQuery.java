package com.gd.manage.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class UserQuery {

    private int page;

    private int size;

    private String userName;

    private String phone;

    private String password;

    private String salt;

    private String mail;

    private Date loginTime;

    private String forbidden;

}