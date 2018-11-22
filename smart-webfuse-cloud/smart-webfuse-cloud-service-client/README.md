# smart-webfuse-cloud-service-client

微服务客户端，集成了常见的微服务需要用到的组件。


## 服务治理

### 服务注册

服务启动后，会将自己注册到Eureka上。

采用的是默认的Eureka配置。

### 服务发现

服务发现在这里可以理解为发现其他服务。包括负载均衡等等

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
