package cn.webfuse.demos.springevents.userdemo.publisher;

import cn.webfuse.demos.springevents.userdemo.event.UserRegisterEvent;
import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserPublisher {

    //    @Autowired
//    ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void register(UserBean user) {
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));
    }


}
