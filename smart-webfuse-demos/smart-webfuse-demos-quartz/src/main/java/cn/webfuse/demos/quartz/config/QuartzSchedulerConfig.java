package cn.webfuse.demos.quartz.config;

import cn.webfuse.demos.quartz.job.Job1;
import cn.webfuse.demos.quartz.job.Job2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
@Slf4j
public class QuartzSchedulerConfig {

    private static final String QUARTZ_PROPERTIES_NAME = "/quartz.properties";

    @Autowired
    private DataSource dataSource;


    /**
     * 配置JobFactory
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, CronTrigger[] cronTrigger, JobDetail[] jobDetails) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        try {
            factoryBean.setQuartzProperties(quartzProperties());
            factoryBean.setDataSource(dataSource);
            factoryBean.setOverwriteExistingJobs(true);

            factoryBean.setJobFactory(jobFactory);
            factoryBean.setTriggers(cronTrigger);
            factoryBean.setJobDetails(jobDetails);
        } catch (Exception e) {
            log.error("加载 {} 配置文件失败.", QUARTZ_PROPERTIES_NAME, e);
            throw new RuntimeException("加载配置文件失败", e);
        }

        return factoryBean;
    }

    /**
     * 获得配置文件
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_NAME));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    //*********************************************************************************************************//

    @Bean(name = "job1Trigger")
    public CronTriggerFactoryBean job1Trigger(@Qualifier("job1Detail") JobDetail jobDetail) {
        return createCronTrigger(jobDetail, "0/15 * * * * ?");
    }

    @Bean(name = "job1Detail")
    public JobDetailFactoryBean job1Detail() {
        return createJobDetail(Job1.class);
    }

    @Bean(name = "job2Trigger")
    public CronTriggerFactoryBean job2Trigger(@Qualifier("job2Detail") JobDetail jobDetail) {
        return createCronTrigger(jobDetail, "0/5 * * * * ?");
    }

    @Bean(name = "job2Detail")
    public JobDetailFactoryBean job2Detail() {
        return createJobDetail(Job2.class);
    }

    //*********************************************************************************************************//

    private static JobDetailFactoryBean createJobDetail(Class jobClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        // job has to be durable to be stored in DB:
        factoryBean.setDurability(true);
        return factoryBean;
    }

    // Use this method for creating cron triggers instead of simple triggers:
    private static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }

    /**
     * Adds autowiring support to quartz jobs.
     * Created by david on 2015-01-20.
     *
     * @see https://gist.github.com/jelies/5085593
     */
    class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }
    }


}
