package cn.webfuse.demos.events.generic.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericSpringEvent<T> {

    private T what;
    protected boolean success;


}
