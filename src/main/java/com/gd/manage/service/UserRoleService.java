package com.gd.manage.service;

import com.gd.manage.entity.dto.UserRoleAddDTO;
import com.gd.manage.entity.dto.UserRoleUpdateDTO;
import com.gd.manage.entity.po.UserRolePO;
import com.gd.manage.entity.query.UserRoleQuery;
import com.github.pagehelper.PageInfo;

public interface UserRoleService {
    /**
     * 人员角色新增
     * @param record
     * @return
     */
    UserRolePO add(UserRoleAddDTO record);

    /**
     * 人员角色修改
     * @param record
     * @return
     */
    UserRolePO update(UserRoleUpdateDTO record);


    /**
     * 人员角色删除
     * @param ids
     * @return
     */
    void delete(String[] ids);

    /**
     * 人员角色详情
     * @param id
     * @return
     */
    UserRolePO get(String id);


    /**
     * 人员角色列表查询
     * @param query
     * @return
     */
    PageInfo<UserRolePO> query(UserRoleQuery query);
}
