package com.gd.manage.service.impl;

import com.gd.manage.entity.dto.RoleAddDTO;
import com.gd.manage.entity.dto.RoleUpdateDTO;
import com.gd.manage.entity.po.RolePO;
import com.gd.manage.entity.query.RoleQuery;
import com.gd.manage.mapper.RoleMapper;
import com.gd.manage.mapper.RolePermissionMapper;
import com.gd.manage.service.BaseService;
import com.gd.manage.service.RoleService;
import com.gd.manage.utils.PageHelperUtil;
import com.gd.manage.utils.UuidUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public RolePO add(RoleAddDTO record) {
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(record, rolePO);

        rolePO.setId(UuidUtils.getUuid32());
        rolePO.setCreateId(currentUser.getId());
        rolePO.setCreateTime(new Date());

        roleMapper.insertSelective(rolePO);
        return rolePO;
    }

    @Override
    public RolePO update(RoleUpdateDTO record) {
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(record, rolePO);

        rolePO.setUpdateId(currentUser.getId());
        rolePO.setUpdateTime(new Date());

        roleMapper.updateByPrimaryKeySelective(rolePO);
        return rolePO;
    }

    @Override
    public void delete(String[] ids) {
        for (String roleId : ids) {
            //删除角色权限关联
            rolePermissionMapper.deleteByRoleId(roleId);
            //删除角色
            roleMapper.deleteByPrimaryKey(roleId);
        }
    }

    @Override
    public RolePO get(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RolePO> getByUserId(String userId) {
        return roleMapper.selectByUserId(userId);
    }

    @Override
    public PageInfo<RolePO> query(RoleQuery query) {
        PageHelperUtil.setPage(query.getPage(),query.getSize());
        PageInfo<RolePO> pageInfo = new PageInfo<>(roleMapper.query(query));
        return pageInfo;
    }
}
