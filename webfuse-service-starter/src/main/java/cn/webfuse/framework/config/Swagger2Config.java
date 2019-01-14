package cn.webfuse.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2配置
 *
 * @author Jesen
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 系统版本
     */
    @Value("${webfuse.swagger2.project-version}")
    private String version;

    /**
     * 系统描述
     */
    @Value("${webfuse.swagger2.project-description}")
    private String description;

    /**
     * 系统名称
     */
    @Value("${webfuse.swagger2.project-name}")
    private String projectName;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.projectName + "'s api document")
                .version(version)
                .description(description)
                .build();
    }


}
