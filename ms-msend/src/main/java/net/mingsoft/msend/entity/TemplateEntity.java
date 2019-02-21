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

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.AESUtil;
import com.mingsoft.util.StringUtil;

import java.util.Date;

 /**
 * 发送消息模板表实体
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-24 17:52:29<br/>
 * 历史修订：<br/>
 */
public class TemplateEntity extends BaseEntity {

	private static final long serialVersionUID = 1503568349408L;
	
	/**
	 * 编号
	 */
	private Integer templateId; 
	/**
	 * 模块编号
	 */
	private Integer modelId; 
	/**
	 * 应用编号
	 */
	private Integer appId; 
	/**
	 * 标题
	 */
	private String templateTitle; 
	/**
	 * 
	 */
	private String templateMail; 
	/**
	 * 
	 */
	private String templateSms; 
	/**
	 * 邮件模块代码
	 */
	private String templateCode; 
	
	/**
	 * 邮件模块代码加密
	 */
	private String templateCodeAes;
	
	
	public String getTemplateCodeAes() {
		templateCodeAes = AESUtil.encrypt(this.templateCode+"", StringUtil.Md5(this.getAppId()+"").substring(16));
		return templateCodeAes;
	}

	public void setTemplateCodeAes(String templateCodeAes) {
		this.templateCodeAes = templateCodeAes;
	}

	/**
	 * 设置编号
	 */
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取编号
	 */
	public Integer getTemplateId() {
		return this.templateId;
	}
	
	/**
	 * 设置模块编号
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	/**
	 * 获取模块编号
	 */
	public Integer getModelId() {
		return this.modelId;
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
	 * 设置标题
	 */
	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}

	/**
	 * 获取标题
	 */
	public String getTemplateTitle() {
		return this.templateTitle;
	}
	
	/**
	 * 设置
	 */
	public void setTemplateMail(String templateMail) {
		this.templateMail = templateMail;
	}

	/**
	 * 获取
	 */
	public String getTemplateMail() {
		return this.templateMail;
	}
	
	/**
	 * 设置
	 */
	public void setTemplateSms(String templateSms) {
		this.templateSms = templateSms;
	}

	/**
	 * 获取
	 */
	public String getTemplateSms() {
		return this.templateSms;
	}
	
	/**
	 * 设置邮件模块代码
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * 获取邮件模块代码
	 */
	public String getTemplateCode() {
		return this.templateCode;
	}
	
}
