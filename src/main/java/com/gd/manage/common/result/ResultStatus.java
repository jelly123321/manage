package com.gd.manage.common.result;

/**
 * @author gq
 * @date 2022/6/21 0021 17:04
 */
public enum ResultStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout");

    private final Integer code;
    private final String message;

    private ResultStatus(Integer value, String message) {
        this.code = value;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static ResultStatus codeOf(Integer code) {
        ResultStatus status = resolve(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        } else {
            return status;
        }
    }

    public static ResultStatus resolve(Integer code) {
        ResultStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultStatus status = var1[var3];
            if (status.code == code) {
                return status;
            }
        }

        return null;
    }
}
