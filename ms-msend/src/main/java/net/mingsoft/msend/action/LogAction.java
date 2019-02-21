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
 */package net.mingsoft.msend.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.util.StringUtil;

import net.mingsoft.base.util.JSONObject;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.msend.biz.ILogBiz;
import net.mingsoft.msend.entity.LogEntity;
	
/**
 * 发送日志管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-24 14:41:18<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/msend/log")
public class LogAction extends net.mingsoft.msend.action.BaseAction{
	
	/**
	 * 注入发送日志业务层 
	 */	
	@Autowired
	private ILogBiz logBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/msend/log/index");
	}
	
	/**
	 * 查询发送日志列表
	 * @param log 发送日志实体
	 * <i>log参数包含字段信息参考：</i><br/>
	 * logId <br/>
	 * appId 应用编号<br/>
	 * logDatetime 时间<br/>
	 * logContent 接收内容<br/>
	 * logReceive 接收人<br/>
	 * logType 日志类型0邮件1短信<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * logId: <br/>
	 * appId: 应用编号<br/>
	 * logDatetime: 时间<br/>
	 * logContent: 接收内容<br/>
	 * logReceive: 接收人<br/>
	 * logType: 日志类型0邮件1短信<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute LogEntity log,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List logList = logBiz.query(log);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(logList,(int)BasicUtil.endPage(logList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面log_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute LogEntity log,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(log.getLogId() != null){
			BaseEntity logEntity = logBiz.getEntity(log.getLogId());			
			model.addAttribute("logEntity",logEntity);
		}
		
		return view ("/msend/log/form");
	}
	
	/**
	 * 获取发送日志
	 * @param log 发送日志实体
	 * <i>log参数包含字段信息参考：</i><br/>
	 * logId <br/>
	 * appId 应用编号<br/>
	 * logDatetime 时间<br/>
	 * logContent 接收内容<br/>
	 * logReceive 接收人<br/>
	 * logType 日志类型0邮件1短信<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * logId: <br/>
	 * appId: 应用编号<br/>
	 * logDatetime: 时间<br/>
	 * logContent: 接收内容<br/>
	 * logReceive: 接收人<br/>
	 * logType: 日志类型0邮件1短信<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute LogEntity log,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(log.getLogId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("log.id")));
			return;
		}
		LogEntity _log = (LogEntity)logBiz.getEntity(log.getLogId());
		this.outJson(response, _log);
	}
	
	/**
	 * 保存发送日志实体
	 * @param log 发送日志实体
	 * <i>log参数包含字段信息参考：</i><br/>
	 * logId <br/>
	 * appId 应用编号<br/>
	 * logDatetime 时间<br/>
	 * logContent 接收内容<br/>
	 * logReceive 接收人<br/>
	 * logType 日志类型0邮件1短信<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * logId: <br/>
	 * appId: 应用编号<br/>
	 * logDatetime: 时间<br/>
	 * logContent: 接收内容<br/>
	 * logReceive: 接收人<br/>
	 * logType: 日志类型0邮件1短信<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute LogEntity log, HttpServletResponse response, HttpServletRequest request) {
		//验证时间的值是否合法			
		if(StringUtil.isBlank(log.getLogDatetime())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.datetime")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogDatetime()+"", 1, 19)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.datetime"), "1", "19"));
			return;			
		}
		//验证接收内容的值是否合法			
		if(StringUtil.isBlank(log.getLogContent())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.content")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogContent()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.content"), "1", "255"));
			return;			
		}
		//验证接收人的值是否合法			
		if(StringUtil.isBlank(log.getLogReceive())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.receive")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogReceive()+"", 1, 0)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.receive"), "1", "0"));
			return;			
		}
		//验证日志类型0邮件1短信的值是否合法			
		if(StringUtil.isBlank(log.getLogType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.type")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.type"), "1", "10"));
			return;			
		}
		logBiz.saveEntity(log);
		this.outJson(response, JSONObject.toJSONString(log));
	}
	
	/**
	 * @param log 发送日志实体
	 * <i>log参数包含字段信息参考：</i><br/>
	 * logId:多个logId直接用逗号隔开,例如logId=1,2,3,4
	 * 批量删除发送日志
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<LogEntity> logs,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[logs.size()];
		for(int i = 0;i<logs.size();i++){
			ids[i] = logs.get(i).getLogId();
		}
		logBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新发送日志信息发送日志
	 * @param log 发送日志实体
	 * <i>log参数包含字段信息参考：</i><br/>
	 * logId <br/>
	 * appId 应用编号<br/>
	 * logDatetime 时间<br/>
	 * logContent 接收内容<br/>
	 * logReceive 接收人<br/>
	 * logType 日志类型0邮件1短信<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * logId: <br/>
	 * appId: 应用编号<br/>
	 * logDatetime: 时间<br/>
	 * logContent: 接收内容<br/>
	 * logReceive: 接收人<br/>
	 * logType: 日志类型0邮件1短信<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute LogEntity log, HttpServletResponse response,
			HttpServletRequest request) {
		//验证时间的值是否合法			
		if(StringUtil.isBlank(log.getLogDatetime())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.datetime")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogDatetime()+"", 1, 19)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.datetime"), "1", "19"));
			return;			
		}
		//验证接收内容的值是否合法			
		if(StringUtil.isBlank(log.getLogContent())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.content")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogContent()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.content"), "1", "255"));
			return;			
		}
		//验证接收人的值是否合法			
		if(StringUtil.isBlank(log.getLogReceive())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.receive")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogReceive()+"", 1, 0)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.receive"), "1", "0"));
			return;			
		}
		//验证日志类型0邮件1短信的值是否合法			
		if(StringUtil.isBlank(log.getLogType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("log.type")));
			return;			
		}
		if(!StringUtil.checkLength(log.getLogType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("log.type"), "1", "10"));
			return;			
		}
		logBiz.updateEntity(log);
		this.outJson(response, JSONObject.toJSONString(log));
	}
		
}
