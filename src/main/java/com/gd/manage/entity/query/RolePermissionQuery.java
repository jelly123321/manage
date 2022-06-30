package com.gd.manage.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionQuery {

    private String roleId;

    private String permissionId;

    private int page;

    private int size;

}