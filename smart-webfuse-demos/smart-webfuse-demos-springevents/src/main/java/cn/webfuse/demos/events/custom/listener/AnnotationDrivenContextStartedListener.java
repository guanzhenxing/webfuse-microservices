package cn.webfuse.demos.events.custom.listener;

import cn.webfuse.demos.events.custom.event.CustomSpringEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDrivenContextStartedListener {
    @EventListener
    public void handleContextStart(ContextStartedEvent cse) {
        System.out.println("Handling context started event.");
    }

    @EventListener
    @Async
    public void handleCustomSpringEvent(CustomSpringEvent cse) {
        System.out.println("from AnnotationDrivenContextStartedListener:" + cse.getMessage());
    }
}
