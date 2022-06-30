package com.gd.manage.common.result;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author gq
 * @date 2022/6/21 0021 16:58
 */
@EqualsAndHashCode
public class ListResult<T> implements Serializable {
    //    @ApiModelProperty("结果代码。200成功 500服务器错误")
    protected Integer code;
    //    @ApiModelProperty("消息内容")
    protected String msg;

    private long page;

    private long total;
    //    @ApiModelProperty("数据列表")
    private List<T> data;


    public static <T> ListResult<T> ok(List<T> data) {
        return build(ResultStatus.OK.code(), ResultStatus.OK.message(), data);
    }

    public static <T> ListResult<T> ok(long page, long size, List<T> data) {
        return build1(ResultStatus.OK.code(), ResultStatus.OK.message(), page, size, data);
    }

    public static <T> ListResult<T> ok(String msg, List<T> data) {
        return build(ResultStatus.OK.code(), msg, data);
    }

    public static <T> ListResult<T> fail(Integer code, String msg, List<T> data) {
        return build(code, msg, data);
    }

    public static <T> ListResult<T> build(Integer code, String msg, List<T> data) {
        ListResult r = new ListResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> ListResult<T> build1(Integer code, String msg, long page, long size, List<T> data) {
        ListResult r = new ListResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setPage(page);
        r.setTotal(size);
        return r;
    }

    public ListResult() {
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ListResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
