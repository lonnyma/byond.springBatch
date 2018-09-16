/* Copyright (c) 2018 白羊人工智能在线技术. All rights reserved.
 * http://www.byond.cn
 */
package cn.byond.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import cn.byond.batch.Starter;

/**
 * 作用：
 * 
 * @author Alpaca Ma | 2018年9月16日
 */
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.
	 * springframework.batch.core.JobExecution)
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.
	 * springframework.batch.core.JobExecution)
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		String jobName = jobExecution.getJobInstance().getJobName(); // TODO Auto-generated method stub
		if (jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
			log.info("ending batch...job run success! jobName=[{}]",jobName);
		}else {
			jobExecution.stop();
			log.info("ending batch... job run failture, was force stopped! jobName=[{}]",jobName);
		}
		
	}

}
