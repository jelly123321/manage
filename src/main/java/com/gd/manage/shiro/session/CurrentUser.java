package com.gd.manage.shiro.session;

import com.gd.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 当前用户
 *
 * @author afeey;
 */
@Component
public class CurrentUser {

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public String getId() {
        return SessionManage.getCurrentUser() == null ? null : SessionManage.getCurrentUser().getId();
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUserName() {
        return SessionManage.getCurrentUser() == null ? null : SessionManage.getCurrentUser().getUserName();
    }

    /**
     * 获取邮箱
     *
     * @return 用户名
     */
    public String getMail() {
        return SessionManage.getCurrentUser() == null ? null : SessionManage.getCurrentUser().getMail();
    }

    /**
     * 获取访问令牌
     *
     * @return 令牌
     */
    public String getAccessToken() {
        return SessionManage.getSession() == null ? null : new StringBuffer((String) SessionManage.getSession().getId()).toString();
    }

//    /**
//     * 获取用户姓名
//     *
//     * @return 用户姓名
//     */
//    public String getFullName() {
//        return SessionManage.getCurrentUser() == null ? null : SessionManage.getCurrentUser().getFullName();
//    }
//
//    public String getOrgId() {
//        return SessionManage.getCurrentUser() == null ? null : SessionManage.getCurrentUser().getOrgId();
//    }

//    /**
//     * 获取登录用户单位类型
//     *
//     * @return
//     * @throws Exception
//     */
//    public String getOrgType() {
//        OrgBean orgBean = orgService.getOrg(getOrgId());
//        return (null == orgBean) ? null : orgBean.getType();
//    }

//    /**
//     * 获取登录用户单位标段编码
//     *
//     * @return 标段编码
//     * @throws Exception 异常
//     */
//    public String getTenderCode() throws Exception {
//        OrgBean orgBean = orgService.getOrg(getOrgId());
//        return (null == orgBean) ? null : orgBean.getTenderCode();
//    }

//    /**
//     * 获取登录用户单位标段名称
//     *
//     * @return 标段名称
//     * @throws Exception 异常
//     */
//    public String getTenderName() throws Exception {
//        OrgBean orgBean = orgService.getOrg(getOrgId());
//        return (null == orgBean) ? null : orgBean.getTenderName();
//    }

//    /**
//     * 注入当前用户
//     *
//     * @param obj 目标对象
//     * @param <T> 类型
//     * @return 注入后的对象
//     */
//    public <T> T injectTo(T obj) {
//
//        try {
//            UserBean user = SessionManage.getCurrentUser();
//            if (user != null) {
//                for (Class clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
//                    //设置创建者id
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setCreateId",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setCreateId",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getId()});
//                    }
//                    //设置创建者id
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setCreatorId",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setCreatorId",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getId()});
//                    }
//
//                    //设置创建者名称
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setCreatorName",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setCreatorName",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getFullName()});
//                    }
//
//                    //设置创建者名称
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setCreateName",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setCreateName",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getFullName()});
//                    }
//
//                    //设置更新者id
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setUpdateId",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setUpdateId",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getId()});
//                    }
//
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setUpdatorId",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setUpdatorId",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getId()});
//                    }
//
//                    //设置更新者名称
//                    if (ReflectUtil.isExistMethod(clazz,
//                            "setUpdateName",
//                            new Class<?>[]{String.class})) {
//                        ReflectUtil.execMethod(obj,
//                                "setUpdateName",
//                                new Class<?>[]{String.class},
//                                new Object[]{user.getFullName()});
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return obj;
//    }

//    /**
//     * 注入当前用户
//     *
//     * @param list
//     * @param <T>  类型
//     * @return 注入后的列表
//     */
//    public <T> List<T> injectTo(List<T> list) {
//        for (int i = 0; i < list.size(); i++) {
//            injectTo(list.get(i));
//        }
//
//        return list;
//    }

//    /**
//     * 获取用户管理范围orgIds
//     *
//     * @return 管理范围orgIds
//     */
//    public List<String> getManageRangeOrgIds() {
//        List<String> orgIds = Lists.newArrayList();
//        try {
//            List<UserManageRangeVo> manageRangeList = userManageRangeService.getByUserId(getId());
//            if (CollectionUtils.isNotEmpty(manageRangeList)) {
//                for (UserManageRangeVo userManageRange : manageRangeList)
//                    orgIds.add(userManageRange.getOrgId());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return orgIds;
//    }

//    /**
//     * 获取用户管理范围orgBeans
//     *
//     * @return 管理范围orgBeans
//     */
//    public List<OrgBean> getManageRangeOrgBeans() {
//        List<OrgBean> orgBeans = Lists.newArrayList();
//        try {
//            List<UserManageRangeVo> manageRangeList = userManageRangeService.getByUserId(getId());
//            if (CollectionUtils.isNotEmpty(manageRangeList)) {
//                for (UserManageRangeVo userManageRange : manageRangeList)
//                    orgBeans.add(orgService.getOrg(userManageRange.getOrgId()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return orgBeans;
//    }
}
