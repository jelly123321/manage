package com.gd.manage.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserAddDTO {

    private String userName;

    private String password;

    private String phone;

    private String salt;

    private String mail;

    private Date loginTime;

    private String forbidden;

    private Integer sort;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;


}