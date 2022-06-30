package com.gd.manage.service.impl;

import com.gd.manage.entity.dto.PermissionAddDTO;
import com.gd.manage.entity.dto.PermissionUpdateDTO;
import com.gd.manage.entity.po.PermissionPO;
import com.gd.manage.entity.query.PermissionQuery;
import com.gd.manage.mapper.PermissionMapper;
import com.gd.manage.mapper.RolePermissionMapper;
import com.gd.manage.service.BaseService;
import com.gd.manage.service.PermissionService;
import com.gd.manage.utils.PageHelperUtil;
import com.gd.manage.utils.UuidUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl extends BaseService implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public PermissionPO add(PermissionAddDTO record) {
        PermissionPO permissionPO = new PermissionPO();
        BeanUtils.copyProperties(record, permissionPO);

        permissionPO.setId(UuidUtils.getUuid32());
        permissionPO.setCreateId(currentUser.getId());
        permissionPO.setCreateTime(new Date());

        permissionMapper.insertSelective(permissionPO);

        return permissionPO;
    }

    @Override
    public PermissionPO update(PermissionUpdateDTO record) {
        PermissionPO permissionPO = new PermissionPO();
        BeanUtils.copyProperties(record, permissionPO);

        permissionPO.setUpdateId(currentUser.getId());
        permissionPO.setUpdateTime(new Date());

        permissionMapper.updateByPrimaryKeySelective(permissionPO);

        return permissionPO;
    }

    @Override
    public void delete(String[] ids) {
        for (String permissionId : ids) {
            //删除角色权限关联
            rolePermissionMapper.deleteByPermissionId(permissionId);
            //删除权限
            permissionMapper.deleteByPrimaryKey(permissionId);
        }
    }

    @Override
    public PermissionPO get(String id) {
        return permissionMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<PermissionPO> queryByUserId(String userId) {
        return permissionMapper.queryByUserId(userId);
    }

    @Override
    public PageInfo<PermissionPO> query(PermissionQuery query) {
        PageHelperUtil.setPage(query.getPage(),query.getSize());
        PageInfo<PermissionPO> pageInfo = new PageInfo<>(permissionMapper.query(query));
        return pageInfo;
    }
}
