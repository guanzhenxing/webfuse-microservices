package cn.webfuse.demos.springevents.userdemo.publisher;

import cn.webfuse.demos.springevents.userdemo.event.UserRegisterEvent;
import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void register(UserBean user) {
        applicationContext.publishEvent(new UserRegisterEvent(this, user));
    }


}
