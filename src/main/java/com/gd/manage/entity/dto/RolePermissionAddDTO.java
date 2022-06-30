package com.gd.manage.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RolePermissionAddDTO {

    private String roleId;

    private String permissionId;

    private String createId;

    private Date createTime;


}