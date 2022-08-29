package com.automatic.AutomaticControl.customer.controller;

import cn.hutool.jwt.JWTUtil;
import com.automatic.AutomaticControl.core.result.ResponseResult;
import com.automatic.AutomaticControl.core.result.ResultCode;
import com.automatic.AutomaticControl.core.statics.GlobalStaticVariable;
import com.automatic.AutomaticControl.customer.entity.CustomerUser;
import com.automatic.AutomaticControl.customer.service.ICustomerUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zgy
 * @since 2022-06-24
 */
@RestController
@RequestMapping("/customer/customerUser")
@Api(value = "/customer/customerUser", tags = "用户接口")
public class CustomerUserController {

    @Autowired
    private ICustomerUserService iCustomerUserService;

    // 查询用户信息登录
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口", httpMethod = "POST", response = CustomerUser.class)
    public ResponseResult login (@RequestBody CustomerUser customerUser) {
        // 生成sql语句的where条件
        QueryWrapper<CustomerUser> queryWrapper1 = new QueryWrapper<>();
        // //eq() 等于根据前端传过来的用户昵称还有密码进行登录
        queryWrapper1.lambda().eq(CustomerUser:: getUserName, customerUser.getUserName()).eq(CustomerUser::getUserPassword, customerUser.getUserPassword());
        CustomerUser userLogin = iCustomerUserService.getOne(queryWrapper1);
        if (userLogin == null) {
            return ResponseResult.error(ResultCode.LOGIN_FAIL);
        }
        // JWT token保存信息(生成token)
        Map<String, Object> map = new HashMap<>();
        /*设置进LOGIN_USER这个变量里面，然后给 Object object = jwt.getPayload(GlobalStaticVariable.LOGIN_USER);
        这个是StatelessShiroRealm这个类里面获取出来，这个类在core.shiro里面*/
        map.put(GlobalStaticVariable.LOGIN_USER, userLogin);
        /**
         * EXPIRE_TIME：  token的一个有效期
         * （1000 * 60 * 60 * 24）括号里面的是一天 * 15(乘以15代表的是15天)
         */
        // 当前的时间戳
        map.put(GlobalStaticVariable.EXPIRE_TIME, System.currentTimeMillis());
        String token = JWTUtil.createToken(map, GlobalStaticVariable.HMAC_SHA256.getBytes());
        // shiro 认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(token, token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            return ResponseResult.error(ResultCode.LOGIN_FAIL);
        }
        return ResponseResult.ok(ResultCode.LOGIN_SUCCESS, token);
    }

}
