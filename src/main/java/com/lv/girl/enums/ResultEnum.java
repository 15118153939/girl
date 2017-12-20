package com.lv.girl.enums;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 21:54
 * @Description
 * 错误码全部用枚举来维护，比较方便
 **/
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"你可能还在上小学！"),
    MIDDLE_SCHOOL(101,"你可能还在上初中！"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
