package com.gd.manage.utils;

import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页辅助工具
 *
 * @author taoy
 * @Time 2017/2/6 0006.
 */
public class PageHelperUtil {

    /**
     * 设置页数
     * @param page
     * @param size
     */
    public static void setPage(int page, int size){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        PageHelperUtil.startPage(params);
    }

    /**
     * 开始分页
     * 根据map中的分页参数进行开启分页
     *
     * @param params page:第几页
     *               size:每页记录大小
     */
    public static void startPage(Map<String, Object> params) {
        if (null == params) {
            return;
        }

        Integer page = (Integer) params.get("page");
        Integer size = (Integer) params.get("size");
        if (null != page && null != size && size > 0) {
            PageHelper.startPage(page, size);
        }

    }

    /**
     * 开始分页
     * 根据map中的分页参数进行开启分页
     *
     * @param params page:第几页
     *               size:每页记录大小
     */
    public static void startPageForDefault(Map<String, Object> params) {
        if (null == params) {
            params.put("page",1);
            params.put("size",10);
            PageHelper.startPage((Integer) params.get("page"), (Integer) params.get("size"));
        }else {
            Integer page = (Integer) params.get("page");
            Integer size = (Integer) params.get("size");
            if (null != page && null != size && size > 0) {
                PageHelper.startPage(page, size);
            }else{
                page = 1;
                size = 10;
                PageHelper.startPage(page, size);
            }
        }
    }

//    /**
//     * 将pageParams转换成Map
//     *
//     * @param params
//     * @return
//     */
//    public static Map<String, Object> convertPageParams(PageParams params) {
//        Map<String, Object> param = new HashMap<>();
//        if (null != params) {
//            param.put("page", params.getPage());
//            param.put("size", params.getSize());
//            param.putAll(params.getMapParams());
//        }
//        return param;
//    }
}
