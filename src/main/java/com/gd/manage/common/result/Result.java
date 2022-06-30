package com.gd.manage.common.result;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author gq
 * @date 2022/6/21 0021 17:04
 */
@EqualsAndHashCode
public class Result implements Serializable {
//    @ApiModelProperty("结果代码。200成功 500服务器错误")
    protected Integer code;
//    @ApiModelProperty("消息内容")
    protected String msg;
//    @ApiModelProperty("错误信息")
    protected String error;

    public static Result ok() {
        return build(ResultStatus.OK.code(), ResultStatus.OK.message(), (String)null);
    }

    public static Result ok(String msg) {
        return build(ResultStatus.OK.code(), msg, (String)null);
    }

    public static Result fail() {
        return build(ResultStatus.BAD_REQUEST.code(), ResultStatus.BAD_REQUEST.message(), (String)null);
    }

    public static Result fail(String msg) {
        return build(ResultStatus.BAD_REQUEST.code(), msg, (String)null);
    }

    public static Result fail(String msg,String type,String error) {
        return build(ResultStatus.UNAUTHORIZED.code(), msg, (String)null);
    }

    public static Result fail(String msg, String error) {
        return build(ResultStatus.BAD_REQUEST.code(), msg, error);
    }

    public static Result fail(Integer code, String msg, String error) {
        return build(code, msg, error);
    }

    public static Result build(Integer code, String msg, String error) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setError(error);
        return r;
    }

    public Result() {
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getError() {
        return this.error;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "R(code=" + this.getCode() + ", msg=" + this.getMsg() + ", error=" + this.getError() + ")";
    }
}
