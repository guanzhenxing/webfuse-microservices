package cn.webfuse.demos.springevents.synchronous.listener;

import cn.webfuse.demos.springevents.synchronous.event.CustomSpringEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(final CustomSpringEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }

}
