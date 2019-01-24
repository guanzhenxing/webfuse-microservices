package cn.webfuse.demos.springevents.synchronous.event;

public class GenericStringSpringAppEvent extends GenericSpringAppEvent<String> {

    public GenericStringSpringAppEvent(final Object source, final String what) {
        super(source, what);
    }

}
