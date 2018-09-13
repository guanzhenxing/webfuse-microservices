package cn.webfuse.framework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {

    /**
     * 跳转到404页面
     */
    @RequestMapping(path = "/404")
    public String errorPage() {
        return "/404.html";
    }


}
