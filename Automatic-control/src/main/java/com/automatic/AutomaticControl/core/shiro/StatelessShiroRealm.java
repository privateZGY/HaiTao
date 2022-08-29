package com.automatic.AutomaticControl.core.shiro;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.automatic.AutomaticControl.customer.entity.CustomerUser;
import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionTable;
import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionUserRole;
import com.automatic.AutomaticControl.core.statics.GlobalStaticVariable;
import com.automatic.AutomaticControl.jurisdiction.service.IJurisdictionRoleJurisdictionService;
import com.automatic.AutomaticControl.jurisdiction.service.IJurisdictionUserRoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName: micro-questions-answers
 * @ClassName: MyStatelessRealm
 * @Description: shiro自定义Realm
 * @Author: xiefan
 * @Date: 2021/11/9 21:04
 * 静态的类，这里面是一些固定的值，通过一些静态的变量来把他引入到项目里面
 * 每一个用户拥有的权限是通过我们这一个自定义的类在这里面去获取用户的信息
 */
public class StatelessShiroRealm extends AuthorizingRealm {

    @Autowired
    private IJurisdictionUserRoleService iJurisdictionUserRoleService;

    // 角色权限service
    @Autowired
    private IJurisdictionRoleJurisdictionService iJurisdictionRoleJurisdictionService;

    Logger logger = LoggerFactory.getLogger(StatelessShiroRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("Realm处理授权");
        /**
         * token令牌
         * 每一个用户拥有的权限是通过我们这一个自定义的类在这里面去获取用户的信息
         */
        String token = (String) principalCollection.getPrimaryPrincipal();
        logger.info("realm授权获取token:" + token);
        /**
         * 1.每一个用户拥有的权限是通过我们这一个自定义的类在这里面去获取用户的信息,
         * 2.通过一个用户的信息获得他这个用户拥有的权限
         */
        // 获取用户的一个token
        JWT jwt = JWTUtil.parseToken(token);
        /**
         * 获取到了token之后通过jwt去解析这一个用户信息
         * 获取这个用户的时候，通过我们登录时签发的一个LOGIN_USER的值也就是用户信息
         *获取得到的是一个object的类型
         */
        //登录时传如ID值到这个LOGIN_USER里面，在这里面获取出来获取到这个用户登录时的ID，获取得到的是一个object的类型 object本身得到的是一个JSON数据类型 GlobalStaticVariable是在static包里面的一个类,object类型就是设置了之后所有数据类型都可以使用，使用的时候不会受数据类型限制
        Object object = jwt.getPayload(GlobalStaticVariable.LOGIN_USER);
        /**
         * JSONUtil.toBean: 转为一个javaBean的一个形式
         * (object.toString(), User.class);解析：
         * 1。object.toString是第一个参数， 传入的是一个json的一个字符串
         * 2. User.classs是第二个参数， 传入的是一个我们要转成的一个JavaBean
         * 这样子就可以获取到这个用户的信息了
         */
        // 将JSON字符串转换成javabean也就是用户的一个对象， 通过JSON转为javabean的一个形式    ,User是我的一个用户的javabean
        CustomerUser customerUser = JSONUtil.toBean(object.toString(), CustomerUser.class);
        // 实例化
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户ID
        customerUser.getUserId();
        // 根据用户ID查询用户角色
        QueryWrapper<JurisdictionUserRole> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(JurisdictionUserRole::getUserId, customerUser.getUserId());
        // 因为一个用户会有多个角色所以用list查询多条, 这里已经查询角色信息包括角色ID
        List<JurisdictionUserRole> selectUserRole = iJurisdictionUserRoleService.list(queryWrapper);
        // 遍历查询出来的用户角色,使用角色ID查询权限ID， 冒号:的意思是赋值
        for (JurisdictionUserRole userRole:selectUserRole) {
            // 获取角色ID
            Integer roleId = userRole.getRoleId();
            // 根据角色ID查询角色权限
            List<JurisdictionTable> selectRoleJurisdiction = iJurisdictionRoleJurisdictionService.selectRoleJurisdiction(roleId);
            for (JurisdictionTable table:selectRoleJurisdiction) {
                /**
                 * 遍历权限添加进shiro框架里面
                 * 1.把用户的权限标志给查询出来，查询出来之后添加到权限认证里面
                 * 2、这里就是添加进shiro框架里面    ！！遍历权限 ，添加到shiro框架里面
                 * getJurisdictionSign权限标志
                 * addStringPermission的作用是：
                 * 1.添加(分配)与帐户直接关联的权限。 如果帐户还没有任何直接权限，将自动创建一个新的权限集合(Set<String>)
                 */
                authorizationInfo.addStringPermission(table.getJurisdictionSign());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("Realm处理登录");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String token = usernamePasswordToken.getUsername();
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
