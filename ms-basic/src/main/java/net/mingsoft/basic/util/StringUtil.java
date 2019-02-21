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
package net.mingsoft.basic.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import cn.hutool.core.util.ArrayUtil;

public class StringUtil {
	/**
	 * 验证长度
	 * 
	 * @param str 需验证的字符串
	 * @param minLength 字符串的最小长度
	 * @param maxLength 字符串的最大长度
	 * @return 如果验证通过，则返回true，否则返回false
	 */
	public static boolean checkLength(String str, int minLength, int maxLength) {
		if (str != null) {
			int len = str.length();
			if (minLength == 0)
				return len <= maxLength;
			else if (maxLength == 0)
				return len >= minLength;
			else
				return (len >= minLength && len <= maxLength);
		}
		return false;
	}
	/**
	 * 判断数字数组
	 * 
	 * @param str String数组
	 * @return 如果是数字，则返回true，否则返回false
	 */
	public static boolean isIntegers(String str[]) {
		try {
			for (int i = 0; i < str.length; i++)
				Integer.parseInt(str[i]);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 字符串转数字型数组
	 * 
	 * @param str String字符串
	 * @return 返回数字型数组
	 */
	public static Integer[] stringsToIntegers(String str[]) {
		Integer array[] = new Integer[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Integer.parseInt(str[i]);
		return array;
	}
	/**
	 * 字符串转数字型数组
	 * 
	 * @param str String字符串
	 * @return 返回数字型数组
	 */
	public static int[] stringsToInts(String str[]) {
		int array[] = new int[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Integer.parseInt(str[i]);
		return array;
	}

	/**
	 * 程序内部字符串转码，将ISO-8859-1转换成utf-8
	 * 
	 * @param str 需要转码的字符串
	 * @return 返回utf8编码字符串
	 */
	public static String isoToUTF8(String str) {
		if (StringUtils.isEmpty(str))
			return "";
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	/**
	 * 字符串转double型数组
	 * 
	 * @param str String字符串
	 * @return 返回数字型数组
	 */
	public static double[] stringsToDoubles(String str[]) {
		double array[] = new double[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Double.parseDouble(str[i]);
		return array;
	}
	/**
	 * 判断数字数组
	 * 
	 * @param str String数组
	 * @return 如果是数组，则返回true，否则返回false
	 */
	public static boolean isDoubles(String str[]) {
		try {
			for (int i = 0; i < str.length; i++)
				Double.parseDouble(str[i]);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 获取时间戳
	 * 
	 * @return 返回获取当前系统时间字符串
	 */
	public static String getDateSimpleStr() {
		return String.valueOf(System.currentTimeMillis());
	}
	/**
	 * 生成随机数
	 * 
	 * @param len 随机数长度
	 * @return 返回随机数
	 */
	public static String randomNumber(int len) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			sb.append(Math.abs(random.nextInt()) % 10);
		}
		return sb.toString();
	}
	/**
	 * 变量形态转换 int型转为String型
	 * 
	 * @param comment 整型数字
	 * @return 返回字符串
	 */
	public static String int2String(int comment) {
		String srt = "";
		srt = Integer.toString(comment);
		return srt;
	}
	/**
	 * 除去字符窜中重复的字符
	 * 
	 * @param content
	 *            　原始内容
	 * @param target
	 *            　重复内容
	 * @return　返回除去后的字符串
	 */
	public static String removeRepeatStr(String content, String target) {
		// int index = content.indexOf(target);
		// content = checkRepeatStr( content,target,index);
		StringBuffer sb = new StringBuffer(content);
		for (int i = 0; i < sb.length()-1; i++) {

			if (sb.substring(i, i + target.length()).equals(target) && sb.substring(i, i + target.length()).equals(sb.substring(i + 1, i + target.length() + 1))) {
				sb.delete(i, i + target.length());
				if (i + target.length() + 1 > sb.length()) {
					break;
				} else {
					i--;
				}
			}

		}
		return sb.toString();
	}
	/**
	 * 降序排序
	 * @param str
	 * @param delimiter
	 * 			分隔符
	 * @return
	 */
	public static String sort(String str,String delimiter){
		String[] articleTypeArrays = str.split(delimiter);
		Arrays.sort(articleTypeArrays);
		return ArrayUtil.join(articleTypeArrays, delimiter);
	}


}
