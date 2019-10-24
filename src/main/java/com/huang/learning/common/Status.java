package com.huang.learning.common;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/22 15:38
 */

public enum Status {
    Success("200","成功"),
    S_400("400", "请求处理异常，请稍后再试"),
    S_500("500", "请求方式有误,请检查 GET/POST"),
    S_501("501", "请求路径不存在"),
    S_502("502", "权限不足"),
    S_10008("10008", "角色删除失败,尚有用户属于此角色"),
    S_10009("10009", "账户已存在"),

    S_20011("20011", "登陆已过期,请重新登陆"),

    S_90003("90003", "缺少必填参数");


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String code;
    private String message;

    Status(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
