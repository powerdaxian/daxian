/**
The MIT License (MIT) * Copyright (c) 2018 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */package net.mingsoft.msend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mingsoft.base.entity.BaseEntity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

 /**
 * 发送日志实体
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-24 14:41:18<br/>
 * 历史修订：<br/>
 */
public class LogEntity extends BaseEntity {

	private static final long serialVersionUID = 1503556878953L;
	
	/**
	 * 
	 */
	private Integer logId; 
	/**
	 * 应用编号
	 */
	private Integer appId; 
	/**
	 * 时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDatetime; 
	/**
	 * 接收内容
	 */
	private String logContent; 
	/**
	 * 接收人
	 */
	private String logReceive; 
	/**
	 * 日志类型0邮件1短信
	 */
	private Integer logType; 
	
		
	/**
	 * 设置
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	/**
	 * 获取
	 */
	public Integer getLogId() {
		return this.logId;
	}
	
	/**
	 * 设置应用编号
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	/**
	 * 获取应用编号
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置时间
	 */
	public void setLogDatetime(Date logDatetime) {
		this.logDatetime = logDatetime;
	}

	/**
	 * 获取时间
	 */
	public Date getLogDatetime() {
		return this.logDatetime;
	}
	
	/**
	 * 设置接收内容
	 */
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	/**
	 * 获取接收内容
	 */
	public String getLogContent() {
		return this.logContent;
	}
	
	/**
	 * 设置接收人
	 */
	public void setLogReceive(String logReceive) {
		this.logReceive = logReceive;
	}

	/**
	 * 获取接收人
	 */
	public String getLogReceive() {
		return this.logReceive;
	}
	
	/**
	 * 设置日志类型0邮件1短信
	 */
	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	/**
	 * 获取日志类型0邮件1短信
	 */
	public Integer getLogType() {
		return this.logType;
	}
	
}
