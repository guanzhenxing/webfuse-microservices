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

spring-boot-autoconfigure：

[Springboot之spring-boot-autoconfigure模块](http://ifeve.com/spring-boot-autoconfigure/)
[spring-boot-starter-swagger](https://github.com/SpringForAll/spring-boot-starter-swagger)
[了解 Spring Boot AutoConfiguration](https://www.cnblogs.com/oopsguy/p/7484398.html)
[springboot-autoconfig自动配置原理](https://blog.csdn.net/seashouwang/article/details/80299571)
[spring boot实战(第十三篇)自动配置原理分析](https://blog.csdn.net/liaokailin/article/details/49559951)
[Creating Your Own Auto-configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html)
[SpringBoot使用AutoConfiguration自定义Starter](https://www.jianshu.com/p/188065e1137b)



