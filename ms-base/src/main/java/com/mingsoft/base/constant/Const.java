/**
The MIT License (MIT) * Copyright (c) 2018 铭飞科技(mingsoft.net)

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
 */

package com.mingsoft.base.constant;

import java.util.ResourceBundle;
import org.springframework.context.ApplicationContext;


/**
 * @ClassName:  BaseAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 铭飞开发团队
 * @date:   2018年3月19日 下午3:28:27   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
public final class Const {

	/**
	 * 当前请求路径,BaseFilter赋值
	 */
	public static String BASE_URL;

	/**
	 * 项目名称,BaseFilter赋值
	 */
	public static String BASE;

	/**
	 * 项目物理路径,BaseFilter赋值
	 */
	public static String PROJECT_PATH;

	/**
	 * spring资源文件加载上下文对象
	 */
	public static ApplicationContext CONTEXT;

	/**
	 * action层对应的国际化资源文件
	 */
	public final static ResourceBundle RESOURCES = ResourceBundle.getBundle("com.mingsoft.base.resources.resources");

	

	/**
	 * 服务器发布地址,带有http:// 在StrutsFilter类里面设置
	 */
	public static String HOST_URL = "";


	/**
	 * 默认编码格式
	 */
	public final static String UTF8 = "utf-8";
	
	/**
	 * URL路径符
	 */
	public final static String SEPARATOR ="/";
	

	/**
	 * 统一定义error错误值，用户返回消息统一
	 */
	public final static String ERROR ="error";
	
	/**
	 * 统一定义error错误值，用户返回消息统一
	 */
	public final static String ERROR_500 ="/500/error.do";
}