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

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import net.mingsoft.msend.biz.ITemplateBiz;
import net.mingsoft.msend.entity.TemplateEntity;
	
/**
 * 发送消息模板表管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-24 17:52:29<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/msend/template")
public class TemplateAction extends net.mingsoft.msend.action.BaseAction{
	
	/**
	 * 注入发送消息模板表业务层
	 */	
	@Autowired
	private ITemplateBiz templateBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/msend/template/index");
	}
	
	/**
	 * 查询发送消息模板表列表
	 * @param template 发送消息模板表实体
	 * <i>template参数包含字段信息参考：</i><br/>
	 * templateId 编号<br/>
	 * modelId 模块编号<br/>
	 * appId 应用编号<br/>
	 * templateTitle 标题<br/>
	 * templateMail <br/>
	 * templateSms <br/>
	 * templateCode 邮件模块代码<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * templateId: 编号<br/>
	 * modelId: 模块编号<br/>
	 * appId: 应用编号<br/>
	 * templateTitle: 标题<br/>
	 * templateMail: <br/>
	 * templateSms: <br/>
	 * templateCode: 邮件模块代码<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute TemplateEntity template,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		if(template == null){
			template = new TemplateEntity();
		}
		template.setAppId(BasicUtil.getAppId());
		BasicUtil.startPage();
		List templateList = templateBiz.query(template);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(templateList,(int)BasicUtil.endPage(templateList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面template_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute TemplateEntity template,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(template.getTemplateId() != null){
			BaseEntity templateEntity = templateBiz.getEntity(template.getTemplateId());			
			model.addAttribute("templateEntity",templateEntity);
		}
		return view ("/msend/template/form");
	}
	
	/**
	 * 获取发送消息模板表
	 * @param template 发送消息模板表实体
	 * <i>template参数包含字段信息参考：</i><br/>
	 * templateId 编号<br/>
	 * modelId 模块编号<br/>
	 * appId 应用编号<br/>
	 * templateTitle 标题<br/>
	 * templateMail <br/>
	 * templateSms <br/>
	 * templateCode 邮件模块代码<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * templateId: 编号<br/>
	 * modelId: 模块编号<br/>
	 * appId: 应用编号<br/>
	 * templateTitle: 标题<br/>
	 * templateMail: <br/>
	 * templateSms: <br/>
	 * templateCode: 邮件模块代码<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute TemplateEntity template,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(template.getTemplateId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("template.id")));
			return;
		}
		TemplateEntity _template = (TemplateEntity)templateBiz.getEntity(template.getTemplateId());
		this.outJson(response, _template);
	}
	
	/**
	 * 保存发送消息模板表实体
	 * @param template 发送消息模板表实体
	 * <i>template参数包含字段信息参考：</i><br/>
	 * templateId 编号<br/>
	 * modelId 模块编号<br/>
	 * appId 应用编号<br/>
	 * templateTitle 标题<br/>
	 * templateMail <br/>
	 * templateSms <br/>
	 * templateCode 邮件模块代码<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * templateId: 编号<br/>
	 * modelId: 模块编号<br/>
	 * appId: 应用编号<br/>
	 * templateTitle: 标题<br/>
	 * templateMail: <br/>
	 * templateSms: <br/>
	 * templateCode: 邮件模块代码<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("sendTemplate:save")
	public void save(@ModelAttribute TemplateEntity template, HttpServletResponse response, HttpServletRequest request,BindingResult result) {
		//验证标题是否合法			
		if(StringUtil.isBlank(template.getTemplateTitle())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("template.title")));
			return;			
		}
		template.setAppId(BasicUtil.getAppId());
		templateBiz.saveEntity(template);
		this.outJson(response, JSONObject.toJSONString(template));
	}
	
	/**
	 * @param template 发送消息模板表实体
	 * <i>template参数包含字段信息参考：</i><br/>
	 * templateId:多个templateId直接用逗号隔开,例如templateId=1,2,3,4
	 * 批量删除发送消息模板表
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("sendTemplate:del")
	public void delete(@RequestBody List<TemplateEntity> templates,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[templates.size()];
		for(int i = 0;i<templates.size();i++){
			ids[i] = templates.get(i).getTemplateId();
		}
		templateBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新发送消息模板表信息发送消息模板表
	 * @param template 发送消息模板表实体
	 * <i>template参数包含字段信息参考：</i><br/>
	 * templateId 编号<br/>
	 * modelId 模块编号<br/>
	 * appId 应用编号<br/>
	 * templateTitle 标题<br/>
	 * templateMail <br/>
	 * templateSms <br/>
	 * templateCode 邮件模块代码<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * templateId: 编号<br/>
	 * modelId: 模块编号<br/>
	 * appId: 应用编号<br/>
	 * templateTitle: 标题<br/>
	 * templateMail: <br/>
	 * templateSms: <br/>
	 * templateCode: 邮件模块代码<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody
	@RequiresPermissions("sendTemplate:update")
	public void update(@ModelAttribute TemplateEntity template, HttpServletResponse response,
			HttpServletRequest request) {
		//验证标题是否合法			
		if(StringUtil.isBlank(template.getTemplateTitle())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("template.title")));
			return;			
		}
		templateBiz.updateEntity(template);
		this.outJson(response, JSONObject.toJSONString(template));
	}
		
}
