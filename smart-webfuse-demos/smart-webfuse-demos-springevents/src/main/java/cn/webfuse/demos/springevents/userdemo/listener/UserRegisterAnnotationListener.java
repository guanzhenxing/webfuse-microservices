package cn.webfuse.demos.springevents.userdemo.listener;

import cn.webfuse.demos.springevents.userdemo.event.UserRegisterEvent;
import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 使用@EventListener方法实现注册事件监听
 */
@Component
public class UserRegisterAnnotationListener {

    @EventListener
    public void handleRegister(UserRegisterEvent userRegisterEvent) {
        UserBean user = userRegisterEvent.getUser();

        System.out.println("@EventListener注册信息，用户名："+user.getUsername()+"，密码："+user.getPassword());
    }
}
