package com.gd.manage.controller;

import com.gd.manage.common.result.ListResult;
import com.gd.manage.common.result.ObjectResult;
import com.gd.manage.common.result.Result;
import com.gd.manage.entity.dto.RolePermissionAddDTO;
import com.gd.manage.entity.dto.RolePermissionUpdateDTO;
import com.gd.manage.entity.po.RolePermissionPO;
import com.gd.manage.entity.query.RolePermissionQuery;
import com.gd.manage.service.RolePermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author gq
 * @date 2022/6/21 0021 15:42
 */
@RestController
@RequestMapping("/api/v1/rolePermission")
public class RolePermissionApiController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectResult add(@RequestBody RolePermissionAddDTO record) throws Exception {
        RolePermissionPO rolePermissionPO = rolePermissionService.add(record);
        return ObjectResult.ok("新增成功", rolePermissionPO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResult update(@RequestBody RolePermissionUpdateDTO record) throws Exception {
        RolePermissionPO rolePermissionPO = rolePermissionService.update(record);
        return ObjectResult.ok("更新成功", rolePermissionPO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody String[] ids) throws Exception {
        rolePermissionService.delete(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResult<RolePermissionPO> get(@RequestParam String id) throws Exception {
        RolePermissionPO rolePermissionPO = rolePermissionService.get(id);
        return ObjectResult.ok("查询人员详情成功", rolePermissionPO);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ListResult<RolePermissionPO> query(@RequestBody RolePermissionQuery query) throws Exception {
        PageInfo<RolePermissionPO> pageInfo = rolePermissionService.query(query);
        return ListResult.ok(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getList());
    }

}
