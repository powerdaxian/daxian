package com.mingsoft.mdiy.biz;


import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.mdiy.entity.DictEntity;

/**
 * 字典表业务
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2016-9-8 17:11:19<br/>
 * 历史修订：<br/>
 */
public interface IDictBiz extends IBaseBiz {
	/**
	 * 
	 * 根据字典类型和标签名或者实体
	 * @param dictType 类型
	 * @param dictLabel 标签名
	 * @return DictEntity 字典实体
	 */
	public DictEntity getByTypeAndLabel(String dictType,String dictLabel);
}