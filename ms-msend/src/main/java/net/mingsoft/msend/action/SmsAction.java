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
import net.mingsoft.msend.biz.ISmsBiz;
import net.mingsoft.msend.entity.SmsEntity;
	
/**
 * 短信管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-24 17:52:29<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/msend/sms")
public class SmsAction extends net.mingsoft.msend.action.BaseAction{
	
	/**
	 * 注入短信业务层
	 */	
	@Autowired
	private ISmsBiz smsBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/msend/sms/index");
	}
	
	/**
	 * 查询短信列表
	 * @param sms 短信实体
	 * <i>sms参数包含字段信息参考：</i><br/>
	 * appId 应用编号<br/>
	 * smsType 短信接口类型<br/>
	 * smsUsername 账号<br/>
	 * smsPassword 密码<br/>
	 * smsSendUrl 发送地址<br/>
	 * smsAccountUrl <br/>
	 * smsManagerUrl 短信平台后台管理地址<br/>
	 * smsSignature 签名<br/>
	 * smsEnable 0启用 1禁用<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * appId: 应用编号<br/>
	 * smsType: 短信接口类型<br/>
	 * smsUsername: 账号<br/>
	 * smsPassword: 密码<br/>
	 * smsSendUrl: 发送地址<br/>
	 * smsAccountUrl: <br/>
	 * smsManagerUrl: 短信平台后台管理地址<br/>
	 * smsSignature: 签名<br/>
	 * smsEnable: 0启用 1禁用<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute SmsEntity sms,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List smsList = smsBiz.query(sms);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(smsList,(int)BasicUtil.endPage(smsList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面sms_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute SmsEntity sms,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		BaseEntity smsEntity = smsBiz.getEntity(BasicUtil.getAppId());			
		model.addAttribute("smsEntity",smsEntity);
		return view ("/msend/sms/form");
	}
	
	/**
	 * 获取短信
	 * @param sms 短信实体
	 * <i>sms参数包含字段信息参考：</i><br/>
	 * appId 应用编号<br/>
	 * smsType 短信接口类型<br/>
	 * smsUsername 账号<br/>
	 * smsPassword 密码<br/>
	 * smsSendUrl 发送地址<br/>
	 * smsAccountUrl <br/>
	 * smsManagerUrl 短信平台后台管理地址<br/>
	 * smsSignature 签名<br/>
	 * smsEnable 0启用 1禁用<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * appId: 应用编号<br/>
	 * smsType: 短信接口类型<br/>
	 * smsUsername: 账号<br/>
	 * smsPassword: 密码<br/>
	 * smsSendUrl: 发送地址<br/>
	 * smsAccountUrl: <br/>
	 * smsManagerUrl: 短信平台后台管理地址<br/>
	 * smsSignature: 签名<br/>
	 * smsEnable: 0启用 1禁用<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute SmsEntity sms,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(sms.getAppId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("app.id")));
			return;
		}
		SmsEntity _sms = (SmsEntity)smsBiz.getEntity(sms.getAppId());
		this.outJson(response, _sms);
	}
	
	/**
	 * 保存短信实体
	 * @param sms 短信实体
	 * <i>sms参数包含字段信息参考：</i><br/>
	 * appId 应用编号<br/>
	 * smsType 短信接口类型<br/>
	 * smsUsername 账号<br/>
	 * smsPassword 密码<br/>
	 * smsSendUrl 发送地址<br/>
	 * smsAccountUrl <br/>
	 * smsManagerUrl 短信平台后台管理地址<br/>
	 * smsSignature 签名<br/>
	 * smsEnable 0启用 1禁用<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * appId: 应用编号<br/>
	 * smsType: 短信接口类型<br/>
	 * smsUsername: 账号<br/>
	 * smsPassword: 密码<br/>
	 * smsSendUrl: 发送地址<br/>
	 * smsAccountUrl: <br/>
	 * smsManagerUrl: 短信平台后台管理地址<br/>
	 * smsSignature: 签名<br/>
	 * smsEnable: 0启用 1禁用<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("sms:save")
	public void save(@ModelAttribute SmsEntity sms, HttpServletResponse response, HttpServletRequest request,BindingResult result) {
		//验证短信接口类型的值是否合法			
		if(StringUtil.isBlank(sms.getSmsType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("sms.type")));
			return;			
		}
		if(!StringUtil.checkLength(sms.getSmsType()+"", 1, 150)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("sms.type"), "1", "150"));
			return;			
		}
		sms.setAppId(BasicUtil.getAppId());
		smsBiz.saveEntity(sms);
		this.outJson(response, JSONObject.toJSONString(sms));
	}
	
	/**
	 * @param sms 短信实体
	 * <i>sms参数包含字段信息参考：</i><br/>
	 * appId:多个appId直接用逗号隔开,例如appId=1,2,3,4
	 * 批量删除短信
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("sms:del")
	public void delete(@RequestBody List<SmsEntity> smss,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[smss.size()];
		for(int i = 0;i<smss.size();i++){
			ids[i] = smss.get(i).getAppId();
		}
		smsBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新短信信息短信
	 * @param sms 短信实体
	 * <i>sms参数包含字段信息参考：</i><br/>
	 * appId 应用编号<br/>
	 * smsType 短信接口类型<br/>
	 * smsUsername 账号<br/>
	 * smsPassword 密码<br/>
	 * smsSendUrl 发送地址<br/>
	 * smsAccountUrl <br/>
	 * smsManagerUrl 短信平台后台管理地址<br/>
	 * smsSignature 签名<br/>
	 * smsEnable 0启用 1禁用<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * appId: 应用编号<br/>
	 * smsType: 短信接口类型<br/>
	 * smsUsername: 账号<br/>
	 * smsPassword: 密码<br/>
	 * smsSendUrl: 发送地址<br/>
	 * smsAccountUrl: <br/>
	 * smsManagerUrl: 短信平台后台管理地址<br/>
	 * smsSignature: 签名<br/>
	 * smsEnable: 0启用 1禁用<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody
	@RequiresPermissions("sms:update")
	public void update(@ModelAttribute SmsEntity sms, HttpServletResponse response,
			HttpServletRequest request) {
		//验证短信接口类型的值是否合法			
		if(StringUtil.isBlank(sms.getSmsType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("sms.type")));
			return;			
		}
		if(!StringUtil.checkLength(sms.getSmsType()+"", 1, 150)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("sms.type"), "1", "150"));
			return;			
		}
		smsBiz.updateEntity(sms);
		this.outJson(response, JSONObject.toJSONString(sms));
	}
		
}
