# 配置信息资源

管理配置信息数据。application-xxx.yml的资源是可以被共用的；每个项目保存为一个文件夹，文件夹名称为项目名。

获取git上的资源信息遵循如下规则：

    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties

当请求的项目名为user-service时，获得application.yml和user-service的配置信息数据。