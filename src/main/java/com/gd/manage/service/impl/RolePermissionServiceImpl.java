package com.gd.manage.service.impl;

import com.gd.manage.entity.dto.RolePermissionAddDTO;
import com.gd.manage.entity.dto.RolePermissionUpdateDTO;
import com.gd.manage.entity.po.RolePermissionPO;
import com.gd.manage.entity.query.RolePermissionQuery;
import com.gd.manage.mapper.RolePermissionMapper;
import com.gd.manage.service.BaseService;
import com.gd.manage.service.RolePermissionService;
import com.gd.manage.utils.PageHelperUtil;
import com.gd.manage.utils.UuidUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RolePermissionServiceImpl extends BaseService implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public RolePermissionPO add(RolePermissionAddDTO record) {
        RolePermissionPO rolePermissionPO = new RolePermissionPO();
        BeanUtils.copyProperties(record, rolePermissionPO);

        rolePermissionPO.setId(UuidUtils.getUuid32());
        rolePermissionPO.setCreateId(currentUser.getId());
        rolePermissionPO.setCreateTime(new Date());

        rolePermissionMapper.insertSelective(rolePermissionPO);
        return rolePermissionPO;
    }

    @Override
    public RolePermissionPO update(RolePermissionUpdateDTO record) {
        RolePermissionPO rolePermissionPO = new RolePermissionPO();
        BeanUtils.copyProperties(record, rolePermissionPO);

        rolePermissionPO.setUpdateId(currentUser.getId());
        rolePermissionPO.setUpdateTime(new Date());

        rolePermissionMapper.updateByPrimaryKeySelective(rolePermissionPO);
        return rolePermissionPO;
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            rolePermissionMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public RolePermissionPO get(String id) {
        return rolePermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<RolePermissionPO> query(RolePermissionQuery query) {
        PageHelperUtil.setPage(query.getPage(),query.getSize());
        PageInfo<RolePermissionPO> pageInfo = new PageInfo<>(rolePermissionMapper.query(query));
        return pageInfo;
    }
}
