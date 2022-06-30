package com.gd.manage.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RolePermissionPO implements Serializable {
    private String id;

    private String roleId;

    private String permissionId;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;



}