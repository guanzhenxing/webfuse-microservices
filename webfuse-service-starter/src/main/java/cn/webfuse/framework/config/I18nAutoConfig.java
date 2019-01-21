package cn.webfuse.framework.config;

import cn.webfuse.framework.i18n.MessageResourceExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class I18nAutoConfig {

    @Value(value = "${webfuse.i18n.base-folder:i18n}")
    private String baseFolder;

    @Value(value = "${webfuse.i18n.base-name:messages}")
    private String basename;

    @Bean("messageSource")
    public MessageResourceExtension messageResourceExtension() {
        MessageResourceExtension messageSource = new MessageResourceExtension();
        messageSource.setBaseFolder(baseFolder);
        messageSource.setBasename(basename);
        return messageSource;
    }
}
