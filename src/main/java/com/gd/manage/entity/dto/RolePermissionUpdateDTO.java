package com.gd.manage.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RolePermissionUpdateDTO {

    @NotNull(message = "ID不能为NULL")
    @NotBlank(message = "ID不能为空")
    private String id;

    private String roleId;

    private String permissionId;

    private String createId;

    private Date createTime;


}