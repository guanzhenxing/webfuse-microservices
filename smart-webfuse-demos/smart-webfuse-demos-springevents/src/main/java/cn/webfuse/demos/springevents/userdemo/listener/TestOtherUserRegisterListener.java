package cn.webfuse.demos.springevents.userdemo.listener;

import cn.webfuse.demos.springevents.userdemo.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestOtherUserRegisterListener {


    @EventListener
    @Order(2)
    @Async
    public void sendMail(UserRegisterEvent userRegisterEvent) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("用户注册成功，发送邮件。");
    }
}
