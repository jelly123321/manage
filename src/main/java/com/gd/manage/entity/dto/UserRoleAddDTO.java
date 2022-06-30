package com.gd.manage.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserRoleAddDTO {

    private String userId;

    private String roleId;

    private String createId;

    private Date createTime;


}