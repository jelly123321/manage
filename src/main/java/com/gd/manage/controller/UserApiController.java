package com.gd.manage.controller;

import com.gd.manage.common.result.ListResult;
import com.gd.manage.common.result.ObjectResult;
import com.gd.manage.common.result.Result;
import com.gd.manage.entity.dto.UserAddDTO;
import com.gd.manage.entity.dto.UserUpdateDTO;
import com.gd.manage.entity.po.UserPO;
import com.gd.manage.entity.query.UserQuery;
import com.gd.manage.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author gq
 * @date 2022/6/21 0021 15:42
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectResult add(@RequestBody UserAddDTO record) throws Exception {
        UserPO userPO = userService.add(record);
        return ObjectResult.ok("新增成功", userPO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResult update(@RequestBody UserUpdateDTO record) throws Exception {
        UserPO userPO = userService.update(record);
        return ObjectResult.ok("更新成功", userPO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody String[] ids) throws Exception {
        userService.delete(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResult<UserPO> get(@RequestParam String id) throws Exception {
        UserPO userPO = userService.get(id);
        return ObjectResult.ok("查询人员详情成功", userPO);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ListResult<UserPO> query(@RequestBody UserQuery query) throws Exception {
        PageInfo<UserPO> pageInfo = userService.query(query);
        return ListResult.ok(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getList());
    }

}
