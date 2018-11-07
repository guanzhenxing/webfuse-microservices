# 服务注册中心 #

微服务架构比传统SOA架构中的服务粒度更小、服务数量更多，为了有效管理各个服务，服务注册的概念应运而生。它的特点是：
1. 简单易用，对用户透明；
1. 高可用，满足CAP理论(一个分布式系统不可能同时满足C(一致性)、A(可用性)和P(分区容错性)。由于分区容错性在是分布式系统中必须要保证的，因此我们只能在A和C之间进行权衡。)；
1. 多语言支持；

## 关于Eureka ##

- Eureka是Netflix开源的一个Restful服务，主要用于服务的注册发现。它由两个组件组成：Eureka服务器和Eureka客户端。
- Eureka服务器用作服务注册服务器。
- Eureka客户端是一个Java客户端，用来简化与服务器的交互、作为轮询负载均衡器，并提供服务的故障切换。
- Eureka Server提供服务注册服务，各个节点启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表中将会存储所有可用服务节点的信息。
- Eureka在设计时就优先保证可用性。即在CAP理论中，Eureka满足AP。Eureka各个节点都是平等的，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。
- Eureka的几个时间点：
    - 在应用启动后，将会向Eureka Server发送心跳,默认周期为30秒。
    - 如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。
    - Eureka Client对已经获取到的注册信息做了30s缓存。即服务通过eureka客户端第一次查询到可用服务地址后会将结果缓存，下次再调用时就不会真正向Eureka发起HTTP请求了。
    - 负载均衡组件Ribbon也有30s缓存。Ribbon会从上面提到的Eureka Client获取服务列表，然后将结果缓存30s。
    - Eureka还有一种自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障。
- Eureka启动保护机制会出现以下情况：
    - Eureka不再从注册列表中移除因为长时间没收到心跳而应该过期的服务
    - Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上(即保证当前节点依然可用)
    - 当网络稳定时，当前实例新的注册信息会被同步到其它节点中
 
## 启动服务命令 ##

    java -jar center-service-registry.jar --spring.profiles.active=xxx

## 一些参考文章

- [eureka缓存细节以及生产环境的最佳配置](http://bhsc881114.github.io/2018/04/01/eureka%E7%BC%93%E5%AD%98%E7%BB%86%E8%8A%82%E4%BB%A5%E5%8F%8A%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E7%9A%84%E6%9C%80%E4%BD%B3%E9%85%8D%E7%BD%AE/)
- [Spring Cloud中，Eureka常见问题总结](http://www.itmuch.com/spring-cloud-sum-eureka/)
- https://github.com/spring-cloud/spring-cloud-netflix/issues/373
- https://github.com/spring-cloud/spring-cloud-netflix/issues/203

以下是一份网传的在中小规模下的配置：

```yml
# 以下的port,defaultZone和hostname来自于系统启动时候传入的参数

spring:
  application:
    name: smart-webfuse-cloud-service-registry
server:
  port: ${port}

eureka:
  server:
    enable-self-preservation: false  # 中小规模下，自我保护模式坑比好处多，所以关闭它
    renewal-threshold-update-interval-ms: 120000  # 心跳阈值计算周期，如果开启自我保护模式，可以改一下这个配置
    eviction-interval-timer-in-ms: 5000 # 主动失效检测间隔,配置成5秒
    use-read-only-response-cache: false # 禁用readOnlyCacheMap
  client:
    healthcheck: true
    service-url:
      defaultZone: ${defaultZone}
    registry-fetch-interval-seconds: 5  # 定时刷新本地缓存时间
  instance:
    hostname: ${hostname:localhost}
    instance-id: ${spring.application.name}@${spring.cloud.client.ipAddress}:${server.port}    # 自定义实例ID
    prefer-ip-address: true
    lease-expiration-duration-in-seconds: 10  #  没有心跳的淘汰时间，10秒
    lease-renewal-interval-in-seconds: 5  # 心跳间隔，5秒
```