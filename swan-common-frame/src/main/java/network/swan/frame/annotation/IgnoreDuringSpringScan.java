package network.swan.frame.annotation;



/**
 * 在Spring自动扫描的时候忽略。
 *
 * 使用：
 * <pre>
 *  {@literal @}SpringBootApplication
    {@literal @}ComponentScan(excludeFilters = @ComponentScan.Filter(IgnoreDuringSpringScan.class))
    public class Application {
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
    }
 * </pre>
 *
 * Created by guanzhenxing on 2017/8/7.
 */
public @interface IgnoreDuringSpringScan {
}
