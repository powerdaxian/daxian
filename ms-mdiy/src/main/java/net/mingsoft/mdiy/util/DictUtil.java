package net.mingsoft.mdiy.util;

import java.util.List;

import com.mingsoft.mdiy.biz.IDictBiz;
import com.mingsoft.mdiy.entity.DictEntity;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.SpringUtil;

public class DictUtil {
	/**
	 * 获取异常返回信息
	 * @param dictLabel 标签名
	 * @param dictType 类型
	 * @return DictEntity 字典实体
	 */
	public static DictEntity get(String dictLabel,String dictType){
		DictEntity dict = new DictEntity();
		dict.setDictLabel(dictLabel);
		dict.setDictType(dictType);
		dict.setAppId(BasicUtil.getAppId());
		return (DictEntity) SpringUtil.getBean(IDictBiz.class).getEntity(dict);
	}
	/**
	 * 根据字典类型获取列表
	 * @param dictType 字典类型
	 * @return 字典列表
	 */
	public static List<DictEntity> list(String dictType){
		DictEntity dict = new DictEntity();
		dict.setDictType(dictType);
		dict.setAppId(BasicUtil.getAppId());
		return (List<DictEntity>) SpringUtil.getBean(IDictBiz.class).query(dict);
	}
}
