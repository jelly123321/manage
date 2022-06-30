package com.gd.manage.shiro.session;

import com.gd.manage.entity.po.UserPO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 会话工具类
 *
 * @author afeey
 */
public class SessionManage {

    public static final String KEY_LOGIN_USER = "CURRENT_USER";

    /**
     * 获取会话
     *
     * @return 会话。会话不存在不会创建会话，返回null
     */
    public static Session getSession() {
        return getSession(false);
    }

    /**
     * 获取会话
     *
     * @param create 创建会话。true会话不存在创建新会话  false会话不存在返回null
     * @return 会话
     */
    public static Session getSession(boolean create) {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession(create);
    }

    /**
     * 获取当前用户
     *
     * @return 用户对象
     */
    public static UserPO getCurrentUser() {
        UserPO user = null;

        Session session = getSession(false);
        if (session != null && session.getAttribute(KEY_LOGIN_USER) != null) {
            user = (UserPO) session.getAttribute(KEY_LOGIN_USER);
        }

        return user;
    }

    /**
     * 设置当前用户
     *
     * @param user 用户对象
     */
    public static void setCurrentUser(UserPO user) {
        Session session = getSession(true);
        session.setAttribute(KEY_LOGIN_USER, user);
        session.setAttribute("USERID", user.getId());
    }

    /**
     * 判断当前用户类型（user type）是否是同root类型管理员
     */
//    public static boolean isRootUser() {
//        UserPO userPO = getCurrentUser();
//        if (userPO == null) {
//            return false;
//        }
//        if (userPO.getUserType().equals(Constants.UserType.ROOT)){
//            return true;
//        }
//        return false;
//    }

}
