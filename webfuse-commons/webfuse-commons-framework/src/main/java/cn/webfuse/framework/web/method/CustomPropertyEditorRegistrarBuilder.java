package cn.webfuse.framework.web.method;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展web初始化的数据绑定配置
 */
public class CustomPropertyEditorRegistrarBuilder {

    //TODO 对时间的转换，参考：https://blog.csdn.net/cz_arel/article/details/50904408 和 https://www.jianshu.com/p/3a5fc2564501

    public static PropertyEditorRegistrar escapeString() {
        return registry -> registry.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
        });
    }


}


//使用方法如下：
//    @Bean
//    public ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
//        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
//
//        List<PropertyEditorRegistrar> propertyEditorRegistrars = new ArrayList();
//        propertyEditorRegistrars.add(CustomPropertyEditorRegistrarBuilder.escapeString());
//
//        PropertyEditorRegistrar[] array = new PropertyEditorRegistrar[propertyEditorRegistrars.size()];
//        propertyEditorRegistrars.toArray(array);
//
//        initializer.setPropertyEditorRegistrars(array);
//        return initializer;
//    }
