/**
 * Project Name:zzj-web
 * File Name:FilterService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.model.FilterVO;

/**
 * <p><strong>类名: </strong></p>FilterService <br/>
 * <p><strong>功能说明: </strong></p>筛选条件处理业务实现类 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日上午9:37:02 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface FilterService {

	/**
	 * 取得全部机能的筛选条件<br/>
	 * @return List<FilterVO>  筛选条件列表
	 */
	public List<FilterVO> searchFilterList();

	/**
	 * 保存筛选条件。
	 * @param  vo FilterVO记录
	 * @param  busiType 业务类别
	 * @return FilterVO 查询记录
	 */
	public FilterVO searchFilter(HttpServletRequest request, String busiType);

	/**
	 * 保存筛选条件
	 * @param  vo FilterVO记录
	 * @param  request http请求实例
	 */
	public void saveFilter(HttpServletRequest request, FilterVO filterVO);

}

