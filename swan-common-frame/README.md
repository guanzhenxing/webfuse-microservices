# 基础的框架



## 动态数据源的使用

配置数据源：
```properties
 spring.datasource.url=""
 spring.datasource.username=""
 spring.datasource.password=""
 spring.datasource.driver-class-name=""

 spring.custom.datasource.names=ds1,ds2
 spring.custom.datasource.ds1.driver-class-name=""
 spring.custom.datasource.ds1.url=""
 spring.custom.datasource.ds1.username=""
 spring.custom.datasource.ds1.password=""

 spring.custom.datasource.ds2.driver-class-name=""
 spring.custom.datasource.ds2.url=""
 spring.custom.datasource.ds2.username=""
 spring.custom.datasource.ds2.password=""
```
在其他项目中使用
```java
@Configuration
@ComponentScan(basePackages = {"network.swan.frame.db.datasource"})
@Import(DynamicDataSourceRegister.class)
public class DataSourceConfig {
}
```