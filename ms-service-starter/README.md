# 服务的模板 #

服务模板的主要的作用是为服务提供者（provider）和服务消费者（customer）提供开发的模板。

## 关于配置 ##

项目中硬编码的配置只存放"配置信息"的信息，其他配置都存放在“配置中心”的相应工程文件夹中。

## 已存在的功能点 ##

- 各服务通过注册中心（Eureka）实现互相发现；
- 整个业务过程中所有服务的配置文件通过Spring Cloud Config来管理；
- 通过远程客户端（Feign）调用其他服务，通过Ribbon实现负载均衡，并在调用过程中增加了断路器（Hystrix）的功能；
- 使用Sleuth实现分布式服务跟踪；

## 关于package ##

    |-client    接入第三方的接口
    |-config    配置文件
    |-controller    controller包
    |-domain    领域
    |-repository    持久化
    |-service   service包