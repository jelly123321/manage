package com.gd.manage.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserUpdateDTO {

    @NotNull(message = "ID不能为NULL")
    @NotBlank(message = "ID不能为空")
    private String id;

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