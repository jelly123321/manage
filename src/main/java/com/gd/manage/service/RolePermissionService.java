package com.gd.manage.service;

import com.gd.manage.entity.dto.RolePermissionAddDTO;
import com.gd.manage.entity.dto.RolePermissionUpdateDTO;
import com.gd.manage.entity.po.RolePermissionPO;
import com.gd.manage.entity.query.RolePermissionQuery;
import com.github.pagehelper.PageInfo;

public interface RolePermissionService {

    /**
     * 角色权限新增
     * @param record
     * @return
     */
    RolePermissionPO add(RolePermissionAddDTO record);

    /**
     * 角色权限修改
     * @param record
     * @return
     */
    RolePermissionPO update(RolePermissionUpdateDTO record);


    /**
     * 角色权限删除
     * @param ids
     * @return
     */
    void delete(String[] ids);

    /**
     * 角色权限详情
     * @param id
     * @return
     */
    RolePermissionPO get(String id);


    /**
     * 角色权限列表查询
     * @param query
     * @return
     */
    PageInfo<RolePermissionPO> query(RolePermissionQuery query);
}
