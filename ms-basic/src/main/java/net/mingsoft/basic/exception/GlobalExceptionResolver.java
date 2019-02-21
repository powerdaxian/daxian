package net.mingsoft.basic.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.ResultJson;
import com.mingsoft.basic.constant.Const;
import com.mingsoft.basic.constant.e.SessionConstEnum;

import net.mingsoft.basic.exception.BusinessException;
import net.mingsoft.basic.util.BasicUtil;
import net.sf.jsqlparser.expression.StringValue;

/**
 * 全局异常处理类
 * @author 铭飞开源团队-Administrator  
 * @date 2018年4月6日
 */
@ControllerAdvice
public class GlobalExceptionResolver extends DefaultHandlerExceptionResolver {
	

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		String url = request.getServletPath();
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw, true));
		String msg = sw.toString();
		Map map = new HashMap();
		int code = 200;
		map.put("url", url); //请求地址
		map.put("msg", msg); //异常文本
		map.put("exc", ex.getClass()); //详细异常信息
		map.put("cls", ex.getStackTrace()[0]+""); //出错的类
		if (ex instanceof MissingServletRequestParameterException) {
			code = 400;
		}
		if (ex instanceof UnauthorizedException) {
			code = 403;
		}
		if (ex instanceof NoHandlerFoundException) {
			code = 404;
		}
		if (ex instanceof HttpRequestMethodNotSupportedException) {
			code = 405;
		}
		if (ex instanceof BusinessException) {
			BusinessException be = (BusinessException) ex;
			map.put("msg", "业务异常"); //异常文本
			map.put("bizCode", be.getBizCode());
			map.put("bizMsg", be.getMessage());
		}
		if (!(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			if (map.get("code") == null) {
				map.put("code", 500); //错误码
				return new ModelAndView("/error/index", map);
			} else {
				ModelAndView mav = super.doResolveException(request, response, handler, ex);
				if (mav == null) {
					map.put("code", "500");
					return new ModelAndView("/error/index", map);

				}
				return mav;
			}
		} else {
			if (ex instanceof BusinessException) {
				map.remove("url");
				map.remove("cls");
				map.remove("exc");
			}
			// 如果是ajax请求，JSON格式返回
			try {
				if(map.get("code") == null) {
					map.put("code", 500); //错误码
				}
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(JSONObject.toJSONString(map));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
