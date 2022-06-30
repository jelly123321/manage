package com.gd.manage.common.result;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author gq
 * @date 2022/6/21 0021 17:04
 */
@EqualsAndHashCode
public class ObjectResult <T> implements Serializable {
//    @ApiModelProperty("结果代码。200成功 500服务器错误")
    protected Integer code;
//    @ApiModelProperty("消息内容")
    protected String msg;
//    @ApiModelProperty("数据对象")
    private T data;

    public static <T> ObjectResult<T> ok(T data) {
        return build(ResultStatus.OK.code(), ResultStatus.OK.message(), data);
    }

    public static <T> ObjectResult<T> ok(String msg, T data) {
        return build(ResultStatus.OK.code(), msg, data);
    }

    public static <T> ObjectResult<T> fail(Integer code, String msg, T data) {
        return build(code, msg, data);
    }

    public static <T> ObjectResult<T> build(Integer code, String msg, T data) {
        ObjectResult<T> r = new ObjectResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public ObjectResult() {
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ObjectR(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
