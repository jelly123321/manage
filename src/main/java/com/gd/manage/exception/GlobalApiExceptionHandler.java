package com.gd.manage.exception;

import com.gd.manage.common.result.Result;
import com.gd.manage.common.result.ResultStatus;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常解析器
 *
 * @author afeey
 */
@RestControllerAdvice(basePackages = {"com.gd"})
public class GlobalApiExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 异常
     * @return JSON视图
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessException(BusinessException e, HandlerMethod handler) {
        return Result.build(Integer.parseInt(e.getCode()), e.getMessage(), null);
    }


    @ExceptionHandler(BindException.class)
    public Result bindException(BindException e) {
        String message = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Result.fail(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return Result.fail(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(message);
    }

    /**
     * 全部异常
     *
     * @param e 异常
     * @return JSON视图
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e, HandlerMethod handler) {
        e.printStackTrace();
        String error = e.getMessage();
        if (error == null || error.trim().length() == 0) {
            error = ExceptionUtils.getStackTrace(e);
        }

        return Result.build(ResultStatus.INTERNAL_SERVER_ERROR.code(), "网络请求失败", error);
    }
}


