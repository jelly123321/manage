package com.gd.manage.entity.query;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PermissionQuery {


    private String permissionType;

    private String parentId;

    private String name;

    private String code;

    private String remarks;

    private String url;

    private int page;

    private int size;

}