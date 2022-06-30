package com.gd.manage.entity.query;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RoleQuery {

    private String name;

    private String code;

    private String remarks;

    private String roleType;

    private int page;

    private int size;

}