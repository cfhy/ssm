package com.yyb.config;

import com.yyb.common.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理验证失败异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData handleValidationException(BindException exception) {
        List<ObjectError> errorList =exception.getAllErrors();
        ObjectError error=errorList.get(0);
        return ResponseData.fail(error.getDefaultMessage());
    }


    /**
     * 500 - Internal Server Error
     * 捕获以上异常之外的所有异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e) {
        return ResponseData.fail("内部服务错误 "+e.getMessage());
    }
}
