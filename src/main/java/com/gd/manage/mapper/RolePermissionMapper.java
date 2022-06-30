package com.gd.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.manage.entity.po.RolePermissionPO;
import com.gd.manage.entity.po.UserRolePO;
import com.gd.manage.entity.query.RolePermissionQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermissionPO> {
    int deleteByPrimaryKey(String id);

    int deleteByRoleId(String roleId);

    int deleteByPermissionId(String roleId);

    int insert(RolePermissionPO record);

    int insertSelective(RolePermissionPO record);

    RolePermissionPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermissionPO record);

    int updateByPrimaryKey(RolePermissionPO record);

    List<RolePermissionPO> query(RolePermissionQuery query);
}