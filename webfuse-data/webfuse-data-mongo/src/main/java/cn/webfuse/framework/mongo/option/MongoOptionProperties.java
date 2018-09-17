package cn.webfuse.framework.mongo.option;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置项参考：com.mongodb.MongoClientOptions
 * <p>
 * 配置项解释参考：http://kweny.io/mongodb-client-connection-options/
 */
@ConfigurationProperties(prefix = "spring.data.mongodb.option")
public class MongoOptionProperties {

    /**
     * 客户端的描述。该描述用于日志记录和 JMX 等位置。
     * <p>
     * 默认为 null。
     */
    private String description;

    /**
     * 应用程序的逻辑名称。客户端可以据此标识应用程序服务器，用于服务器日志、慢查询日志及概要收集。
     * <p>
     * 默认为 null。
     */
    private String applicationName;

    /**
     * 设定当由于网络错误而写入失败时，是否进行重试。
     */
    private boolean retryWrites;

    /**
     * 每个主机的最小连接数，这些连接在空闲时将保持在连接池中。
     * <p>
     * 默认为 0，不能小于 0。
     */
    private int minConnectionsPerHost = 0;

    /**
     * 每个主机允许的最大连接数，这些连接在空闲时将保持在连接池中。当连接池耗尽后，任何需要连接的操作都将被阻塞并等待可用连接。
     * <p>
     * 默认为 100，不能小于 1。
     */
    private int maxConnectionsPerHost = 100;

    /**
     * 允许阻塞的连接线程数乘数。该值和 maxConnectionsPerHost 相乘的结果就是连接等待队列的最大值，超出的线程将立即抛出异常。
     * <p>
     * 如该值为 5，maxConnectionsPerHost 为 10，则最多可以同时有 50 个线程等待连接。
     * <p>
     * 默认为 5，不能小于 1。
     */
    private int threadsAllowedToBlockForConnectionMultiplier = 5;


    /**
     * 服务器选择的超时时间（毫秒），指定驱动程序从集群中选择服务器时，无法成功而放弃并抛出异常的时间。可以根据需要（如耐心等待或快速返回错误）来设置该参数的值。
     * <p>
     * 默认为 30,000（30秒），这个时间对于在经典的故障恢复阶段中选举出新的主节点来说已经足够。0 表示无可用服务器，将立即超时；负值意味着无限期等待。
     */
    private int serverSelectionTimeout = 30000;

    /**
     * 线程从连接池中获取可用连接的最长等待时间（毫秒）。
     * <p>
     * 默认为 120,000（120秒），0 表示不等待，负值意味着无限期等待。
     */
    private int maxWaitTime = 120000;

    /**
     * 连接池中连接的最大空闲时间（毫秒）。超出空闲时间的连接将被关闭，并在必要时由新建连接替换。
     * <p>
     * 默认为 0，表示无限制，不能小于 0。
     */
    private int maxConnectionIdleTime = 0;

    /**
     * 连接池中连接的最大使用寿命（毫秒）。超出使用寿命的连接将被关闭，并在必要时由新建连接替换。
     * <p>
     * 默认为 0，表示无限制，不能小于 0。
     */
    private int maxConnectionLifeTime = 0;

    /**
     * 连接超时时间（毫秒），仅在新建连接时使用。
     * <p>
     * 默认为 10,000（10秒），0 表示无限制，不能小于 0。
     */
    private int connectTimeout = 10000;

    /**
     * socket 超时时间（毫秒），用于 I/O 读写操作。
     * <p>
     * 默认为 0，表示无限制。
     */
    private int socketTimeout = 0;

    /**
     * 是否启用 SSL。
     * <p>
     * 在未设置 socket factory 的情况下，设置该选项时将同时设置默认的 socket factory，true 时为 java.net.ssl.SSLSocketFactory.getDefault()，false 时为 javax.net.SocketFactory.getDefault()。
     * <p>
     * 开启该选项时，如果设置其它 socket factory，则该 factory 必须创建 java.net.ssl.SSLSocket 的实例，否则将拒绝连接。
     * <p>
     * 默认为 false，不启用。
     */
    private boolean sslEnabled = false;

    /**
     * 启用 SSL 时是否允许无效的主机名（证书域名检查）。true 为允许，即关闭域名检查。
     * <p>
     * 设为 true（允许无效主机名）将使应用程序容易受到中间人攻击。
     * <p>
     * 注意，证书域名检查需要 Java 7 及以上版本，如果应用程序使用了 SSL 运行在 Java 6 上，则必须将该选项设为 true（关闭域名检查）。
     * <p>
     * 默认为 false，即开启域名检查，不允许无效的主机名。
     */
    private boolean sslInvalidHostNameAllowed = false;

    /**
     * 设置驱动程序注册的 JMX Beans 是否始终为 MBeans，无论 VM 是不是 Java 6 及更高版本。
     * <p>
     * 当该选项为 false 时，驱动程序将在 Java 6 或更高版本时使用 MXBeans；Java 5 中使用 MBeans。
     * <p>
     * 默认为 false。
     */
    private boolean alwaysUseMBeans = false;

    /**
     * 心跳检测频率（毫秒）。该选项用于设定驱动程序每次尝试确定每个服务器当前状态的间隔时间。
     * <p>
     * 默认为 10,000（10秒）。
     */
    private int heartbeatFrequency = 10000;

    /**
     * 心跳检测的最小频率（毫秒）。如果驱动程序需要经常检查服务器的可用性，那么距离上次检测至少等待这么长时间，以避免资源浪费。
     * <p>
     * 默认为 500。
     */
    private int minHeartbeatFrequency = 500;

    /**
     * 用于心跳检测的连接超时时间（毫秒）。
     * <p>
     * 默认为 20,000（20秒）。
     */
    private int heartbeatConnectTimeout = 20000;

    /**
     * 用于心跳检测的 socket 超时时间（毫秒）。
     * <p>
     * 默认为 20,000（20秒）。
     */
    private int heartbeatSocketTimeout = 20000;

    /**
     * localThreshold
     * int
     * <p>
     * 用于服务器选择的一个参考阈值，单位为毫秒。如果对于某个操作存在多个合适的服务器，则以该选项的值来确定一个基于延迟时间（RTT）的延迟窗口范围（Latency Window）。以延迟最小的服务器为基准（最小延迟时间），所有延迟时间和最小延迟时间的差值小于该值的服务器，都有资格被随机选中。
     * <p>
     * 如果该参数设为 0，则不使用随机算法，而是选择延迟时间最小的服务器。
     * <p>
     * 默认为 15 毫秒，意味着所有有资格被选中的服务器之间的延迟时间只有 15 毫秒以内的差异。
     */
    private int localThreshold = 15;

    /**
     * requiredReplicaSetName
     * java.lang.String
     * <p>
     * 指定必须的副本集名称。确保连接的所有节点都属于一个指定的副本集。
     * <p>
     * 设置该选项后，客户端将进行以下行为——
     * <p>
     * 以副本集模式连接，并根据给定的服务器发现副本集的所有成员。
     * 确保所有成员报告的副本集名称与指定名称匹配。
     * 如果服务器列表中的任何成员不属于该副本集，则拒绝所有请求。
     * 默认为 null。
     */
    private String requiredReplicaSetName;

    /**
     * 是否启用游标的 finalize 方法，用于清理客户端未关闭的 DBCursor 实例。
     * <p>
     * 如果能够确保每次都会调用 DBCursor 的 close 方法，则可以关闭该选项（设为 false）。
     * <p>
     * 默认为 true。
     */
    private boolean cursorFinalizerEnabled = true;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public boolean isRetryWrites() {
        return retryWrites;
    }

    public void setRetryWrites(boolean retryWrites) {
        this.retryWrites = retryWrites;
    }

    public int getMinConnectionsPerHost() {
        return minConnectionsPerHost;
    }

    public void setMinConnectionsPerHost(int minConnectionsPerHost) {
        this.minConnectionsPerHost = minConnectionsPerHost;
    }

    public int getMaxConnectionsPerHost() {
        return maxConnectionsPerHost;
    }

    public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public int getServerSelectionTimeout() {
        return serverSelectionTimeout;
    }

    public void setServerSelectionTimeout(int serverSelectionTimeout) {
        this.serverSelectionTimeout = serverSelectionTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public int getMaxConnectionLifeTime() {
        return maxConnectionLifeTime;
    }

    public void setMaxConnectionLifeTime(int maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public boolean isSslInvalidHostNameAllowed() {
        return sslInvalidHostNameAllowed;
    }

    public void setSslInvalidHostNameAllowed(boolean sslInvalidHostNameAllowed) {
        this.sslInvalidHostNameAllowed = sslInvalidHostNameAllowed;
    }

    public boolean isAlwaysUseMBeans() {
        return alwaysUseMBeans;
    }

    public void setAlwaysUseMBeans(boolean alwaysUseMBeans) {
        this.alwaysUseMBeans = alwaysUseMBeans;
    }

    public int getHeartbeatFrequency() {
        return heartbeatFrequency;
    }

    public void setHeartbeatFrequency(int heartbeatFrequency) {
        this.heartbeatFrequency = heartbeatFrequency;
    }

    public int getMinHeartbeatFrequency() {
        return minHeartbeatFrequency;
    }

    public void setMinHeartbeatFrequency(int minHeartbeatFrequency) {
        this.minHeartbeatFrequency = minHeartbeatFrequency;
    }

    public int getHeartbeatConnectTimeout() {
        return heartbeatConnectTimeout;
    }

    public void setHeartbeatConnectTimeout(int heartbeatConnectTimeout) {
        this.heartbeatConnectTimeout = heartbeatConnectTimeout;
    }

    public int getHeartbeatSocketTimeout() {
        return heartbeatSocketTimeout;
    }

    public void setHeartbeatSocketTimeout(int heartbeatSocketTimeout) {
        this.heartbeatSocketTimeout = heartbeatSocketTimeout;
    }

    public int getLocalThreshold() {
        return localThreshold;
    }

    public void setLocalThreshold(int localThreshold) {
        this.localThreshold = localThreshold;
    }

    public String getRequiredReplicaSetName() {
        return requiredReplicaSetName;
    }

    public void setRequiredReplicaSetName(String requiredReplicaSetName) {
        this.requiredReplicaSetName = requiredReplicaSetName;
    }

    public boolean isCursorFinalizerEnabled() {
        return cursorFinalizerEnabled;
    }

    public void setCursorFinalizerEnabled(boolean cursorFinalizerEnabled) {
        this.cursorFinalizerEnabled = cursorFinalizerEnabled;
    }
}
