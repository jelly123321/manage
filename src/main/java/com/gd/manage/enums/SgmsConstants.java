package com.gd.manage.enums;

/**
 * 系统静态常量接口
 * Created by Scott on 2017/2/7.
 */
public interface SgmsConstants {

    /**
     * 是否禁止
     */
    interface YesNo {
        /**
         * 否
         */
        String NO = "1";

        /**
         * 是
         */
        String YES = "2";
    }

    /**
     * 角色类型
     */
    interface RoleType{
        /**
         * root角色
         */
        String ROOT = "1";

        /**
         * 普通角色
         */
        String NORMAL = "2";

    }

    /**
     * 权限类型
     */
    interface PermissionType{
        /**
         * 目录
         */
        String CATALOGUE = "1";

        /**
         * 接口
         */
        String INTERFACE = "2";

    }



}
