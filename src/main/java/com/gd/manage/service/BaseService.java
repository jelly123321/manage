package com.gd.manage.service;

import com.gd.manage.shiro.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseService {

    @Autowired
    protected CurrentUser currentUser;

    /**
     * 列表转字符串
     *
     * @param list 列表
     * @return 字符串。 'id1','id2','id3'
     */
    protected String list2Str(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (String id : list) {
            builder.append(",\'" + id + "\'");
        }

        return builder.substring(1);
    }
}
