/**
 * Project Name:zzj-web
 * File Name:AdService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import com.zzj.db.model.CommercialVO;

/**
 * <p><strong>类名: </strong></p>AdService <br/>
 * <p><strong>功能说明: </strong></p>广告管理操作模块相关业务逻辑处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月3日下午5:02:55 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface AdService {
	/**
	 *广告编辑方法<br/>
	 * @param commercialCd 
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	Integer editAd(String name, String brief, String userId, String isAdd, String imgPath, String commercialCd);
	/**
	 *广告编辑查询方法<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	CommercialVO editSearch(String id, String isAdd);
	/**
	 *广告删除方法<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	Integer deleteAd(String commercialCd);

}

