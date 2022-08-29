package com.automatic.AutomaticControl.core.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.automatic.AutomaticControl.customer.entity.CustomerUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ProjectName: micro-questions-answers
 * @ClassName: MybatisPlusMetaObjectHandler
 * @Description: MybatisPlus新增修改全局填充
 * @Author: xiefan
 * @Date: 2021/11/9 14:59
 */
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取前端发送的请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 实例化我们封装的方法
        MybatisPlusMetaUser mybatisPlusMetaUser = new MybatisPlusMetaUser();
        // 获取我们封装的方法里面获取出来的用户信息
        CustomerUser customerUser = mybatisPlusMetaUser.request(request);
        this.strictInsertFill(metaObject,"createTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject,"creator", () -> customerUser.getUserName(), String.class);
        this.strictInsertFill(metaObject,"updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject,"updater", () -> customerUser.getUserName(), String.class);
        this.strictInsertFill(metaObject,"deleteFlag", () -> 0, Integer.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 获取前端发送的请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 实例化我们封装的方法
        MybatisPlusMetaUser mybatisPlusMetaUser = new MybatisPlusMetaUser();
        // 获取我们封装的方法里面获取出来的用户信息
        CustomerUser customerUser = mybatisPlusMetaUser.request(request);
        this.strictUpdateFill(metaObject,"updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictUpdateFill(metaObject,"updater", () -> customerUser.getUserName(), String.class);
    }
}
