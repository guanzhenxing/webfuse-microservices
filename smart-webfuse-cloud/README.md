# smart-webfuse-cloud

## 功能

## 模块

```text
.
├── smart-webfuse-cloud-service-client（服务客户端，即服务生产者或者消费者）
├── smart-webfuse-cloud-service-config（分布式配置中心）
├── smart-webfuse-cloud-service-hystrix-dashboard（Hystrix Dashboard）
├── smart-webfuse-cloud-service-registry（服务注册中心）
└── smart-webfuse-cloud-service-turbine（Turbine服务，聚合Hystrix数据）
```

## 部署

### 端口说明

- 2001，2002，2003 -> Eureka注册中心
- 2101 -> Config配置中心
- 2201 -> service-turbine服务
- 7001 -> hystrix-dashboard
- 7002 -> zipkin-server


### 关于SpringCloud

- [Compendium of Configuration Properties - SpringCloud所有的配置项](https://cloud.spring.io/spring-cloud-static/Finchley.RELEASE/multi/multi__appendix_compendium_of_configuration_properties.html)
- [使用Spring Cloud与Docker实战微服务](http://book.itmuch.com/)