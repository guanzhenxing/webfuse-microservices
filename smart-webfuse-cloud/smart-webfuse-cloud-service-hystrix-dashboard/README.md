# smart-webfuse-cloud-service-dashboard

## Hystrix Dashboard

Hystrix Dashboard实现Hystrix指标数据的可视化面板。Hystrix Dashboard共支持三种不同的监控方式，依次为：
                                     
- 默认的集群监控：通过URL http://turbine-hostname:port/turbine.stream开启，实现对默认集群的监控。
- 指定的集群监控：通过URL http://turbine-hostname:port/turbine.stream?cluster=[clusterName]开启，实现对clusterName集群的监控。
- 单体应用的监控：通过URL http://hystrix-app:port/hystrix.stream开启，实现对具体某个服务实例的监控。


