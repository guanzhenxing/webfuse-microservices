# smart-webfuse

定义为快速的单体开发



- iBase4J


##  spring boot配置参数参考

[common-application-properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

--------

- 登录：账号密码模式、短信验证码模式、社交账号模式、单点登录
- 用户管理：该功能主要完成系统用户配置
- 机构管理：配置系统组织机构，树结构展现，可随意调整上下级
- 权限管理：配置权限的代码
- 菜单管理：配置系统菜单，操作权限，按钮权限标识等
- 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分
- 终端管理：动态配置oauth终端，后端可配置化
- 字典管理：对系统中经常使用的一些较为固定的数据进行维护，如：是否等。
- 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。

---------------------------------------------------------

- 动态路由：基于zuul实现动态路由，后端可配置化
- 服务限流：多种维度的流量控制（服务、IP、用户等）
- 消息总线：配置动态实时刷新
- 文件系统: 支持FastDFS、七牛云，扩展API几行代码实现上传下载
- 消息中心：短信、邮件模板发送，几行代码实现发送
- 缓存管理：基于Cache Cloud 保证Redis 的高可用
- 服务监控: Spring Boot Admin
- 分布式任务调度： 基于elastic-job的分布式文件系统，zookeeper做调度中心
- zipkin链路追踪： 数据保存ELK，图形化展示
- pinpoint链路追踪： 数据保存hbase，图形化展示


---------------------------------------------------------

- 分库分表：shardingdbc分库分表策略
- 数据权限: 使用mybatis对原查询做增强，业务代码不用控制，即可实现。



mvn clean package docker:build