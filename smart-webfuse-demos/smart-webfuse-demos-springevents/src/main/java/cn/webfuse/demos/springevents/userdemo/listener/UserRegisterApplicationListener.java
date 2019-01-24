package cn.webfuse.demos.springevents.userdemo.listener;

import cn.webfuse.demos.springevents.userdemo.event.UserRegisterEvent;
import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterApplicationListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        UserBean user = event.getUser();
        System.out.println("ApplicationListener 注册信息，用户名：" + user.getUsername() + "，密码：" + user.getPassword());
    }
}
