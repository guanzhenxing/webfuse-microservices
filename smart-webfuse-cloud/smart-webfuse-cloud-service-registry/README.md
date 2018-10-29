# 服务注册中心 #

微服务架构比传统SOA架构中的服务粒度更小、服务数量更多，为了有效管理各个服务，服务注册的概念应运而生。它的特点是：
1. 简单易用，对用户透明；
1. 高可用，满足CAP理论；
1. 多语言支持；

Netflix Eureka支持Region和Zone的概念，其中一个Region可以包含多个Zone。Eureka在启动时需要指定一个Zone名，即指定当前Eureka属于哪个Zone, 如果不指定则属于defaultZone。值得注意的是，Eureka Client也需要指定Zone。

## 启动服务命令 ##

    java -jar center-service-registry.jar --port=2001 --eureka.instance.hostname=eureka-2001 --defaultZone=http://localhost:2002/eureka/,http://localhost:2003/eureka/
    java -jar center-service-registry.jar --port=2002 --eureka.instance.hostname=eureka-2002 --defaultZone=http://localhost:2001/eureka/,http://localhost:2003/eureka/
    java -jar center-service-registry.jar --port=2003 --eureka.instance.hostname=eureka-2003 --defaultZone=http://localhost:2001/eureka/,http://localhost:2002/eureka/
    
上面的代码中`eureka-1001`,`eureka-1002`,`eureka-1003`为域名，defaultZone中的localhost需要改为对应的IP地址。