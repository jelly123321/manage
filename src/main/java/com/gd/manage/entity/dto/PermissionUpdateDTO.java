package com.gd.manage.entity.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PermissionUpdateDTO {

    @NotNull(message = "ID不能为NULL")
    @NotBlank(message = "ID不能为空")
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