package com.gd.manage.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PermissionAddDTO {


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