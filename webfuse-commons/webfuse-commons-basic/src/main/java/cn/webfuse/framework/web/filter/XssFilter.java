package cn.webfuse.framework.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 防止XSS攻击的过滤器
 */
public class XssFilter extends AbstractBaseFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
