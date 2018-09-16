/* Copyright (c) 2018 白羊人工智能在线技术. All rights reserved.
 * http://www.byond.cn
 */
package cn.byond.batch.common.configs;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultJobLoader;
import org.springframework.batch.core.configuration.support.JobLoader;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * 作用：batch 基础类
 * @author Alpaca Ma
 *  | 2018年9月16日
 */
@Configuration
@EnableBatchProcessing
public class BatchBaseConfig {

	@Value("${conf.maxPoolSize}")
	private Integer maxPoolSize;
	@Value("${conf.corePoolSize}")
	private Integer corePoolSize;
	@Autowired
	private JdbcPropertiesConfig jdbcProperties;

	@Bean
	public DataSource jobDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(jdbcProperties.getClassDriverName());
		dataSource.setUrl(jdbcProperties.getUrl());
		dataSource.setUsername(jdbcProperties.getUserName());
		dataSource.setPassword(jdbcProperties.getUserPassword());
		dataSource.setInitialSize(jdbcProperties.getInitConnSize());
		dataSource.setMinIdle(jdbcProperties.getMinConnSize());
		dataSource.setMaxActive(jdbcProperties.getMaxConnSize());
		// 配置获取连接等待超时的时间
		dataSource.setMaxWait(60000);
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("SELECT 'x'");
		// 打开PSCache，并且指定每个连接上PSCache的大小
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		// 配置监控统计拦截的filters
		dataSource.setFilters("stat,log4j");
		return dataSource;
	}
	
	@Bean
	public DruidDataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(jdbcProperties.getClassDriverName());
		dataSource.setUrl(jdbcProperties.getUrl());
		dataSource.setUsername(jdbcProperties.getUserName());
		dataSource.setPassword(jdbcProperties.getUserPassword());
		dataSource.setInitialSize(jdbcProperties.getInitConnSize());
		dataSource.setMinIdle(jdbcProperties.getMinConnSize());
		dataSource.setMaxActive(jdbcProperties.getMaxConnSize());
		// 配置获取连接等待超时的时间
		dataSource.setMaxWait(60000);
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("SELECT 'x'");
		// 打开PSCache，并且指定每个连接上PSCache的大小
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		// 配置监控统计拦截的filters
		dataSource.setFilters("stat,log4j");
		return dataSource;
	}
	
@Bean
public TaskExecutor taskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setMaxPoolSize(maxPoolSize);
	executor.setCorePoolSize(corePoolSize);
	return executor;
}

	@Bean
	public JobRepository jobRepository(DataSource jobDataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(jobDataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());
		return jobRepositoryFactoryBean.getObject();
	}
@Bean 
public PlatformTransactionManager transactionManager(DataSource jobDataSource) {
	DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
	transactionManager.setDataSource(jobDataSource);
	return transactionManager;
}
	
	@Bean
	public SimpleJobLauncher jobLauncher(DataSource jobDataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository(jobDataSource, transactionManager));
		return jobLauncher;
	}
	
	@Bean 
	public JobOperator jobOperator(JobRepository jobRepository,SimpleJobLauncher jobLauncher,JobExplorer jobExplorer,
			JobRegistry jobRegistry) {
		SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
		simpleJobOperator.setJobRepository(jobRepository);
		simpleJobOperator.setJobLauncher(jobLauncher);
		simpleJobOperator.setJobExplorer(jobExplorer);
		simpleJobOperator.setJobRegistry(jobRegistry);		
		return simpleJobOperator;
	}
	
	@Bean 
	public JobExplorer jobExplorer(DataSource jobDataSource,JdbcOperations jdbcOperations) throws Exception {
		JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
		jobExplorerFactoryBean.setDataSource(jobDataSource);
		jobExplorerFactoryBean.setJdbcOperations(jdbcOperations);
		return jobExplorerFactoryBean.getObject();
	}
	
@Bean
public JdbcOperations jdbcOperations(DataSource jobDataSource) {
	JdbcTemplate  jdbcTemplate = new JdbcTemplate();
	jdbcTemplate.setDataSource(jobDataSource);
	return jdbcTemplate;
}
	@Bean
	public JobLoader jobLoadar(JobRegistry jobRegistry) {
		return new DefaultJobLoader(jobRegistry);
	}
	
	@Bean 
	public JobRegistry jobRegistry() {
		return new MapJobRegistry();
	}
}
