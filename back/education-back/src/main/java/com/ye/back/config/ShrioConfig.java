package com.ye.back.config;

import com.ye.back.realm.MyRealm;
import com.ye.back.shiro.JwtFilter;
import com.ye.back.shiro.MyCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @PackageName:com.ye.shiro.config
 * @ClassName:ShrioConfig
 * @Description:
 * @author:何进业
 * @date:2021/4/29 23:09
 */
@Configuration
public class ShrioConfig {


    @Autowired
    private MyRealm myRealm;

    @Autowired
    private MyCredentialsMatcher myCredentialsMatcher;

    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        myRealm.setCredentialsMatcher(myCredentialsMatcher);
        securityManager.setRealm(myRealm);
        return securityManager;
    }

//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        Map<String, String> filterMap = new LinkedHashMap<>();
//
//        //不拦截swagger
//        filterMap.put("/swagger-ui.html**", "anon");
//        filterMap.put("/v2/api-docs", "anon");
//        filterMap.put("/swagger-resources/**", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/**", "jwt");
//        chainDefinition.addPathDefinitions(filterMap);
//        return chainDefinition;
//    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, JwtFilter jwtFilter) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> map = new HashMap<>();
        map.put("jwt", jwtFilter);
        filterFactoryBean.setFilters(map);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/swagger-ui.html**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/css/**", "anon");
//        filterMap.put("/js/**", "anon");
//        filterMap.put("/index.html", "anon");
//        filterMap.put("/fonts/**", "anon");
//        filterMap.put("/favicon", "anon");
        filterMap.put("/**", "jwt");
        filterFactoryBean.setLoginUrl("/user/login");
        filterFactoryBean.setUnauthorizedUrl("/user/login");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }

    @Bean
    public JwtFilter getJwtFilter() {
        return new JwtFilter();
    }

    // 开启注解代理
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
