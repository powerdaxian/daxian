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
 */package net.mingsoft.msend.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.util.StringUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.SpringUtil;
import net.mingsoft.msend.biz.ILogBiz;
import net.mingsoft.msend.biz.IMailBiz;
import net.mingsoft.msend.biz.ISmsBiz;
import net.mingsoft.msend.biz.ITemplateBiz;
import net.mingsoft.msend.constant.e.SendEnum;
import net.mingsoft.msend.entity.LogEntity;
import net.mingsoft.msend.entity.MailEntity;
import net.mingsoft.msend.entity.SmsEntity;
import net.mingsoft.msend.entity.TemplateEntity;

public class SendUtil {

	private static final Logger LOG = Logger.getLogger(SendUtil.class);

	/**
	 * 发送
	 * 
	 * @param code
	 *            模板编码
	 * @param toUser
	 *            接收用户
	 * @param values
	 *            替换的内容参数
	 * @param type
	 *            发送类型sms|mail|
	 * @return
	 */
	public static boolean send(String code, String receive, Map<String, String> values, String type,MailEntity.SendTypeEnum sendType) {
		ITemplateBiz templateBiz = (ITemplateBiz) SpringUtil.getBean(ITemplateBiz.class);
		TemplateEntity template = new TemplateEntity();
		template.setTemplateCode(code);
		template.setAppId(BasicUtil.getAppId());
		template = (TemplateEntity) templateBiz.getEntity(template);
		if (template == null) {
			LOG.error("模板不存在");
			return false;
		}
		String mailContent = template.getTemplateMail();
		if(type.equals(SendEnum.MAIL.toString())){
			if (template.getTemplateId() > 0) {
				if (values != null) {
					Iterator it = values.keySet().iterator();
					while (it.hasNext()) {
						String key = it.next() + "";
						if (values.get(key) instanceof String) {
							mailContent = mailContent.replaceAll("\\{" + key + "/\\}", values.get(key));
						}
					}
				}
				LOG.debug(code + "send  to:" + receive + " content:" + mailContent);
				// 如果实体不为空就获取邮箱模板的标题和内容一起发送指定的邮箱地址
			} else {
				LOG.error("发送模板不存在");
				return false;
			}
		}
		if (type.equalsIgnoreCase(SendEnum.MAIL.toString())) {
			return SendUtil.sendMail(sendType, template.getTemplateTitle(), mailContent, receive.split(","),
					template);
		} else if (type.equalsIgnoreCase(SendEnum.SMS.toString())) {
			return SendUtil.sendSms(code, receive, values, template);
		}
		return true;
	}

	/**
	 * 发送邮件
	 * 
	 * @param mailType
	 *            邮件类型(MailEntity.TEXT MailEntity.HTML)
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param toUser
	 *            接收用户
	 * @param template
	 */
	private static boolean sendMail(MailEntity.SendTypeEnum sendType, String title, String content, String[] toUser,
			TemplateEntity template) {
		IMailBiz mailBiz = (IMailBiz) SpringUtil.getBean(IMailBiz.class);
		ILogBiz logBiz = (ILogBiz) SpringUtil.getBean(ILogBiz.class);
		MailEntity mail = (MailEntity) mailBiz.getEntity(BasicUtil.getAppId());
		LogEntity log = new LogEntity();
		log.setAppId(BasicUtil.getAppId());
		if (mail == null) {
			LOG.error("没有配置邮件服务器");
			return false;
		}
		if (mail.getMailType()!= null && mail.getMailType().equals(MailEntity.MailType.SENDCLOUD)) {
			try {
				String _toUser = "";
				for (int i = 0; i < toUser.length; i++) {
					if (StringUtil.isEmail(toUser[i])) {
						_toUser += toUser[i];
						if (i < toUser.length) {
							_toUser += ";";
						}
					}
				}
				boolean flag = false;
				flag = SendcloudUtil.sendMail(mail.getMailName(), mail.getMailPassword(), mail.getMailForm(),
						mail.getMailFormName(), _toUser, title, content);
				if(flag){
					log.setAppId(BasicUtil.getAppId());
					log.setLogType(SendEnum.MAIL.toInt());
					log.setLogDatetime(new Date());
					log.setLogContent("mail类型"+template.getTemplateCode());
					log.setLogReceive(_toUser.replace(";",""));
					logBiz.saveEntity(log);
				}
				return flag;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (sendType == MailEntity.SendTypeEnum.TEXT) {
				MailUtil.sendText(mail.getMailServer(), mail.getMailPort(), mail.getMailName(), mail.getMailPassword(),
						title, content, toUser);
			} else if (sendType == MailEntity.SendTypeEnum.HTML) {
				MailUtil.sendHtml(mail.getMailServer(), mail.getMailPort(), mail.getMailName(), mail.getMailPassword(),mail.getMailFormName(),
						title, content, toUser);
			}
			for(int i = 0; i < toUser.length ; i++){
				log.setLogType(SendEnum.MAIL.toInt());
				log.setLogDatetime(new Date());
				log.setLogContent("mail类型");
				log.setLogReceive(toUser[i]);
				logBiz.saveEntity(log);
			}
			return true;
		}
		return false;

	}

	/**
	 * 
	 * @param code
	 *            模块编号，
	 * @param phone
	 *            接收手机号，多个手机号逗号隔开
	 * @param values
	 *            根据values.key值替换替换模版里面内容的{key/}，
	 * @param template
	 *            模板内容
	 */
	private static boolean sendSms(String code, String phone, Map<String, String> values, TemplateEntity template) {
		ISmsBiz smsBiz = (ISmsBiz) SpringUtil.getBean(ISmsBiz.class);
		ILogBiz logBiz = (ILogBiz) SpringUtil.getBean(ILogBiz.class);
		SmsEntity sms = (SmsEntity) smsBiz.getEntity(BasicUtil.getAppId());
		if(ObjectUtil.isNull(sms)){
			LOG.error("没有设置短信配置");
			return false;
		}
		if (sms.getSmsType().equals(MailEntity.MailType.SENDCLOUD)) {
			String templateId = sms.getSmsSendUrl();
			if (!StringUtil.isInteger(templateId)) {
				LOG.error("sendcloud 的模板id不正确");
				return false;
			}
			LOG.debug(code + "send sms to:" + phone + " 模板ID:" + templateId);
			try {
				boolean flag = false;
				String[] phones = phone.split(",");
				for (int i = 0; i < phones.length; i++) {
					flag = SendcloudUtil.sendSms(sms.getSmsUsername(), sms.getSmsPassword(),
							Integer.parseInt(templateId), "0", phones[i], JSONArray.toJSONString(values));
					if (flag) {
						LogEntity log = new LogEntity();
						log.setAppId(BasicUtil.getAppId());
						log.setLogType(SendEnum.SMS.toInt());
						log.setLogDatetime(new Date());
						log.setLogContent("模板编号:" + template.getTemplateCode());
						log.setLogReceive(phones[i]);
						logBiz.saveEntity(log);
					} else {
						LOG.error("发送失败：" +  phones[i]);
						break;
					}
				}
				return flag;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // 普通通过post 地址的方式请求
			String mailContent = template.getTemplateSms();
			 if (values != null) {
				if (template.getTemplateId() > 0) {
					Iterator it = values.keySet().iterator();
					while (it.hasNext()) {
						String key = it.next() + "";
						if (values.get(key) instanceof String) {
							mailContent = mailContent.replaceAll("\\{" + key + "/\\}", values.get(key));
						}
					}
				} else {
					LOG.error("发送模板不存在");
					return false;
				}
			 }
			 Map params = new HashMap();
			 params.put("content", mailContent);
			 params.put("mobile", phone);
			 String result = HttpUtil.post(sms.getSmsSendUrl(),params);
			 LOG.error("消息发送结果"+result);
			 String[] phones = phone.split(",");
				for (int i = 0; i < phones.length; i++) {
					LogEntity log = new LogEntity();
					log.setAppId(BasicUtil.getAppId());
					log.setLogType(SendEnum.SMS.toInt());
					log.setLogDatetime(new Date());
					log.setLogContent("模板编号:" + template.getTemplateCode());
					log.setLogReceive(phones[i]);
					logBiz.saveEntity(log);
				}
			 return true;
		}

		return false;
	}
}
