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

package com.mingsoft.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mingsoft.base.constant.e.DeleteEnum;

/**
 * 
 * @ClassName:  BaseEntity   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 铭飞开发团队
 * @date:   2018年3月19日 下午3:36:17   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
public abstract class  BaseEntity implements Serializable{
	
	/**
	 * 创建用户编号
	 */
	@JsonIgnore
	@XmlTransient	
	protected int createBy;
	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;

	/**
	 * 标记
	 */
	@JsonIgnore
	@XmlTransient	
	protected int delFlag;
	
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;	
	
	/**
	 * 备注
	 */
	protected String remarks;	
	
	/**
	 * 最后更新用户编号
	 */
	@JsonIgnore
	@XmlTransient	
	protected int updateBy;		
	
	/**
	 * 最后更新日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;

	/**
	 * 自定义SQL where条件，需要配合对应dao.xml使用
	 */
	@JsonIgnore
	@XmlTransient
	protected String sqlWhere;
	
	/**
	 * 自定义SQL where条件，需要配合对应dao.xml使用
	 */
	@JsonIgnore
	@XmlTransient
	protected String sqlDataScope;
	
	/**
	 * 排序字段
	 */
	@JsonIgnore
	@XmlTransient
	protected String orderBy;

	/**
	 * 排序方式
	 */
	protected String order;
	

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(DeleteEnum delFlag) {
		this.delFlag = delFlag.toInt();
	}
	
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@XmlTransient
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public String getSqlDataScope() {
		return sqlDataScope;
	}

	public void setSqlDataScope(String sqlDataScope) {
		this.sqlDataScope = sqlDataScope;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	
}