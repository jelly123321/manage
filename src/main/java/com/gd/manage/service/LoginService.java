package com.gd.manage.service;

import com.gd.manage.entity.dto.UserAddDTO;
import com.gd.manage.entity.dto.UserUpdateDTO;
import com.gd.manage.entity.po.UserPO;
import com.gd.manage.entity.query.UserQuery;
import com.github.pagehelper.PageInfo;


public interface LoginService {

    /**
     * 人员新增
     * @param record
     * @return
     */
    UserPO add(UserAddDTO record);

    /**
     * 人员修改
     * @param record
     * @return
     */
    UserPO update(UserUpdateDTO record);


    /**
     * 人员删除
     * @param ids
     * @return
     */
    void delete(String[] ids);

    /**
     * 人员详情
     * @param id
     * @return
     */
    UserPO get(String id);

    /**
     * 人员详情
     * @param userName
     * @return
     */
    UserPO getByUserName(String userName);


    /**
     * 人员列表查询
     * @param query
     * @return
     */
    PageInfo<UserPO> query(UserQuery query);

}
