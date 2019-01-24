package cn.webfuse.demos.springevents.synchronous.event;

import org.springframework.context.ApplicationEvent;

public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    public CustomSpringEvent(final Object source, final String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


