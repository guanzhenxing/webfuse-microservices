package cn.webfuse.demos.events.generic.publish;

import cn.webfuse.demos.events.generic.event.GenericSpringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class GenericSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent() {
        System.out.println("Publishing generic event. ");
        GenericSpringEvent<String> genericSpringEvent = new GenericSpringEvent(this, true);
        applicationEventPublisher.publishEvent(genericSpringEvent);
    }
}
