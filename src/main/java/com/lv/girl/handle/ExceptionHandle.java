package com.lv.girl.handle;

import com.lv.girl.domain.Result;
import com.lv.girl.utils.ResultUtil;
import exception.GirlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 21:36
 * @Description
 **/

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class) //捕获exception
    @ResponseBody
    public Result handle(Exception e) {
//判断异常是不是自定义异常
        if (e instanceof GirlException) {
//        如果是的话
            GirlException girlException = (GirlException) e;
//        返回错误码的提示
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else {
            logger.error("[系统异常]{}",e);
            return ResultUtil.error(-1, "未知错误！");
        }
    }
}
