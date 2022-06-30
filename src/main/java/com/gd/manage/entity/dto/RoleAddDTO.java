package com.gd.manage.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RoleAddDTO {

    private String name;

    private String code;

    private String remarks;

    private BigDecimal sort;

    private String roleType;

    private String createId;

    private String createName;

    private Date createTime;

    private String updateId;

    private String updateName;

    private Date updateTime;


}