/**
 * Project Name:zzj-web
 * File Name:FilterService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.model.FieldResultVO;

/**
 * <p><strong>类名: </strong></p>FieldService <br/>
 * <p><strong>功能说明: </strong></p>领域处理业务实现类 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月17日上午9:37:02 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface FieldService {

	/**
	 * 取得对应的研究领域<br/>
	 * @param techField 技术领域
	 * @return FieldResultVO 领域信息
	 */
	public FieldResultVO searchFieldList(String techField);

	/**
	 * 保存对应的研究领域<br/>
	 * @param  request http请求实例
	 */
	public void saveField(HttpServletRequest request);
	
	/**
	 * 取得画面表示用内容
	 * @param techFieldCd 技术领域编码
	 * @return FieldResultVO 领域结果信息
	 */
	public FieldResultVO searchRschFieldList(String techField);

	/**
	 * 取得画面表示用内容
	 * @param  request http请求实例
	 * @return Map<String, Object> 编码信息
	 */
	public Map<String, Object> setCodeInfo(HttpServletRequest request);

	/**
	 * 更新数据库<br/>
	 * @param codeInfo 更新信息
	 * @return Integer 查询结果记录
	 */
	Integer insert(MstCodeInfo codeInfo);
	
	/**
	 * 保存领域表
	 * @param  String 技术领域编码
	 */
	void insertTechField(String techFieldCd);
}