# Restful接口版本管理

参考：

- http://www.cnblogs.com/jcli/p/springmvc_restful_version.html
- https://github.com/hongfuli/study_notes/tree/master/spring
- http://www.saily.top/2015/03/31/Spring-MVC-%E6%8E%A5%E5%8F%A3%E7%89%88%E6%9C%AC%E7%AE%A1%E7%90%86/


Spring MVC通过在方法上使用RequestMapping来确认应该使用哪个方法来响应相应的请求，而RequestMapping又通过各种RequestCondition的实现来完成各种过滤（比如：consumes，headers，methods，params，produces以及value等）。在Spring MVC框架中使用RequestConditionHolder和RequestMappingInfo这两个实现。