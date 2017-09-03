package network.swan.uaa.configuration;

import network.swan.uaa.models.Role;
import network.swan.uaa.service.AccountService;
import network.swan.uaa.service.CustomerTokenService;
import network.swan.uaa.service.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


/**
 * 配置认证
 * Created by guanzhenxing on 2017/9/3.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public UserDetailsService userDetailsService() {
        return new AccountService();
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // we're allowing access to the token only for clients with 'ROLE_TRUSTED_CLIENT' authority
                .tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("trusted-app")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .authorities(Role.ROLE_TRUSTED_CLIENT.toString())
                .scopes("read", "write")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(30000)
                .refreshTokenValiditySeconds(86400)
                .secret("secret")
                .and()
                .withClient("register-app")
                .authorizedGrantTypes("client_credentials")
                .authorities(Role.ROLE_REGISTER.toString())
                .scopes("registerUser")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(10)
                .refreshTokenValiditySeconds(10)
                .secret("secret");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("dynamax");
        return converter;
    }

    @Autowired
    private TokenBlackListService blackListService;

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        CustomerTokenService tokenService = new CustomerTokenService(blackListService);
        tokenService.setTokenStore(tokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setTokenEnhancer(accessTokenConverter());
        return tokenService;
    }


}
