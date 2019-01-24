package cn.webfuse.demos.events.generic.listener;

import cn.webfuse.demos.events.generic.event.GenericSpringEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDrivenEventListener {

    @EventListener
    public void handleSuccessful(GenericSpringEvent<String> event) {
        System.out.println("Handling generic event (conditional)." + event.isSuccess());
    }
}

