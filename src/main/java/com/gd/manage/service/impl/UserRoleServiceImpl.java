package com.gd.manage.service.impl;

import com.gd.manage.entity.dto.UserRoleAddDTO;
import com.gd.manage.entity.dto.UserRoleUpdateDTO;
import com.gd.manage.entity.po.UserRolePO;
import com.gd.manage.entity.query.UserRoleQuery;
import com.gd.manage.mapper.UserRoleMapper;
import com.gd.manage.service.BaseService;
import com.gd.manage.service.UserRoleService;
import com.gd.manage.utils.PageHelperUtil;
import com.gd.manage.utils.UuidUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRoleServiceImpl extends BaseService implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public UserRolePO add(UserRoleAddDTO record) {
        UserRolePO userRolePO = new UserRolePO();
        BeanUtils.copyProperties(record, userRolePO);

        userRolePO.setId(UuidUtils.getUuid32());
        userRolePO.setCreateId(currentUser.getId());
        userRolePO.setCreateTime(new Date());

        userRoleMapper.insertSelective(userRolePO);
        return userRolePO;
    }

    @Override
    public UserRolePO update(UserRoleUpdateDTO record) {
        UserRolePO userRolePO = new UserRolePO();
        BeanUtils.copyProperties(record, userRolePO);

        userRolePO.setUpdateId(currentUser.getId());
        userRolePO.setUpdateTime(new Date());

        userRoleMapper.updateByPrimaryKeySelective(userRolePO);
        return userRolePO;
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            userRoleMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public UserRolePO get(String id) {
        return userRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<UserRolePO> query(UserRoleQuery query) {
        PageHelperUtil.setPage(query.getPage(),query.getSize());
        return null;
    }
}
