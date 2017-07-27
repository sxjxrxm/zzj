/**
 * Project Name:zzj-web
 * File Name:EnterpriseService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzj.db.dto.CorpInfo;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.model.EnterpriseVO;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>EnterpriseService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月6日下午3:54:43 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface EnterpriseService {
	
	/**
	 * 取得政企一览记录。
	 * @param corpInfo 查询条件
	 * @return 画面表示用数据对象
	 */
	List<EnterpriseVO> selectByParament(EnterpriseVO enterpriseVO);

	/**
	 * 城市联动菜单异步请求。<br/>
	 * @param  request 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	public List<MstAreaInfo> getCityC(HttpServletRequest request);

	/**
	 * 取得城市一级记录。
	 * @param corpInfo 查询对象
	 * @return 画面表示用数据对象
	 */
	List<MstAreaInfo> selectByArea();

	/**
	 * 取得政企审核使用记录。
	 * @param userId 查询对象
	 * @return EnterpriseVO 画面表示用政企数据对象
	 */
	EnterpriseVO selectBykey(String userId);
	
	/**
	 * 保存政企审核结果。
	 * @param  corpInfo 保存对象
	 * return 更新结果
	 */
	int upDataByKey(CorpInfo corpInfo);

	/**
	 * 取得政企一览符合条件记录的总数
	 * @param enterpriseVO 查询条件
	 * @return 画面表示用数据对象
	 */
	PageResult<EnterpriseVO> searchPagging(EnterpriseVO enterpriseVO);

	/** 
	 * 查询省份对应的城市信息<br/>
	 * @param  enterpriseVO 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	List<MstAreaInfo> getCityC1(EnterpriseVO enterpriseVO);

	/**
	 * 保存政企一览查询条件 
	 * @param  request 请求实例
	 * @return enterpriseVO一览查询条件
	 */
	Object savePara(HttpServletRequest request, String add) throws Exception ;

	/**
	 * 保存政企审核结果
	 * @param  request 请求实例
	 * @param  corpInfoList 使用对象类
	 * @param  userId 使用用户ID
	 * @throws Exception
	 */
	void save(HttpServletRequest request, EnterpriseVO corpInfoList, String userId) throws Exception;

	/**
	 * 政企一览数据做成（CSV）
	 * @param  request 请求实例
	 * @param  response 返回实例
	 * @param  corpInfo 使用对象类
	 * @throws Exception
	 */
	void outputCSV(HttpServletRequest request, HttpServletResponse response, EnterpriseVO corpInfo) throws Exception;

	/**
	 * 跳转到政企一览页面。<br/>
	 * @param  request 请求实例
	 * @param  corpInfo 使用的对象类
	 * @return 无
	 * @throws Exception
	 */
	void toEnterprise(HttpServletRequest request, EnterpriseVO corpInfo) throws Exception;
}

