package com.gd.manage.service;

import com.gd.manage.entity.dto.RoleAddDTO;
import com.gd.manage.entity.dto.RoleUpdateDTO;
import com.gd.manage.entity.po.RolePO;
import com.gd.manage.entity.query.RoleQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    /**
     * 角色新增
     * @param record
     * @return
     */
    RolePO add(RoleAddDTO record);

    /**
     * 角色修改
     * @param record
     * @return
     */
    RolePO update(RoleUpdateDTO record);


    /**
     * 角色删除
     * @param ids
     * @return
     */
    void delete(String[] ids);

    /**
     * 角色详情
     * @param id
     * @return
     */
    RolePO get(String id);


    /**
     * 根据人员id查询角色详情
     * @param userId
     * @return
     */
    List<RolePO> getByUserId(String userId);


    /**
     * 角色列表查询
     * @param query
     * @return
     */
    PageInfo<RolePO> query(RoleQuery query);
}
