package cn.webfuse.microservices.centralregistry.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    /**
     * Eureka客户端下线事件
     *
     * @param eurekaInstanceCanceledEvent
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        LOGGER.info("");
    }

    /**
     * Eureka客户端服务注册事件
     *
     * @param eurekaInstanceRegisteredEvent
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        LOGGER.info("");
    }

    /**
     * Eureka客户端续约事件
     *
     * @param eurekaInstanceRenewedEvent
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent eurekaInstanceRenewedEvent) {
        LOGGER.info("");
    }

    /**
     * Eureka服务端可用事件
     *
     * @param eurekaRegistryAvailableEvent
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent eurekaRegistryAvailableEvent) {

    }

    /**
     * Eureka服务端启动事件
     *
     * @param eurekaServerStartedEvent
     */
    @EventListener
    public void listen(EurekaServerStartedEvent eurekaServerStartedEvent) {


    }

}