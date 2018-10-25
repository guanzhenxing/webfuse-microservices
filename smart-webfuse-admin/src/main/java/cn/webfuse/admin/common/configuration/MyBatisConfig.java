package cn.webfuse.admin.common.configuration;

import cn.webfuse.admin.upms.mapper.ActionsMapper;
import cn.webfuse.data.mybatis.utils.ProviderHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public ProviderHelper providerHelper() {
        ProviderHelper providerHelper = new ProviderHelper();
        providerHelper.registerMapper(ActionsMapper.class);
        return providerHelper;
    }
}
