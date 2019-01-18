# webfuse-commons-logging-starter

## 怎么使用

- 如果只是想使用access日志相关，在对应的Application上使用@EnableAccessLogger注解
- 默认使用的是Logger去写日志，如果想要其他方式的写日志，可以继承LoggerWriter
- 如果想要全部的logger功能，可以使用@EnableLogger注解