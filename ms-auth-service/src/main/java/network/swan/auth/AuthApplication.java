package network.swan.auth;

import network.swan.frame.annotation.IgnoreDuringSpringScan;
import network.swan.auth.models.Account;
import network.swan.auth.models.Role;
import network.swan.auth.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan(excludeFilters = @ComponentScan.Filter(IgnoreDuringSpringScan.class))
public class AuthApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }


    @Bean
    CommandLineRunner init(
            AccountService accountService
    ) {
        return (evt) -> Arrays.asList(
                "user,admin,john,robert,ana".split(",")).forEach(
                username -> {
                    Account acct = new Account();
                    acct.setUsername(username);
                    acct.setPassword("password");
                    acct.setFirstName(username);
                    acct.setLastName("LastName");
                    acct.grantAuthority(Role.ROLE_USER);
                    if (username.equals("admin"))
                        acct.grantAuthority(Role.ROLE_ADMIN);
                    accountService.registerUser(acct);
                }
        );
    }

}