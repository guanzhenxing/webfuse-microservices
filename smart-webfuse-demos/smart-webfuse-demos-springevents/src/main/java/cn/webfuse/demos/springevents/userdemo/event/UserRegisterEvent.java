package cn.webfuse.demos.springevents.userdemo.event;

import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class UserRegisterEvent extends ApplicationEvent {

    private UserBean user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(Object source, UserBean user) {
        super(source);
        this.user = user;
    }
}
