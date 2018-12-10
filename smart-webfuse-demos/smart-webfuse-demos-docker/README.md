# Spring Boot with Docker

## 运行命令

打包命令： `mvn clean package docker:build`

Spring Profiles运行：`$ docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t webfuse/smart-webfuse-demos-docker`


## 参考

- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)