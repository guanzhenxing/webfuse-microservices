# Mybatis

## 可以参考的common-mapper

https://github.com/jch123/mybatis-common-opration

https://github.com/jaspercloud/common-mapper

https://github.com/lmkang/common-mapper

https://github.com/kazuki43zoo/mybatis-common-mapper-demo


## 自己的对common-mapper思路

1. 使用jpa的注解，对bean进行注解
2. 在系统启动的时候，对所有的jpa注解进行解析，生成对应的sql元数据
3. 在执行Mapper的时候，时候进行拦截，重新拼装SQL
4. 执行相应的SQL


## 分库分表

https://gitee.com/mirrors/TSharding-Client


## 文章

http://www.cnblogs.com/fangjian0423/p/mybaits-dynamic-sql-analysis.html
