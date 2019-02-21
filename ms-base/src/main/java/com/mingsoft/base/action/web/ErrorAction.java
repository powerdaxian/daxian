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
package com.mingsoft.base.action.web;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.action.BaseAction;
import com.mingsoft.util.FileUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.base.util.BaseUtil;

/**
 * @ClassName:  BaseAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 铭飞开发团队
 * @date:   2018年3月19日 下午3:28:27   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
@Controller("baseErrorAction")
@RequestMapping("/base/error")
public class ErrorAction extends BaseAction {
	/**
	 * 返回404页面
	 */
	@RequestMapping("/{code}")
	@ResponseBody
	public void code(@PathVariable("code") String code, HttpServletRequest req, HttpServletResponse resp,
			Exception ex) {
		String content = FileUtil.readFile(this.getRealPath(req, "/error/" + code + ".htm"));
		if (StringUtil.isBlank(content)) {
			content = FileUtil.readFile(this.getRealPath(req, "/error/error.htm"));
		}
		content = content.replace("{code/}", code);
		Object obj = BaseUtil.getSession("ms_exception");
		if (obj != null) {
			Exception e = (Exception) obj;
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			content = content.replace("{ms_exception/}", sw.toString());
		}
		this.outString(resp, content);
	}
}
