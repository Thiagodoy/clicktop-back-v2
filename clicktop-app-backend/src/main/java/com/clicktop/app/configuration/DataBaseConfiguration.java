package com.clicktop.app.configuration;


import com.clicktop.app.properties.ClickTopProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author Thiago H. Godoy <thiagodoy@hotmail.com>
 */
@EnableJpaRepositories(basePackages = {"com.core.behavior.repository"},
         entityManagerFactoryRef = "behaviorEntityManager", 
        transactionManagerRef = "behaviorTransactionManager") 
@Configuration
public class DataBaseConfiguration implements EnvironmentAware {

    @Autowired
    private ClickTopProperties clicktopProperties;

    private Environment environment;
    
    @org.springframework.beans.factory.annotation.Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    public void setEnvironment(Environment e) {
        this.environment = e;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean behaviorEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceBehavior());
        em.setPackagesToScan(
          new String[] { "com.clicktop.app.model", "com.clicktop.app.repository" });
 
        System.out.println("hibernate.dialect" +  environment.getProperty("hibernate.dialect"));
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
          "org.hibernate.dialect.MySQL5InnoDBDialect");
        em.setJpaPropertyMap(properties);
 
        return em;
    }
    
    @Primary
    @Bean
    public DataSource dataSourceBehavior() {

        DataSource dataSource;
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setInitializationFailTimeout(-1);
        
        hikariConfig.setMinimumIdle(clicktopProperties.getDatasource().getMinimumIdle());
        hikariConfig.setMaximumPoolSize((clicktopProperties.getDatasource().getMaximumPoolSize()));
        hikariConfig.setValidationTimeout((clicktopProperties.getDatasource().getValidationTimeout()));
        hikariConfig.setIdleTimeout((clicktopProperties.getDatasource().getIdleTimeout()));
        hikariConfig.setConnectionTimeout((clicktopProperties.getDatasource().getConnectionTimeout()));
        hikariConfig.setAutoCommit((clicktopProperties.getDatasource().isAutoCommit()));        
        hikariConfig.setJdbcUrl(clicktopProperties.getDatasource().getSqlserver().getDataUrl());
        hikariConfig.setUsername(clicktopProperties.getDatasource().getSqlserver().getDataSourceUser());
        hikariConfig.setPassword(clicktopProperties.getDatasource().getSqlserver().getDataSourcePassword());        
        hikariConfig.setPoolName("ClicktopPool");
        hikariConfig.setConnectionTestQuery(clicktopProperties.getDatasource().getSqlserver().getConnectionTestQuery());
        dataSource = new HikariDataSource(hikariConfig);
        
        Logger.getLogger(DataBaseConfiguration.class.getName()).log(Level.INFO, "Profile -> " + activeProfile );

        return dataSource;
    }
    
    @Primary
    @Bean
    public PlatformTransactionManager behaviorTransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          behaviorEntityManager().getObject());
        return transactionManager;
    }

}
