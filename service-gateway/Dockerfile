# 基于哪个镜像
FROM openjdk:8-jdk-alpine

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器
ADD ./target/service-gateway-0.1-SNAPSHOT.jar  service-gateway-0.1-SNAPSHOT.jar

# 开放端口
EXPOSE 2200

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-gateway-0.1-SNAPSHOT.jar"]