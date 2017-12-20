package com.lv.girl.utils;

import com.lv.girl.domain.Result;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 20:19
 * @Description
 **/
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}
