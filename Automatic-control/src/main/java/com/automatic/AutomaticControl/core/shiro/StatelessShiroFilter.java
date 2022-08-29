package com.automatic.AutomaticControl.core.shiro;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.automatic.AutomaticControl.core.result.ResponseResult;
import com.automatic.AutomaticControl.core.result.ResultCode;
import com.automatic.AutomaticControl.core.statics.GlobalStaticVariable;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ProjectName: micro-questions-answers
 * @ClassName: MyStatelessShiroFilter
 * @Description: shiro 过滤器
 * @Author: xiefan
 * @Date: 2021/11/9 21:06
 * 这个类是一个安全框架的一个过滤器，这个过滤器主要是验证这个token他是不是有效的
 */
public class StatelessShiroFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(StatelessShiroFilter.class);

    /**
     *返回false
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return 返回结果是false的时候才会执行下面的onAccessDenied方法
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        logger.info("is access allowed");
        return false;
    }

    /**
     * 从请求头获取token并验证，验证通过后交给realm进行登录
     * @param servletRequest
     * @param servletResponse
     * @return 返回结果为true表明登录通过
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.info("on access denied");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader(GlobalStaticVariable.HTTP_HEADER);
        if (StrUtil.isBlank(jwt)) {
            this.redirectToLogin(servletRequest,servletResponse);
            return false;
        }
        // 判断token是否过期
        if (!JWTUtil.verify(jwt, GlobalStaticVariable.HMAC_SHA256.getBytes())) {
            this.redirectToLogin(servletRequest,servletResponse);
            return false;
        }
        JWT jwtoken = JWTUtil.parseToken(jwt);
        // 获取签发时的时间戳
        Long tokenTime = (Long) jwtoken.getPayload(GlobalStaticVariable.EXPIRE_TIME);
        // 这个是当前请求的时间戳
        Long currTime = System.currentTimeMillis();
        // 拿当前的时间戳跟签发的token的一个时间戳去做判断, 算出秒/1000/ *60得出分钟
        Long min = (currTime - tokenTime) / (1000 * 60);
        // 拿当前的时间戳跟签发的token的一个时间戳去做判断, 1L等于一分钟
        if (min > 120L) {
            // 如果大于一分钟则证明这个token是过期的，则让他重新登录，重新生成一个在有效期的token
            this.redirectToLogin(servletRequest,servletResponse);
            return false;
        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
        try {
            // 委托realm进行登录认证
            getSubject(servletRequest, servletResponse).login(usernamePasswordToken);
            return true;
        }catch (Exception e) {
            this.redirectToLogin(servletRequest,servletResponse);
            return false;
        }
    }

    /**
     * 重定向到登录页
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        logger.info("redirectToLogin");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding(GlobalStaticVariable.CHARACTER_ENCODING);
        httpServletResponse.setContentType(GlobalStaticVariable.CONTENT_TYPE);
        PrintWriter out = httpServletResponse.getWriter();
        JSONConfig jsonConfig = new JSONConfig();
        jsonConfig.setIgnoreNullValue(false);
        JSON json = JSONUtil.parse(ResponseResult.error(ResultCode.NO_PERMISSION), jsonConfig);
        out.println(json.toString());
        // WebUtils.issueRedirect(request, response, "/login");
    }
}
