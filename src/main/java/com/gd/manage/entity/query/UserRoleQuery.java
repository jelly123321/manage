package com.gd.manage.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleQuery {

    private String userId;

    private String roleId;

    private int page;

    private int size;

}