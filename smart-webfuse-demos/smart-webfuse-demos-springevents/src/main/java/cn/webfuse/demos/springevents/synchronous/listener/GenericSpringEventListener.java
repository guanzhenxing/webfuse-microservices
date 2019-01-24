package cn.webfuse.demos.springevents.synchronous.listener;

import cn.webfuse.demos.springevents.synchronous.event.GenericSpringEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GenericSpringEventListener {

    @EventListener(condition = "#event.success")
    public void handleSuccessful(final GenericSpringEvent<String> event) {
        System.out.println("Handling generic event (conditional): " + event.getWhat());
    }

}
