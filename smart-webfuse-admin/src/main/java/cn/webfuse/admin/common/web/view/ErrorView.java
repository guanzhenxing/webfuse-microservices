package cn.webfuse.admin.common.web.view;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 错误页面的默认跳转
 */
public class ErrorView implements View {

    @Override
    public String getContentType() {
        return "text/html";
    }


    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/global/error").forward(request, response);
    }
}
