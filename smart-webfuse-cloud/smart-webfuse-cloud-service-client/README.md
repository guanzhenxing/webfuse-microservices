# smart-webfuse-cloud-service-client

微服务客户端，集成了常见的微服务需要用到的组件。


## 服务治理

### 服务注册

服务启动后，会将自己注册到Eureka上。

采用的是默认的Eureka配置。

#### Ribbon

1. Ribbon的主要功能是提供客户端负载均衡算法。
2. Ribbon客户端组件提供一系列完善的配置项，如，连接超时，重试等。它是一个客户端负载均衡器，会自动的帮助你基于某种规则(如简单轮询，随机连接等)去连接各服务，很容易使用Ribbon实现自定义的负载均衡算法。
3. Ribbon实现软负载均衡，核心有三点：
   * 服务发现，发现依赖服务的列表
   * 服务选择规则，在多个服务中如何选择一个有效服务
   * 服务监听，检测失效的服务，高效剔除失效服务
4. Ribbon流程
   * Ribbon的负载均衡，主要通过LoadBalancerClient来实现的，而LoadBalancerClient具体交给了ILoadBalancer来处理，
   * ILoadBalancer通过配置IRule、IPing等信息，并向EurekaClient获取注册列表的信息，并默认10秒一次向EurekaClient发送“ping”,进而检查是否更新服务列表，最后，得到注册列表后，ILoadBalancer根据IRule的策略进行负载均衡。
   * 而RestTemplate 被@LoadBalance注解后，能过用负载均衡，主要是维护了一个被@LoadBalance注解的RestTemplate列表，并给列表中的RestTemplate添加拦截器，进而交给负载均衡器去处理。
5. 策略说明
   * RoundRobinRule ： 轮询
   * RandomRule ： 随机
   * AvailabilityFilteringRule ： 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
   * WeightedResponseTimeRule ： 根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，会切换到WeightedResponseTimeRule
   * RetryRule ： 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
   * BestAvailableRule ： 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
   * ZoneAvoidanceRule ： 默认规则,复合判断server所在区域的性能和server的可用性选择服务器
6. 注意点
   * 针对某个客户端的自定义策略配置类不能放在@ComponentScan所扫描的当前包下以及子包。见：https://cloud.spring.io/spring-cloud-static/Finchley.SR2/single/spring-cloud.html#_customizing_the_ribbon_client
   
#### Feign

1. 运行过程如下：
   * 首先通过@EnableFeignCleints注解开启FeignCleint
   * 根据Feign的规则实现接口，并加@FeignCleint注解
   * 程序启动后，会进行包扫描，扫描所有的@FeignClient的注解的类，并将这些信息注入到ioc容器中
   * 当接口的方法被调用，通过jdk的代理，来生成具体的RequestTemplate
   * RequestTemplate在生成Request
   * Request交给Client去处理，其中Client可以是HttpUrlConnection、HttpClient也可以是OkHttp
   * 最后Client被封装到LoadBalanceClient类，这个类结合类Ribbon做到了负载均衡
   
#### Hystrix

1. Hystrix断路器的原理很简单可以实现快速失败，如果它在一段时间内侦测到许多类似的错误，会强迫其以后的多个调用快速失败，不再访问远程服务器，从而防止应用程序不断地尝试执行可能会失败的操作，熔断器也可以使应用程序能够诊断错误是否已经修正，如果已经修正，应用程序会再次尝试调用操作。
2. 在为具体执行逻辑的函数上增加@HystrixCommand注解来指定服务降级方法
3. Hystrix实现线程池的隔离，它会为每一个Hystrix命令创建一个独立的线程池，这样就算某个在Hystrix命令包装下的依赖服务出现延迟过高的情况，也只是对该依赖服务的调用产生影响，而不会拖慢其他的服务。
4. 断路器是在什么情况下开始起作用呢？这里涉及到断路器的三个重要参数：快照时间窗、请求总数下限、错误百分比下限。这个参数的作用分别是： 
   * 快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
   * 请求总数下限：在快照时间窗内，必须满足请求总数下限才有资格根据熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用此时不足20次，即时所有的请求都超时或其他原因失败，断路器都不会打开。
   * 错误百分比下限：当请求总数在快照时间窗内超过了下限，比如发生了30次调用，如果在这30次调用中，有16次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%下限情况下，这时候就会将断路器打开。                      