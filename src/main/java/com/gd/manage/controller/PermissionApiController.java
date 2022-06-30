package com.gd.manage.controller;

import com.gd.manage.common.result.ListResult;
import com.gd.manage.common.result.ObjectResult;
import com.gd.manage.common.result.Result;
import com.gd.manage.entity.dto.PermissionAddDTO;
import com.gd.manage.entity.dto.PermissionUpdateDTO;
import com.gd.manage.entity.po.PermissionPO;
import com.gd.manage.entity.query.PermissionQuery;
import com.gd.manage.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author gq
 * @date 2022/6/21 0021 15:42
 */
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionApiController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectResult add(@RequestBody PermissionAddDTO record) throws Exception {
        PermissionPO permissionPO = permissionService.add(record);
        return ObjectResult.ok("新增成功", permissionPO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResult update(@RequestBody PermissionUpdateDTO record) throws Exception {
        PermissionPO permissionPO = permissionService.update(record);
        return ObjectResult.ok("更新成功", permissionPO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody String[] ids) throws Exception {
        permissionService.delete(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResult<PermissionPO> get(@RequestParam String id) throws Exception {
        PermissionPO permissionPO = permissionService.get(id);
        return ObjectResult.ok("查询人员详情成功", permissionPO);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ListResult<PermissionPO> query(@RequestBody PermissionQuery query) throws Exception {
        PageInfo<PermissionPO> pageInfo = permissionService.query(query);
        return ListResult.ok(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getList());
    }

}
