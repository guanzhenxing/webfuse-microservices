# webfuse-service-starter

## 功能

- [swagger2支持](https://github.com/SpringForAll/spring-boot-starter-swagger)

## 运维

### 如何优雅下线微服务

在想下线应用的application.yml 中添加配置，从而暴露/service-registry 端点：

```yml
management:
  endpoints:
    web:
      exposure:
        include: service-registry

```

发送：`curl -X "POST" "http://localhost:8000/actuator/service-registry?status=DOWN" \
       -H "Content-Type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8"
`