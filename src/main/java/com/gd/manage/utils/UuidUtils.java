package com.gd.manage.utils;

import java.util.UUID;

public class UuidUtils {
    /**
     * 获取小写UUID字符串
     *
     * @return UUID字符串
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取大写UUID字符串
     *
     * @return UUID字符串
     */
    public static String getUuidUp() {
        return getUuid().toUpperCase();
    }

    /**
     * 获取小写32位UUID字符串
     *
     * @return UUID字符串
     */
    public static String getUuid32() {
        return getUuid().replaceAll("-", "");
    }

    /**
     * 获取大写32位UUID字符串
     *
     * @return UUID字符串
     */
    public static String getUuidUp32() {
        return getUuidUp().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUuid());
        System.out.println(getUuidUp());
        System.out.println(getUuid32());
        System.out.println(getUuidUp32());
    }
}
