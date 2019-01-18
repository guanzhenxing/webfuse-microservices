package cn.webfuse.framework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import java.lang.invoke.MethodHandles;

public abstract class AbstractBaseFilter implements Filter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


}
