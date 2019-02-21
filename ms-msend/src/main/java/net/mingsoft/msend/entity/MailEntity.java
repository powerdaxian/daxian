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

import com.mingsoft.base.constant.e.BaseEnum;
import com.mingsoft.base.entity.BaseEntity;
import java.util.Date;

 /**
 * 邮件实体
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-24 14:41:18<br/>
 * 历史修订：<br/>
 */
public class MailEntity extends BaseEntity {

	private static final long serialVersionUID = 1503556878920L;
	
	/**
	 * 应用编号
	 */
	private Integer appId; 
	/**
	 * 邮件类型
	 */
	private String mailType; 
	/**
	 * 账号
	 */
	private String mailName; 
	/**
	 * 
	 */
	private String mailPassword; 
	/**
	 * 
	 */
	private Integer mailPort; 
	/**
	 * 服务器
	 */
	private String mailServer; 
	/**
	 * 
	 */
	private String mailForm; 
	/**
	 * 
	 */
	private String mailFormName; 
	/**
	 * 0启用 1禁用
	 */
	private Integer mailEnable; 
	
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
	 * 设置邮件类型
	 */
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	/**
	 * 获取邮件类型
	 */
	public String getMailType() {
		return this.mailType;
	}
	
	/**
	 * 设置账号
	 */
	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	/**
	 * 获取账号
	 */
	public String getMailName() {
		return this.mailName;
	}
	
	/**
	 * 设置
	 */
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	/**
	 * 获取
	 */
	public String getMailPassword() {
		return this.mailPassword;
	}
	
	/**
	 * 设置
	 */
	public void setMailPort(Integer mailPort) {
		this.mailPort = mailPort;
	}

	/**
	 * 获取
	 */
	public Integer getMailPort() {
		return this.mailPort;
	}
	
	/**
	 * 设置服务器
	 */
	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	/**
	 * 获取服务器
	 */
	public String getMailServer() {
		return this.mailServer;
	}
	
	/**
	 * 设置
	 */
	public void setMailForm(String mailForm) {
		this.mailForm = mailForm;
	}

	/**
	 * 获取
	 */
	public String getMailForm() {
		return this.mailForm;
	}
	
	/**
	 * 设置
	 */
	public void setMailFormName(String mailFormName) {
		this.mailFormName = mailFormName;
	}

	/**
	 * 获取
	 */
	public String getMailFormName() {
		return this.mailFormName;
	}
	
	/**
	 * 设置0启用 1禁用
	 */
	public void setMailEnable(Integer mailEnable) {
		this.mailEnable = mailEnable;
	}

	/**
	 * 获取0启用 1禁用
	 */
	public Integer getMailEnable() {
		return this.mailEnable;
	}
	public static class MailType {
		public static final String SENDCLOUD = "sendcloud";
	}
	public static enum SendTypeEnum implements BaseEnum {
		TEXT("text"), HTML("html");
		
		/**
		 * 枚举类型
		 */
		Object code;

		/**
		 * 构造方法
		 * @param code 传入的枚举类型
		 */
		SendTypeEnum(Object code) {
			this.code = code;
		}

		@Override
		public int toInt() {
			// TODO Auto-generated method stub
			return Integer.parseInt(this.code.toString());
		}

	}
}
