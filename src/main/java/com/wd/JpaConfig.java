package com.wd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;


/**
 * Created by wd on 2017/7/5.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "jpaEntityManagerFactoryBean",
        transactionManagerRef = "jpaTransactionManager",
        basePackages = {"com.wd.dao","com.wd"}
)
public class JpaConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    DataSource dataSource;



    @Bean(name = "jpaEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                .packages("com.wd.entity")
                .persistenceUnit("persistenceUnit")
                .build();
    }
    private Map<String,String> getVendorProperties(DataSource dataSource){
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
        PlatformTransactionManager manager = new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
        return manager;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        EntityManager manager= entityManagerFactoryBean(builder).getObject().createEntityManager();
        return manager;
    }
}
