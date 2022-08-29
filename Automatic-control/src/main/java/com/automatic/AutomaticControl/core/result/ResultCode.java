package com.automatic.AutomaticControl.core.result;

/**
 *
 * @ProjectName: micro-questions-answers
 * @ClassName: ResultCode
 * @Description: 响应状态码
 * @Author: xiefan
 * @Date: 2021/11/8 11:17
 *
 */
public enum ResultCode {

    // 成功
    SUCCESS(200, "Success!"),
    // 失败
    FAIL(400, "Failure!"),
    // 没有权限
    NO_PERMISSION(403, "Need Authorities!"),
    // 未登录
    LOGIN_NO(402, "Need Login!"),
    // 登录失败
    LOGIN_FAIL(401, "Login Failure!"),
    // 登录成功
    LOGIN_SUCCESS(200, "Login Success!"),
    // 退出登录
    LOGOUT_SUCCESS(200, "Logout Success!"),
    // 会话到期
    SESSION_EXPIRES(101, "Session Expires!"),
    // 会话到期, 其他用户登录
    SESSION_EXPIRES_OTHER_LOGIN(101, "Session Expires!Other users login！");

    /**
     * 编码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 构造函数
     * @param code
     * @param message
     */
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
