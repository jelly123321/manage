package com.gd.manage.service.impl;

import com.gd.manage.entity.dto.UserAddDTO;
import com.gd.manage.entity.dto.UserUpdateDTO;
import com.gd.manage.entity.po.UserPO;
import com.gd.manage.entity.query.UserQuery;
import com.gd.manage.enums.SgmsConstants;
import com.gd.manage.mapper.UserMapper;
import com.gd.manage.mapper.UserRoleMapper;
import com.gd.manage.service.BaseService;
import com.gd.manage.service.UserService;
import com.gd.manage.utils.PageHelperUtil;
import com.gd.manage.utils.ShiroUtils;
import com.gd.manage.utils.UuidUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserPO add(UserAddDTO record) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(record, userPO);

        userPO.setId(UuidUtils.getUuid32());
        userPO.setForbidden(SgmsConstants.YesNo.NO);
        userPO.setSalt(ShiroUtils.getRandomSalt(32));
        //密码加密
        userPO.setPassword(ShiroUtils.md5(userPO.getPassword(), userPO.getSalt()));
        userPO.setCreateId(currentUser.getId());
        userPO.setCreateTime(new Date());

        userMapper.insertSelective(userPO);

        return userPO;
    }

    @Override
    public UserPO update(UserUpdateDTO record) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(record, userPO);

        userPO.setUpdateId(currentUser.getId());
        userPO.setUpdateTime(new Date());

        userMapper.updateByPrimaryKeySelective(userPO);
        return userMapper.selectByPrimaryKey(userPO.getId());
    }

    @Override
    public void delete(String[] ids) {
        for (String userId : ids) {
            //删除人员角色关联
            userRoleMapper.deleteByPrimaryKey(userId);
            //删除人员
            userMapper.deleteByPrimaryKey(userId);
        }
    }

    @Override
    public UserPO get(String id) {
        UserPO userPO = userMapper.selectByPrimaryKey(id);
        return userPO;
    }


    @Override
    public UserPO getByUserName(String userName) {
        UserPO userPO = userMapper.selectByUserName(userName);
        return userPO;
    }

    @Override
    public PageInfo<UserPO> query(UserQuery query) {
        PageHelperUtil.setPage(query.getPage(),query.getSize());
        PageInfo<UserPO> pageInfo = new PageInfo<>(userMapper.query(query));
        return pageInfo;
    }



}
