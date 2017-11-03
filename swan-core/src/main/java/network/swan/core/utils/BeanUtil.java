package network.swan.core.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
public class BeanUtil {

    /**
     * 两个Bean之间的数据合并
     *
     * @param target      目标Bean
     * @param destination 源Bean
     * @param <M>
     * @throws Exception
     */
    public static <M> void merge(M target, M destination) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());

        // Iterate over all the attributes
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {

            // Only copy writable attributes
            if (descriptor.getWriteMethod() != null) {
                Object originalValue = descriptor.getReadMethod().invoke(target);

                // Only copy values values where the destination values is null
                if (originalValue == null) {
                    Object defaultValue = descriptor.getReadMethod().invoke(
                            destination);
                    descriptor.getWriteMethod().invoke(target, defaultValue);
                }

            }
        }
    }
}
