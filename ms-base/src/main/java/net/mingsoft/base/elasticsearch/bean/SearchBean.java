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
package net.mingsoft.base.elasticsearch.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mingsoft.util.StringUtil;

/**
 * @ClassName:  SearchBean   
 * @Description:TODO(搜索引擎mapping类)   
 * @author: 铭飞开发团队
 * @date:   2018年3月19日 下午3:45:22   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
public  class SearchBean {


	private int pageNo = 1;
	
	private int pageSize = 20; 
	
	private String orderBy = "id";
	
	private String order = "desc";
	
	private String keyword;


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo<=1) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		if(StringUtil.isBlank(orderBy)) {
			orderBy = "id";
		}
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}
	

	public void setOrder(String order) {
		if(StringUtil.isBlank(order)) {
			order = "desc";
		}
		this.order = order;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	
}
