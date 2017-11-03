package network.swan.frame.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
public class GlobalContextHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_CONTEXTS = new NamedThreadLocal<>("Global Context");

    public static Map<String, Object> currentContextMap() {

        Map<String, Object> context = THREAD_LOCAL_CONTEXTS.get();

        if (context == null) {
            context = initContextMap();
        }

        return context;
    }

    protected static Map<String, Object> initContextMap() {
        Map<String, Object> context = new HashMap<>();
        THREAD_LOCAL_CONTEXTS.set(context);
        return context;
    }

    protected static boolean has(String key) {
        return currentContextMap().containsKey(key);
    }

    protected static <T> T get(String key, Class<T> valueClass) {
        Map<String, Object> currentContextMap = currentContextMap();
        if (currentContextMap.containsKey(key)) {
            Object o = currentContextMap.get(key);
            if (valueClass.isInstance(o)) {
                return valueClass.cast(o);
            }
        }
        return null;
    }

    protected static <T> void set(String key, T value) {
        LOGGER.debug("set {}:{}", key, value);
        currentContextMap().put(key, value);
    }

    protected static void delete(String key) {
        Map<String, Object> currentContextMap = currentContextMap();
        if (currentContextMap.containsKey(key))
            currentContextMap.remove(key);
    }

    public static void removeCurrentContextMap() {
        THREAD_LOCAL_CONTEXTS.remove();
    }


}
