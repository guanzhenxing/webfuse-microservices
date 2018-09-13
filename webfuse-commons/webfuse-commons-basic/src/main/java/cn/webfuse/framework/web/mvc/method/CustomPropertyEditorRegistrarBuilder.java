package cn.webfuse.framework.web.mvc.method;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.PropertyEditorRegistrar;

import java.beans.PropertyEditorSupport;

/**
 * 扩展web初始化的数据绑定配置
 */
public class CustomPropertyEditorRegistrarBuilder {

    //TODO 对时间的转换，参考：https://blog.csdn.net/cz_arel/article/details/50904408 和 https://www.jianshu.com/p/3a5fc2564501

    public static PropertyEditorRegistrar escapeString() {
        return registry -> registry.registerCustomEditor(String.class, new PropertyEditorSupport() {
            public void setAsText(String text) {
                // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
        });
    }


}
