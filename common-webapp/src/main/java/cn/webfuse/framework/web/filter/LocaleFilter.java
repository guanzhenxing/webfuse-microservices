package cn.webfuse.framework.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;

/**
 * 国际化信息设置
 */
public class LocaleFilter extends AbstractBaseFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("init LocaleFilter.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 设置客户端语言
        Locale locale = request.getLocale();
        if (locale == null) {
            String language = request.getParameter("locale");
            if (StringUtils.isNotBlank(language)) {
                locale = new Locale(language);
            } else {
                locale = Locale.SIMPLIFIED_CHINESE;
            }
        }
        LocaleContextHolder.setLocale(locale);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("destroy LocaleFilter.");
    }
}
