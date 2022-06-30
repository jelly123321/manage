package com.gd.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.manage.entity.po.RolePO;
import com.gd.manage.entity.po.RolePermissionPO;
import com.gd.manage.entity.query.RoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RolePO> {
    int deleteByPrimaryKey(String id);

    int insert(RolePO record);

    int insertSelective(RolePO record);

    RolePO selectByPrimaryKey(String id);

    List<RolePO> selectByUserId(String userId);

    int updateByPrimaryKeySelective(RolePO record);

    int updateByPrimaryKey(RolePO record);

    List<RolePO> query(RoleQuery query);
}