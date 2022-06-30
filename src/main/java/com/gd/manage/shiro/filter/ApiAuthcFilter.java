package com.gd.manage.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Api认证过滤器
 *
 * @author afeey
 */
public class ApiAuthcFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiAuthcFilter.class);

    private String unauthenticatedUrl = "/v1/unauthenticated";

    /**
     * 判断访问是否被允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    /**
     * 访问被拒绝调用方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = null;
        if ((request instanceof HttpServletRequest)) {
            httpRequest = (HttpServletRequest) request;
        } else {
            log.error("request is not HttpServletRequest");
        }
        httpRequest.getRequestDispatcher(unauthenticatedUrl).forward(request, response);
        return false;
    }

    public void setUnauthenticatedUrl(String unauthenticatedUrl) {
        this.unauthenticatedUrl = unauthenticatedUrl;
    }
}
