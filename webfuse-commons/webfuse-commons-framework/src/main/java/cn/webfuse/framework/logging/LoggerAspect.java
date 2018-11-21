package cn.webfuse.framework.logging;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class LoggerAspect {

    protected final List<LoggerWriter> loggerWriters = new ArrayList<>();

    @Autowired
    public void addWriter(LoggerWriter writer) {
        if (!loggerWriters.contains(writer)) {
            loggerWriters.add(writer);
        }
    }

}
