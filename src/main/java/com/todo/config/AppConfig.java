package com.todo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.todo")
@EnableJpaRepositories("com.todo.repository")
@EnableTransactionManagement
public class AppConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
        .addDefaultScripts().setName("testdb").build();
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource());
    factory.setPersistenceUnitName("testdb");
    factory.setPackagesToScan("com.todo.domain");
    factory.setJpaVendorAdapter(jpaAdapter());
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean
  public JpaVendorAdapter jpaAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.HSQL);

    return adapter;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory());
  }

  @Bean
  public HibernateExceptionTranslator exceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

}
