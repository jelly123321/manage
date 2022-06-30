package com.gd.manage.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPO implements Serializable {
    private String id;

    private String userName;

    private String password;

    private String phone;

    private String salt;

    private String mail;

    private Date loginTime;

    private String forbidden;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;


}