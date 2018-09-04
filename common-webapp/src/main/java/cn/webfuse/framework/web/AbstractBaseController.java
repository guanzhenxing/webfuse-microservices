package cn.webfuse.framework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 基础的Controller，所有的Controller可以继承这个
 */
public abstract class AbstractBaseController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


}
