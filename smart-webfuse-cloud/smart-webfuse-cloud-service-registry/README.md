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

## 一些参考文章

- [eureka缓存细节以及生产环境的最佳配置](http://bhsc881114.github.io/2018/04/01/eureka%E7%BC%93%E5%AD%98%E7%BB%86%E8%8A%82%E4%BB%A5%E5%8F%8A%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E7%9A%84%E6%9C%80%E4%BD%B3%E9%85%8D%E7%BD%AE/)
- [Spring Cloud中，Eureka常见问题总结](http://www.itmuch.com/spring-cloud-sum-eureka/)
- https://github.com/spring-cloud/spring-cloud-netflix/issues/373