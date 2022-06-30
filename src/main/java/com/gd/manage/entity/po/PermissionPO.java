package com.gd.manage.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PermissionPO implements Serializable {

    private String id;

    private String permissionType;

    private String parentId;

    private String name;

    private String code;

    private String remarks;

    private BigDecimal sort;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private String url;

}