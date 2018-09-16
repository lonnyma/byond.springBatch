/* Copyright (c) 2018 白羊人工智能在线技术. All rights reserved.
 * http://www.byond.cn
 */
package cn.byond.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 作用：启动入口
 * 
 * @author burgeen 2018年9月16日
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "cn.byond.batch" })
public class Starter {
	private static final Logger log = LoggerFactory.getLogger(Starter.class);

	/**
	 * 功能：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("starting batch... jobName=[{}]", "importUserJob");
		try {
			SpringApplication application = new SpringApplication(Starter.class);
			ConfigurableApplicationContext ctx = application.run(args);
			JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
			Job jobName = (Job) ctx.getBean("importUserJob");
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("data", System.currentTimeMillis() + "")
					.addString("para2", "value2")
					.toJobParameters();

			jobLauncher.run(jobName, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
