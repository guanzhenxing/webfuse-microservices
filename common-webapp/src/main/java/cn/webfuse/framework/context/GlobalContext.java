package cn.webfuse.framework.context;

import org.springframework.beans.factory.annotation.Value;

/**
 * 全局的上下文
 */
public class GlobalContext {

    @Value("${spring.profiles.active}")
    private static String env;

    @Value("${webfuse.debug-mode:false}")
    private static boolean debugMode;

    /**
     * 判断是否是debug模式
     *
     * @return
     */
    public static boolean isDebugMode() {
        return debugMode;
    }

    /**
     * 获得环境
     *
     * @return
     */
    public static String getEnv() {
        return env;
    }


}
