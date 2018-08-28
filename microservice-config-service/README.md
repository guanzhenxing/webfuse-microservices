# 配置管理服务 #

配置中心用来管理整个服务的配置信息。

## 未完成内容 ##

- 支持bus
- 数据库化
- 版本化
- 加密和解密

## 部署 ##

    java -jar microservice-config-service-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev

## 刷新配置 ##

对配置中心执行 curl -X POST http://localhost:2001/bus/refresh 

## 关于高可用 ##

可以将“配置服务中心”注册到“服务注册中心”中，实现高可用。（暂时没有去实现）

## 其他 ##

### 获得配置信息的规则 ###
获取git上的资源信息遵循如下规则：

    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties  
    
在配置资源中application.yml被所有客户端应用共享，比如当Notification-service请求配置时，使用shared/notification-service.yml和shared/application.yml配置服务响应。

### 为什么不把配置中心注册到服务治理中 ###

如果注册到Eureka以后，客户端需要用到Eureka Discovery来实现，这样就在配置文件中定义eureka的defaultZone，而不用写配置中心的地址；

如果没有注册到服务发现中，每个服务都需要去写配置中心的地址，而不用写服务注册中心的地址；
