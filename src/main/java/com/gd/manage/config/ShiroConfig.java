package com.gd.manage.config;

import com.gd.manage.shiro.filter.ApiAuthcFilter;
import com.gd.manage.shiro.mgt.DefaultHeaderSessionManager;
import com.gd.manage.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gq
 * @date 2022/6/16 0016 16:06
 */
@Configuration
public class ShiroConfig {

    /**
     * 自定义认证域
     * @return
     */
    @Bean
    public MyRealm realm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return new MyRealm();
    }


    /**
     * 安全管理器
     * @param sessionManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(SessionManager sessionManager,HashedCredentialsMatcher hashedCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(realm(hashedCredentialsMatcher));
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        return new DefaultHeaderSessionManager();
    }

    /**
     * 过滤链
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        shiroFilterFactoryBean.setUnauthorizedUrl("/api/v1/index/notLogin");
//        bean.setLoginUrl("/login");
//        bean.setSuccessUrl("/index");
//        bean.setUnauthorizedUrl("/unauthorizedurl");
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("/doLogin", "anon");
//        map.put("/**", "authc");
//        bean.setFilterChainDefinitionMap(map);

        //加载过滤链
        Map<String, String> chainMap = new LinkedHashMap<>();
        chainMap.put("/static/**", "anon");
        chainMap.put("/files/**", "anon");
        chainMap.put("/index", "anon");
        chainMap.put("/doc.html", "anon");
        chainMap.put("/webjars/**", "anon");
        chainMap.put("/v2/api-docs/**", "anon");
        chainMap.put("/swagger-resources/**", "anon");
        chainMap.put("/api/v1/index/login", "anon");

        chainMap.put("/v1/auth/**", "anon");
        chainMap.put("/v1/file/**", "anon");
        chainMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainMap);

        //自定义过滤器(用于处理前后端分离未认证返回json结果)
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("authc", apiAuthcFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        return shiroFilterFactoryBean;
    }


    /**
     * 凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    @Bean
    public FilterRegistrationBean registration(ApiAuthcFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public ApiAuthcFilter apiAuthcFilter() {
        ApiAuthcFilter filter = new ApiAuthcFilter();
        filter.setUnauthenticatedUrl("/api/v1/index/notLogin");
        return filter;
    }

}
