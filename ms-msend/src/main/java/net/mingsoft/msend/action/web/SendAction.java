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
 */package net.mingsoft.msend.action.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mingsoft.util.JsonUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.msend.biz.IMailBiz;
import net.mingsoft.msend.constant.ModelCode;
import net.mingsoft.msend.entity.MailEntity;
import net.mingsoft.msend.util.SendUtil;

/**
 * 邮件管理控制层
 * 
 * @author 伍晶晶
 * @version 版本号：0.0<br/>
 *          创建日期：2017-8-24 14:41:18<br/>
 *          历史修订：<br/>
 */
@Controller("sendAction")
@RequestMapping("/msend")
public class SendAction extends net.mingsoft.msend.action.BaseAction {

	/**
	 * 注入邮件业务层
	 */
	@Autowired
	private IMailBiz mailBiz;

	/**
	 * 自由调用邮箱
	 * 
	 * @param modelCode
	 *            模块编码（AES加密过的）
	 * @param content 消息内容
	 * @param type 消息类型 mail|sms
	 * @param receive 接收者（邮箱或手机号）
	 * @param request
	 *            HttpServletRequest对象
	 * @param response 
	 *            HttpServletResponse对象 
	 */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "send", method = RequestMethod.POST)
	public void send(HttpServletRequest request, HttpServletResponse response) {
		String receive = request.getParameter("receive");
		String modelCode = request.getParameter("modelCode");
		String content = request.getParameter("content");
		String type = request.getParameter("type"); 
		String sendType = "html";//request.getParameter("sendType"); 
		
		
		// 验证模块编码是否为空
		if (StringUtil.isBlank(modelCode)) {
			this.outJson(response, ModelCode.SEND, false,
					this.getResString("err.error", this.getResString("model.code")));
			return;
		}

		String _modelCode = this.decryptByAES(request, modelCode);
		// 将邮箱地址压如String数组
		if (_modelCode == null) {
			this.outJson(response, ModelCode.SEND, false,
					this.getResString("err.error", this.getResString("model.code")));
			return;
		}
		Map params = null;
		try {
			params = JsonUtil.getJsonToObject(content, Map.class);
		} catch (Exception e) {
			LOG.error("content 参数不正确");
			this.outJson(response, ModelCode.SEND, false,
					this.getResString("err.error", "content"));
			return;
		}
		// 发送邮箱
		boolean status = SendUtil.send(_modelCode, receive, params, type,MailEntity.SendTypeEnum.HTML);
		this.outJson(response, null, status);
	}
	
}
