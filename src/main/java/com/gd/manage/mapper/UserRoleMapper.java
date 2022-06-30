package com.gd.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.manage.entity.po.UserRolePO;
import com.gd.manage.entity.query.UserRoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper extends BaseMapper<UserRolePO> {
    int deleteByPrimaryKey(String id);

    int deleteByUserId(String userId);

    int insert(UserRolePO record);

    int insertSelective(UserRolePO record);

    UserRolePO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRolePO record);

    int updateByPrimaryKey(UserRolePO record);

    List<UserRolePO> query(UserRoleQuery query);
}