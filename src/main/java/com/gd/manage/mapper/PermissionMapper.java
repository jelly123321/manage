package com.gd.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.manage.entity.po.PermissionPO;
import com.gd.manage.entity.query.PermissionQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<PermissionPO> {
    int deleteByPrimaryKey(String id);

    int insert(PermissionPO record);

    int insertSelective(PermissionPO record);

    PermissionPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PermissionPO record);

    int updateByPrimaryKeyWithBLOBs(PermissionPO record);

    int updateByPrimaryKey(PermissionPO record);

    List<PermissionPO> query(PermissionQuery query);

    List<PermissionPO> queryByUserId(String userId);
}