package com.olcow.tsso.constant;

public enum ReturnCode {

    SUCCESS("00001","成功"),
    FAIL("00001","失败"),
    CODE01001("01001","用户名不合法"),
    CODE01002("01002","密码不合法"),
    CODE01003("01003","密码加密失败"),
    CODE01004("01004","当前用户名已被注册"),
    CODE01005("01005","当前邮箱已被注册"),
    CODE02001("02001","用户名或密码错误"),
    CODE02002("02002","登录信息验证失败"),
    CODE02003("02003","登录信息过期"),
    CODE99999("99999","系统错误");
    private final String code;

    private final String msg;

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
