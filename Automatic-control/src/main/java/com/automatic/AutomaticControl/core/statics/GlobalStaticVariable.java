package com.automatic.AutomaticControl.core.statics;

/**
 * @version v1.0
 * @ProjectName: micro-questions-answers
 * @ClassName: JWTStatic
 * @Description: TODO(一句话描述该类的功能)
 * @Author: xiefan
 * @Date: 2021/11/9 21:58
 */
public class GlobalStaticVariable {

    /**
     * 登录用户变量
     */
    public static String LOGIN_USER = "user";

    /**
     * Token有效期
     */
    public static String EXPIRE_TIME = "expire_time";

    /**
     * hutool生成token秘钥
     */
    public static String HMAC_SHA256 = "Z_GY_JSON_WEB_TOKEN_HMAC_SHA256";

    /**
     * token存放的变量名ContentType
     */
    public static String HTTP_HEADER = "Authorization";

    /**
     * 请求编码
     */
    public static String CHARACTER_ENCODING = "UTF-8";

    /**
     * 响应contentType
     */
    public static String CONTENT_TYPE = "\"application/json;charset=utf-8\"";

    /**
    *  字典翻译文本后缀
    */
    public static final String DICT_TEXT_SUFFIX = "_dictText";

}
