package cn.webfuse.framework.configuration;

import cn.webfuse.framework.security.authentication.AuthenticationExtractorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.List;

/**
 * 自定义权限配置基类
 */
public abstract class AbstractBaseCustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseCustomWebSecurityConfigurer.class);


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 提供从请求获取认证对象的Extractor管理器
     *
     * @return
     */
    @Bean
    public AuthenticationExtractorManager authenticationExtractorManager() {
        return new AuthenticationExtractorManager();
    }

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void configureGlobal(AuthenticationManagerBuilder auth, List<AuthenticationProvider> providers) {
        for (AuthenticationProvider provider : providers) {
            LOGGER.debug("AuthenticationProvider: {}", provider.getClass().getSimpleName());
            auth.authenticationProvider(provider);
        }
        auth.eraseCredentials(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()   // we don't need CSRF because our token is invulnerable
                .exceptionHandling().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // don't create session
                .authorizeRequests().anyRequest().permitAll();

        http.headers().cacheControl();   // disable page caching
    }


}
