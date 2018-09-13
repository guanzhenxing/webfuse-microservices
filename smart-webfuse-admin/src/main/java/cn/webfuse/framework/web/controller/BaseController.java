package cn.webfuse.framework.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected BaseController() {
    }

    protected HttpServletRequest getHttpServletRequest() {
        return null;
    }

    protected HttpServletResponse getHttpServletResponse() {
        return null;
    }

    protected HttpSession getSession() {
        return null;
    }

    protected HttpSession getSession(boolean create) {
        return null;
    }

    protected String getParameter(String name) {
        return null;
    }

    protected void setAttribute(String name, Object value) {

    }


}
