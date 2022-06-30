package com.gd.manage.service;

import com.gd.manage.entity.dto.PermissionAddDTO;
import com.gd.manage.entity.dto.PermissionUpdateDTO;
import com.gd.manage.entity.po.PermissionPO;
import com.gd.manage.entity.query.PermissionQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermissionService {

    /**
     * 权限新增
     * @param record
     * @return
     */
    PermissionPO add(PermissionAddDTO record);

    /**
     * 权限修改
     * @param record
     * @return
     */
    PermissionPO update(PermissionUpdateDTO record);


    /**
     * 权限删除
     * @param ids
     * @return
     */
    void delete(String[] ids);

    /**
     * 权限详情
     * @param id
     * @return
     */
    PermissionPO get(String id);


    /**
     * 根据人员id权限列表查询
     * @param userId
     * @return
     */
    List<PermissionPO> queryByUserId(String userId);

    /**
     * 权限列表查询
     * @param query
     * @return
     */
    PageInfo<PermissionPO> query(PermissionQuery query);
}
