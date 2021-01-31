package com.redditcooll.workflow.configuration;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.engine.spring.SpringProcessEngineServicesConfiguration;
import org.camunda.bpm.engine.spring.SpringTransactionsProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.CamundaBpmActuatorConfiguration;
import org.camunda.bpm.spring.boot.starter.CamundaBpmConfiguration;
import org.camunda.bpm.spring.boot.starter.CamundaBpmPluginConfiguration;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.camunda.bpm.spring.boot.starter.property.ManagementProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@EnableConfigurationProperties({
        CamundaBpmProperties.class,
        ManagementProperties.class
})
@Import({
        CamundaBpmConfiguration.class,
        CamundaBpmActuatorConfiguration.class,
        CamundaBpmPluginConfiguration.class,
        SpringProcessEngineServicesConfiguration.class
})
@ConditionalOnProperty(prefix = CamundaBpmProperties.PREFIX, name = "enabled", matchIfMissing = true)
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
@Configuration
public class MyConfig {
    @Autowired
    protected DataSource dataSource;
    @Autowired
    protected ProcessEngineConfigurationImpl processEngineConfigurationImpl;
    @Autowired
    protected SpringTransactionsProcessEngineConfiguration springTransactionsProcessEngineConfiguration;
    @Autowired
    protected SpringProcessEngineConfiguration processEngineConfiguration;

    @Bean
    public SpringProcessEngineConfiguration myProcessEngineConfiguration(){
        processEngineConfiguration.setTransactionManager(transactionManager());
        processEngineConfiguration.setJpaHandleTransaction(true);
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setJobExecutorActivate(false);
        processEngineConfiguration.setHistory("full");
        processEngineConfiguration.setJavaSerializationFormatEnabled(true);
        return processEngineConfiguration;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public ProcessEngineFactoryBean myProcessEngine(){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(myProcessEngineConfiguration());
        return processEngineFactoryBean;
    }
}
