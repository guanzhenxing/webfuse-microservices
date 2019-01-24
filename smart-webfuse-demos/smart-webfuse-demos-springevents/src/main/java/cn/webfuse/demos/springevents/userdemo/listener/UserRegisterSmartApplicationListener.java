package cn.webfuse.demos.springevents.userdemo.listener;

import cn.webfuse.demos.springevents.userdemo.event.UserRegisterEvent;
import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import cn.webfuse.demos.springevents.userdemo.publisher.UserPublisher;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterSmartApplicationListener implements SmartApplicationListener {

    /**
     * 根据ApplicationEvent进行过滤
     *
     * @param eventType 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserRegisterEvent.class;
    }

    /**
     * 根据事件源进行过滤
     *
     * @param sourceType
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == UserPublisher.class;
    }

    /**
     * 同步情况下监听执行的顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        //转换事件类型
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
        //获取注册用户对象信息
        UserBean user = userRegisterEvent.getUser();
        //.../完成注册业务逻辑
        System.out.println("SmartApplicationListener 注册信息，用户名：" + user.getUsername() + "，密码：" + user.getPassword());

    }
}
