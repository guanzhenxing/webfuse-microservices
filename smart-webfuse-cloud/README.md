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

- 2001~2099 服务注册中心
- 2101~2199 配置服务中心
- 2201~2299 监控收集服务
- 7001~7999 Dashboard服务
- 8000~9999 应用服务系列