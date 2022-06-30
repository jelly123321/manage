package com.gd.manage.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RoleUpdateDTO {
    @NotNull(message = "ID不能为NULL")
    @NotBlank(message = "ID不能为空")
    private String id;

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