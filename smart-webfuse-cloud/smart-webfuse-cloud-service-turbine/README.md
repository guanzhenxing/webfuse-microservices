# smart-webfuse-cloud-service-turbine

Turbine来对服务的Hystrix数据进行聚合展示。也就是一个Turbine服务聚合若干个服务的Hystrix数据，并指定到具体的集群名中。

如：

部署在不同的服务器上的uaa-service的Hystrix数据可以使用uaa-service-turbine聚合在一起。此时，配置如下：
```yml
turbine:
  app-config: uaa-service
  cluster-name-expression: 'uaa-service-cluster'
  combine-host-port: true
```

或者，可以把部署在阿里云上的若干种服务（uaa-service,message-service等）Hystrix数据可以使用aliyun-turbine聚合在一起。此时，配置如下：
```yml
turbine:
  app-config: aliyun-service
  cluster-name-expression: 'aliyun-service-cluster'
  combine-host-port: true
```



使用Turbine后，架构图如下：

![使用Turbine后的Hystrix数据聚合架构](http://blog.didispace.com/content/images/posts/spring-cloud-starter-dalston-5-2-2.png)