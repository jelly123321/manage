package com.gd.manage.controller;

import com.gd.manage.common.result.ListResult;
import com.gd.manage.common.result.ObjectResult;
import com.gd.manage.common.result.Result;
import com.gd.manage.entity.dto.RoleAddDTO;
import com.gd.manage.entity.dto.RoleUpdateDTO;
import com.gd.manage.entity.po.RolePO;
import com.gd.manage.entity.query.RoleQuery;
import com.gd.manage.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author gq
 * @date 2022/6/21 0021 15:42
 */
@RestController
@RequestMapping("/api/v1/role")
public class RoleApiController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectResult add(@RequestBody RoleAddDTO record) throws Exception {
        RolePO rolePO = roleService.add(record);
        return ObjectResult.ok("新增成功", rolePO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResult update(@RequestBody RoleUpdateDTO record) throws Exception {
        RolePO rolePO = roleService.update(record);
        return ObjectResult.ok("更新成功", rolePO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody String[] ids) throws Exception {
        roleService.delete(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResult<RolePO> get(@RequestParam String id) throws Exception {
        RolePO rolePO = roleService.get(id);
        return ObjectResult.ok("查询人员详情成功", rolePO);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ListResult<RolePO> query(@RequestBody RoleQuery query) throws Exception {
        PageInfo<RolePO> pageInfo = roleService.query(query);
        return ListResult.ok(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getList());
    }

}
