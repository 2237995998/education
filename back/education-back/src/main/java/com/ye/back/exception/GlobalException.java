package com.ye.back.exception;

import com.ye.back.response.Result;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @PackageName:com.ye.back.exception
 * @ClassName:GlobalException
 * @Description:
 * @author:何进业
 * @date:2021/5/31 17:13
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = UnauthorizedException.class)
    public Result handler(UnauthorizedException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return new Result().setCode(402).setMessage("无权限操作");
    }

    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Result handler(ExpiredCredentialsException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return new Result().setCode(401).setMessage("登录已过期，请重新登录");
    }


    @ExceptionHandler(value = UnauthenticatedException.class)
    public Result handler(UnauthenticatedException e) {
        log.error("运行时异常：----------------{}", e);
        return new Result().setCode(401).setMessage("未登录");
    }

    @ExceptionHandler(value = UnknownAccountException.class)
    public Result handler(UnknownAccountException e) {
        log.error("运行时异常：----------------{}", e);
        return new Result().setCode(401).setMessage("未登录123");
    }
}
