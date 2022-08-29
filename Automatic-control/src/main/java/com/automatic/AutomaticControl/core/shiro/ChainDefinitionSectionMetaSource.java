package com.automatic.AutomaticControl.core.shiro;

import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionTable;
import com.automatic.AutomaticControl.jurisdiction.service.IJurisdictionTableService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.List;

/**
 * @ProjectName: micro-questions-answers
 * @ClassName: ChainDefinitionSectionMetaSource
 * @Description: 动态加载所有权限类
 * @Author: xiefan
 * @Date: 2021/11/10 10:31
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {

    @Autowired
    private IJurisdictionTableService iJurisdictionTableService;

    /**
     * 默认url过滤定义（shiro过滤器的filterChainDefinitions属性）
     */
    private String filterChainDefinitions;

    /**
     * 设置默认url过滤定义
     * @param filterChainDefinitions
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    @Override
    public Section getObject() throws Exception {
        /*******************************************
         *!!!!!!这里是鉴权方式
         *  rest：例子/admins/user/**=rest[user]，根据请求的方法，相当于/admins/user/**=perms[user:method]
         * ,其中method为post，get，delete等。
         * port：例子/admins/user/**=port[8081]，当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString，
         * 其中schmal是协议http或https等，serverName是你访问的host，8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
         * perms：例子/admins/user/**=perms[user:add:*]，perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，
         * 例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
         * roles：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，
         * 例如/admins/user/**=roles["admin,guest"]，每个参数通过才算通过，相当于hasAllRoles()方法。
         * anon：例子/admins/**=anon 没有参数，表示可以匿名使用。
         * authc：例如/admins/user/**=authc表示需要认证才能使用，没有参数。
         * authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证。
         * ssl：例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
         * user：例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查。
         *******************************************/
        String restPermissionString = "statelessAuth,perms[{0}]";
        // 加载默认的url过滤定义
        Ini ini = new Ini();
        ini.load(this.filterChainDefinitions);
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        // 动态权限资源（查询数据库的所有权限添加进来） 哪一些接口需要访问，哪一些接口不需要访问，可以在这里面定义权限访问
        // 这里就是一些需要权限认证的链接，增加需要权限认证的链接
        /**
         * /jurisdiction/role这个是controller需要添加进数据库里面的url字段里面，证明这个链接需要权限才能访问这个链接
         * MessageFormat.format(restPermissionString, "jurisdiction")里面的jurisdiction就是权限标志，添加进数据库里面的jurisdiction_sign里面
         */
        // 查询权限表里面的全部信息
        List<JurisdictionTable> selectTables = iJurisdictionTableService.list();
        // 遍历全部信息
        for (JurisdictionTable table : selectTables) {
            // 设置进
            section.put(table.getUrl(), MessageFormat.format(restPermissionString, table.getJurisdictionSign()));
        }
        /*//  这个注释里面就是你有多少条权限就给你生成多少权限出来
        对比的是这一条：section.put("/jurisdiction/role", MessageFormat.format(restPermissionString, "jurisdiction"));
        假如你有100条就给你生成100条
        List<Table> selectTables = tableService.list();
        // 遍历全部信息
        for (Table table : selectTables) {
            // 设置进
            section.put(table.getUrl(), MessageFormat.format(restPermissionString, table.getJurisdictionSign()));
        }*/
//        section.put("/jurisdiction/role", MessageFormat.format(restPermissionString, "jurisdiction"));
        // 哪个链接需要权限访问就在这后面继续添加
        // 例子：section.put("controller", MessageFormat.format(restPermissionString, "标志名称，自定义"));
        // 其他controller没有定义权限认证的话执行这一行代码
        section.put("/**", "statelessAuth");
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
