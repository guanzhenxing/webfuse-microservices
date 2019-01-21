package cn.webfuse.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webfuse.i18n")
@Data
public class I18nProperties {

    /**
     * 指定的国际化文件目录
     */
    private String baseFolder = "i18n";
    /**
     * 父MessageSource指定的国际化文件
     */
    private String baseName = "messages";
}
