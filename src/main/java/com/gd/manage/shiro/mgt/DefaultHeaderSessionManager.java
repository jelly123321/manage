package com.gd.manage.shiro.mgt;

import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class DefaultHeaderSessionManager extends DefaultWebSessionManager {

    private final String TOKEN_HEADER_NAME = "Authorization";  //token头名称

    private Cookie sessionIdCookie;

    public DefaultHeaderSessionManager() {
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setHttpOnly(true); //more secure, protects against XSS attacks
        this.sessionIdCookie = cookie;
    }

    public Cookie getSessionIdCookie() {
        return sessionIdCookie;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public void setSessionIdCookie(Cookie sessionIdCookie) {
        this.sessionIdCookie = sessionIdCookie;
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        return getReferencedSessionId(request, response);
    }

    /**
     * 首先从请求头中获取sid
     * 没有其次从cookie中获取sid
     * 没有然后从url中“;”后面JSESSIONID中获取  例：http://www.abc.com/login.jsp;JSESSIONID=1234
     * 最后从url参数中获取sid
     *
     * @param request
     * @param response
     * @return
     */
    private Serializable getReferencedSessionId(ServletRequest request, ServletResponse response) {

        //尝试请求头获取sessionId
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String id = httpServletRequest.getHeader(TOKEN_HEADER_NAME);
        if (id != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    "header");
        } else {
            //尝试cookie获取sessionId
            id = getSessionIdCookieValue(request, response);

            if (id != null) {
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                        ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
            } else {
                //尝试url获取sessionId
                //not in a cookie, or cookie is disabled - try the request URI as a fallback (i.e. due to URL rewriting):

                //try the URI path segment parameters first:
                id = getUriPathSegmentParamValue(request, ShiroHttpSession.DEFAULT_SESSION_ID_NAME);

                if (id == null) {
                    //not a URI path segment parameter, try the query parameters:
                    String name = getSessionIdName();
                    id = request.getParameter(name);
                    if (id == null) {
                        //try lowercase:
                        id = request.getParameter(name.toLowerCase());
                    }
                }
                if (id != null) {
                    request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                            ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
                }
            }
        }
        if (id != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            //automatically mark it valid here.  If it is invalid, the
            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        }
        request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());

        return id;
    }

    private String getSessionIdCookieValue(ServletRequest request, ServletResponse response) {
        if (!isSessionIdCookieEnabled()) {
            return null;
        }
        if (!(request instanceof HttpServletRequest)) {
            return null;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return getSessionIdCookie().readValue(httpRequest, WebUtils.toHttp(response));
    }

    private String getUriPathSegmentParamValue(ServletRequest servletRequest, String paramName) {

        if (!(servletRequest instanceof HttpServletRequest)) {
            return null;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri == null) {
            return null;
        }

        int queryStartIndex = uri.indexOf('?');
        if (queryStartIndex >= 0) { //get rid of the query string
            uri = uri.substring(0, queryStartIndex);
        }

        int index = uri.indexOf(';'); //now check for path segment parameters:
        if (index < 0) {
            //no path segment params - return:
            return null;
        }

        //there are path segment params, let's get the last one that may exist:

        final String TOKEN = paramName + "=";

        uri = uri.substring(index + 1); //uri now contains only the path segment params

        //we only care about the last JSESSIONID param:
        index = uri.lastIndexOf(TOKEN);
        if (index < 0) {
            //no segment param:
            return null;
        }

        uri = uri.substring(index + TOKEN.length());

        index = uri.indexOf(';'); //strip off any remaining segment params:
        if (index >= 0) {
            uri = uri.substring(0, index);
        }

        return uri; //what remains is the value
    }

    //since 1.2.1
    private String getSessionIdName() {
        String name = this.sessionIdCookie != null ? this.sessionIdCookie.getName() : null;
        if (name == null) {
            name = ShiroHttpSession.DEFAULT_SESSION_ID_NAME;
        }
        return name;
    }
}
