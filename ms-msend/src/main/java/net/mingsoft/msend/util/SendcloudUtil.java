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
 *//**
The MIT License (MIT) * Copyright (c) 2016 铭飞科技(mingsoft.net)

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

package net.mingsoft.msend.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.activiti.engine.impl.json.JsonObjectConverter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 铭飞MS平台-邮件模块
 * 
 * @author 铭飞开发团队
 * @version 版本号：100-000-000<br/>
 *          创建日期：2016年5月8日<br/>
 *          历史修订：<br/>
 */
public class SendcloudUtil {

	/*
	 * log4j日志记录
	 */
	protected static final Logger LOG = Logger.getLogger(SendcloudUtil.class);

	/**
	 * 发送邮件
	 * 
	 * @param apiUser
	 *            平台分配
	 * @param apiKey
	 *            平台分配
	 * @param from
	 *            发送邮件
	 * @param fromName
	 *            发送名称
	 * @param to
	 *            接收者
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static boolean sendMail(String apiUser, String apiKey, String from, String fromName, String to,
			String subject, String content) throws IOException {
		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);

		List params = new ArrayList();
		// 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("from", from));
		params.add(new BasicNameValuePair("fromname", fromName));
		params.add(new BasicNameValuePair("to", to));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("html", content));
		params.add(new BasicNameValuePair("resp_email_id", "true"));
		httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		// 请求
		HttpResponse response = httpclient.execute(httpost);
		// 处理响应
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			// 读取xml文档
			String result = EntityUtils.toString(response.getEntity());
			LOG.debug(result);
			return true;
		} else {
			LOG.debug("error");
		}
		httpost.releaseConnection();
		return false;
	}

	/**
	 * sendcloud发送
	 * 
	 * @param smsUser
	 *            用户
	 * @param smsKey
	 *            key
	 * @param templateId
	 *            模板
	 * @param msgType
	 *            0短信 1彩信
	 * @param phone
	 *            接收手机号，多个用逗号分隔
	 * @param vars
	 *            需要替换的内容 json
	 * @throws IOException
	 */
	public static boolean sendSms(String smsUser, String smsKey, int templateId, String msgType, String phone,
			String vars) throws IOException {
		
		String url = "http://www.sendcloud.net/smsapi/send";

        // 填充参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("smsUser", smsUser);
        params.put("templateId", templateId+"");
        params.put("msgType", "0");
        params.put("phone", phone);
        params.put("vars", vars);
//        params.put("vars", "{\"appointmentPhone\":\"18979833333\",\"appointmentTime\":1504108800000,\"appointmentName\":\"xx\",\"appointmentCarNo\":\"asdas\",\"appointmentType\":0}");
        
        // 对参数进行排序
        Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String arg0, String arg1) {
                // 忽略大小写
                return arg0.compareToIgnoreCase(arg1); 
            }
        });
        sortedMap.putAll(params);

        // 计算签名
        StringBuilder sb = new StringBuilder();
        sb.append(smsKey).append("&");
        for (String s : sortedMap.keySet()) {
            sb.append(String.format("%s=%s&", s, sortedMap.get(s)));
        }
        sb.append(smsKey);
        LOG.debug("sign_str:"+sb.toString());
        String sig = DigestUtils.md5Hex(sb.toString());
        LOG.debug("sign_md5"+sig);
        // 将所有参数和签名添加到post请求参数数组里
        List<NameValuePair> postparams = new ArrayList<NameValuePair>();
        for (String s : sortedMap.keySet()) {
            postparams.add(new BasicNameValuePair(s, sortedMap.get(s)));
        }
        postparams.add(new BasicNameValuePair("signature", sig));

        LOG.debug(JSONObject.toJSONString(postparams));
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(postparams, "utf8"));
            CloseableHttpClient httpClient;
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(100000).build();
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            ResponseData rd = JSON.parseObject(EntityUtils.toString(entity), ResponseData.class);
			LOG.debug(rd);
			return rd.getResult();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            httpPost.releaseConnection();
        }
		
		return false;
		
		
		
//		
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("smsUser", smsUser);
//		params.put("templateId", templateId + "");
//		params.put("msgType", msgType);
//		params.put("phone", phone);
//		params.put("vars", vars);
//
//		Map sortedMap = new TreeMap(new Comparator() {
//			@Override
//			public int compare(Object arg0, Object arg1) {
//				return arg0.toString().compareToIgnoreCase(arg1.toString());
//			}
//		});
//		sortedMap.putAll(params);
//
//		StringBuilder sb = new StringBuilder();
//		sb.append(smsKey).append("&");
//		Iterator<String> iter = sortedMap.keySet().iterator();
//		while (iter.hasNext()) {
//			String key = (String) iter.next();
//			String value = params.get(key);
//			sb.append(String.format("%s=%s&", new Object[] { key, value }));
//		}
//		sb.append(smsKey);
//		String sig = DigestUtils.md5Hex(sb.toString());
//
//		Object postparams = new ArrayList();
//		Iterator<String> iterator = sortedMap.keySet().iterator();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			String value = params.get(key);
//			((List) postparams).add(new BasicNameValuePair(key, value));
//		}
//		((List) postparams).add(new BasicNameValuePair("signature", sig));
//
//		HttpPost httpPost = new HttpPost("http://sendcloud.sohu.com/smsapi/send");
//		try {
//			httpPost.setEntity(new UrlEncodedFormEntity((List) postparams, "utf8"));
//
//			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(100000)
//					.build();
//			CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
//			HttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity();
//			String str = EntityUtils.toString(entity);
//			ResponseData rd = JSON.parseObject(str, ResponseData.class);
//			LOG.debug(rd);
//			return rd.getResult();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpPost.releaseConnection();
//		}
//		return false;
	}


}

class ResponseData {

	public boolean result;
	public int statusCode;
	public String message;
	public String info;

	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
