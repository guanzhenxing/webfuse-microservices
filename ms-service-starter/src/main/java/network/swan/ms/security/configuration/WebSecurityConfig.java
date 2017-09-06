package network.swan.ms.security.configuration;

import network.swan.ms.security.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    /**
     * 设置权限管理
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AbstractSecurityInterceptor securityInterceptor() throws Exception {
        CustomFilterSecurityInterceptor securityInterceptor = new CustomFilterSecurityInterceptor();
        securityInterceptor.setSecurityMetadataSource(securityMetadataSource());
        securityInterceptor.setAccessDecisionManager(accessDecisionManager());
        securityInterceptor.setAuthenticationManager(authenticationManager);
        return securityInterceptor;
    }

    /**
     * 实例化权限管理决断器
     *
     * @return
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new CustomerAccessDecisionManager();
    }

    /**
     * 实例化权限配置资源管理器
     *
     * @return
     */
    public SecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource();
    }


}
