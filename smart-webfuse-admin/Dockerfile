# 基于哪个镜像
FROM openjdk:8-jdk-alpine

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器
ADD ./target/smart-webfuse-admin-1.0-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'

# 开放8080端口
EXPOSE 8080

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]