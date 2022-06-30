package com.gd.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.manage.entity.po.UserPO;
import com.gd.manage.entity.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserPO> {
    int deleteByPrimaryKey(String id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);

    UserPO selectByUserName(String userName);

    List<UserPO> query(UserQuery query);
}