

## 配置如下
```java
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"io.dynamax.gateway.security.*"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * 提供从请求获取认证对象的Extractor管理器
     *
     * @return
     */
    @Bean
    public AuthenticationExtractorManager authenticationExtractorManager() {
        return new AuthenticationExtractorManager();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        return new TokenAuthenticationFilter(authenticationManagerBean(), authenticationExtractorManager());
    }


}

```