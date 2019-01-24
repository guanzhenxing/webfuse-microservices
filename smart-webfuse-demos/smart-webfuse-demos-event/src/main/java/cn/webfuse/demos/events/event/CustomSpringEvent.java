package cn.webfuse.demos.events.event;

import org.springframework.context.ApplicationEvent;

public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
