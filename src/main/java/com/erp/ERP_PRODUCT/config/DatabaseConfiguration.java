package com.erp.ERP_PRODUCT.config;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.erp.ERP_PRODUCT.ENUM.DataSourceNamesENUM;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "multiEntityManager",
		transactionManagerRef = "multiTransactionManager",
		basePackages = {"com.erp.ERP_PRODUCT.dao"})
@EntityScan("com.erp.ERP_PRODUCT.entity")
public class DatabaseConfiguration {

	private final String PACKAGE_SCAN= "com.erp.ERP_PRODUCT.entity";
	
	@Primary
	@Bean(name= "dbdtDataSource")
	@ConfigurationProperties("app.datasource.dbdt")
	public DataSource dbdtDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="dbdtnDataSource")
	@ConfigurationProperties("app.datasource.dbdtn")
	public DataSource dbdtnDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="dbdmrDataSource")
	@ConfigurationProperties("app.datasource.dbdmr")
	public DataSource dbdmrDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="multiRoutingDataSource")
	public DataSource multiRoutingDataSource() {
		Map<Object,Object> objTargetDataSources= new HashMap<>();
		objTargetDataSources.put(DataSourceNamesENUM.DT, dbdtDataSource());
		objTargetDataSources.put(DataSourceNamesENUM.DTN, dbdtnDataSource());
		objTargetDataSources.put(DataSourceNamesENUM.DMR, dbdmrDataSource());
		
		MultiRoutingDataSource objmultiRoutingDataSource= new MultiRoutingDataSource();
		objmultiRoutingDataSource.setDefaultTargetDataSource(dbdtDataSource());
		objmultiRoutingDataSource.setTargetDataSources(objTargetDataSources);
		
		return objmultiRoutingDataSource;
	}
	
	@Bean(name="multiEntityManager")
	public LocalContainerEntityManagerFactoryBean multiEntityManager(
			@Qualifier("multiRoutingDataSource") DataSource objMultiRoutingDataSource) 
	{
		
		LocalContainerEntityManagerFactoryBean objLocalContainerEntityManagerFactoryBean= new LocalContainerEntityManagerFactoryBean();
		objLocalContainerEntityManagerFactoryBean.setDataSource(objMultiRoutingDataSource);
		objLocalContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGE_SCAN);
		
		HibernateJpaVendorAdapter objHibernateJpaVendorAdapter= new HibernateJpaVendorAdapter();
		objLocalContainerEntityManagerFactoryBean.setJpaVendorAdapter(objHibernateJpaVendorAdapter);
		objLocalContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
		
		return objLocalContainerEntityManagerFactoryBean;
	}
	
	private Properties hibernateProperties() {
		
		Properties objProperties= new Properties();
		objProperties.put("hibernate.show_sql",true);
		objProperties.put("hibernate.format_sql", true);
		objProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		objProperties.put("hibernate.id.new_generator_mappings", false);
		objProperties.put("hibernate.jdbc.lob.non_contextual_creation", true);
		objProperties.put("hibernate.hbm2ddl.auto", "update");
		
		return objProperties;
	}
	@Bean(name="multiTransactionManager")
	public PlatformTransactionManager multTransactionManager(
	@Qualifier("multiEntityManager") LocalContainerEntityManagerFactoryBean objLCEMFBean) 
	{
		JpaTransactionManager objJpaTransactionManager= new JpaTransactionManager();
		objJpaTransactionManager.setEntityManagerFactory(objLCEMFBean.getObject());
		
		return objJpaTransactionManager;
	}

	@Primary
	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean dbSessionFactory() {
		LocalSessionFactoryBean objLocalSessionFactoryBean= new LocalSessionFactoryBean();
		objLocalSessionFactoryBean.setDataSource(multiRoutingDataSource());
		objLocalSessionFactoryBean.setPackagesToScan(PACKAGE_SCAN);
		objLocalSessionFactoryBean.setHibernateProperties(hibernateProperties());
		
		return objLocalSessionFactoryBean;
	}
}
