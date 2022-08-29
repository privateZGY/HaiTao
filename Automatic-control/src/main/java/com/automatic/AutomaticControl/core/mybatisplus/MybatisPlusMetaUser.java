package com.automatic.AutomaticControl.core.mybatisplus;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.automatic.AutomaticControl.customer.entity.CustomerUser;
import com.automatic.AutomaticControl.core.statics.GlobalStaticVariable;

import javax.servlet.http.HttpServletRequest;

/**
* 封装获取用户信息方法
 * 从Token里面解析出来用户信息
* */
public class MybatisPlusMetaUser {
    public CustomerUser request (HttpServletRequest request) {
        // 获取请求头里面的数据
        String token = request.getHeader(GlobalStaticVariable.HTTP_HEADER);
        // 获取用户的一个token
        JWT jwt = JWTUtil.parseToken(token);
        //登录时传如ID值到这个LOGIN_USER里面，在这里面获取出来获取到这个用户登录时的ID，获取得到的是一个object的类型 object本身得到的是一个JSON数据类型 GlobalStaticVariable是在static包里面的一个类,object类型就是设置了之后所有数据类型都可以使用，使用的时候不会受数据类型限制
        Object object = jwt.getPayload(GlobalStaticVariable.LOGIN_USER);
        // 将JSON字符串转换成javabean也就是用户的一个对象， 通过JSON转为javabean的一个形式    ,User是我的一个用户的javabean
        CustomerUser customerUser = JSONUtil.toBean(object.toString(), CustomerUser.class);
        return customerUser;
    }
}
