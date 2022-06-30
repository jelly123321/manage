package com.gd.manage.controller;

import com.gd.manage.common.result.ListResult;
import com.gd.manage.common.result.ObjectResult;
import com.gd.manage.common.result.Result;
import com.gd.manage.entity.dto.UserRoleAddDTO;
import com.gd.manage.entity.dto.UserRoleUpdateDTO;
import com.gd.manage.entity.po.UserRolePO;
import com.gd.manage.entity.query.UserRoleQuery;
import com.gd.manage.service.UserRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author gq
 * @date 2022/6/21 0021 15:42
 */
@RestController
@RequestMapping("/api/v1/userRole")
public class UserRoleApiController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectResult add(@RequestBody UserRoleAddDTO record) throws Exception {
        UserRolePO userRolePO = userRoleService.add(record);
        return ObjectResult.ok("新增成功", userRolePO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResult update(@RequestBody UserRoleUpdateDTO record) throws Exception {
        UserRolePO userRolePO = userRoleService.update(record);
        return ObjectResult.ok("更新成功", userRolePO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody String[] ids) throws Exception {
        userRoleService.delete(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResult<UserRolePO> get(@RequestParam String id) throws Exception {
        UserRolePO userRolePO = userRoleService.get(id);
        return ObjectResult.ok("查询人员详情成功", userRolePO);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ListResult<UserRolePO> query(@RequestBody UserRoleQuery query) throws Exception {
        PageInfo<UserRolePO> pageInfo = userRoleService.query(query);
        return ListResult.ok(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getList());
    }

}
