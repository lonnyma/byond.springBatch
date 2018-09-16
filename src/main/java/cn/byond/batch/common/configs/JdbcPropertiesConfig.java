/* Copyright (c) 2018 白羊人工智能在线技术. All rights reserved.
 * http://www.byond.cn
 */
package cn.byond.batch.common.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 作用：数据库连接池参数配置
 * 
 * @author burgeen 2018年9月16日
 */
@Component
@ConfigurationProperties(prefix="mjdbc",ignoreUnknownFields=false)
@PropertySource("classpath:conf/jdbc.properties")
public class JdbcPropertiesConfig {

	private String classDriverName;
	private String url;
	private String userName;
	private String userPassword;
	private Integer initConnSize;
	private Integer minConnSize;
	private Integer maxConnSize;
	private String maxFreeTime;
	private String getConnTime;
	private String acquireIncrement;
	private String maxIdleTimeExcessConnections;
	private boolean autoCommitOnClose;
	/**
	 * @return the classDriverName
	 */
	public String getClassDriverName() {
		return classDriverName;
	}
	/**
	 * @param classDriverName the classDriverName to set
	 */
	public void setClassDriverName(String classDriverName) {
		this.classDriverName = classDriverName;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the initConnSize
	 */
	public Integer getInitConnSize() {
		return initConnSize;
	}
	/**
	 * @param initConnSize the initConnSize to set
	 */
	public void setInitConnSize(Integer initConnSize) {
		this.initConnSize = initConnSize;
	}
	/**
	 * @return the minConnSize
	 */
	public Integer getMinConnSize() {
		return minConnSize;
	}
	/**
	 * @param minConnSize the minConnSize to set
	 */
	public void setMinConnSize(Integer minConnSize) {
		this.minConnSize = minConnSize;
	}
	/**
	 * @return the maxConnSize
	 */
	public Integer getMaxConnSize() {
		return maxConnSize;
	}
	/**
	 * @param maxConnSize the maxConnSize to set
	 */
	public void setMaxConnSize(Integer maxConnSize) {
		this.maxConnSize = maxConnSize;
	}
	/**
	 * @return the maxFreeTime
	 */
	public String getMaxFreeTime() {
		return maxFreeTime;
	}
	/**
	 * @param maxFreeTime the maxFreeTime to set
	 */
	public void setMaxFreeTime(String maxFreeTime) {
		this.maxFreeTime = maxFreeTime;
	}
	/**
	 * @return the getConnTime
	 */
	public String getGetConnTime() {
		return getConnTime;
	}
	/**
	 * @param getConnTime the getConnTime to set
	 */
	public void setGetConnTime(String getConnTime) {
		this.getConnTime = getConnTime;
	}
	/**
	 * @return the acquireIncrement
	 */
	public String getAcquireIncrement() {
		return acquireIncrement;
	}
	/**
	 * @param acquireIncrement the acquireIncrement to set
	 */
	public void setAcquireIncrement(String acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}
	/**
	 * @return the maxIdleTimeExcessConnections
	 */
	public String getMaxIdleTimeExcessConnections() {
		return maxIdleTimeExcessConnections;
	}
	/**
	 * @param maxIdleTimeExcessConnections the maxIdleTimeExcessConnections to set
	 */
	public void setMaxIdleTimeExcessConnections(String maxIdleTimeExcessConnections) {
		this.maxIdleTimeExcessConnections = maxIdleTimeExcessConnections;
	}
	/**
	 * @return the autoCommitOnClose
	 */
	public boolean isAutoCommitOnClose() {
		return autoCommitOnClose;
	}
	/**
	 * @param autoCommitOnClose the autoCommitOnClose to set
	 */
	public void setAutoCommitOnClose(boolean autoCommitOnClose) {
		this.autoCommitOnClose = autoCommitOnClose;
	}
	
	
}
