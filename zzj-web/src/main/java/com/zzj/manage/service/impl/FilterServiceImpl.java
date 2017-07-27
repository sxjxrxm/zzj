/**
 * Project Name:zzj-web
 * File Name:FilterServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.db.blo.ScreeningInfoBlo;
import com.zzj.db.dto.ScreeningInfo;
import com.zzj.db.model.FilterVO;
import com.zzj.manage.service.FilterService;

/**
 * <p><strong>类名: </strong></p>FilterServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>筛选条件处理业务实现类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日上午9:20:17 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class FilterServiceImpl implements FilterService {
	
	/**
	 * 筛选条件业务数据库操作类
	 */
	@Autowired
	private ScreeningInfoBlo blo;

	/**
	 * 取得全部机能的筛选条件
	 * @return List<FilterVO> 画面表示用全部机能的筛选条件
	 */
	@Override
	public List<FilterVO> searchFilterList() {
		
		// 取得全部机能的筛选条件
		List<ScreeningInfo> infoList = blo.selectAllScreeningInfo();
		
		// 取得的筛选条件放入VO
		List<FilterVO> voList = new ArrayList<FilterVO>();
		if(infoList != null) {
			ScreeningInfo info = new ScreeningInfo();
			FilterVO vo = new FilterVO();
			for (int i = 0; i<infoList.size(); i++)
			{
				vo = new FilterVO();
				info = infoList.get(i);			
				BeanUtils.copyProperties(info, vo);
				voList.add(vo);
			}
		}		
		
		return voList;
	}

	/**
	 * 保存筛选条件。
	 * @param  vo FilterVO记录
	 * @param  busiType 业务类别
	 * @return FilterVO 查询记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FilterVO searchFilter(HttpServletRequest request, String busiType) {

		// 取得筛选条件
		ScreeningInfo screeningInfo = blo.selectByPrimaryKey(busiType);
		
		FilterVO vo = new FilterVO();		
		if(screeningInfo != null)
		{
			BeanUtils.copyProperties(screeningInfo, vo);			
		}

		return vo;
	}
	
	/**
	 * 保存筛选条件
	 * @param  vo FilterVO记录
	 * @param  request http请求实例
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveFilter(HttpServletRequest request, FilterVO vo) {
		
		// VO转成DTO
		ScreeningInfo info = new ScreeningInfo();
		BeanUtils.copyProperties(vo, info);
		
		// 筛选条件存在
		ScreeningInfo screeningInfo = blo.selectByPrimaryKey(vo.getBusiType());
		
		// 筛选条件存在时，更新
		if(screeningInfo != null)
		{
			blo.updateByPrimaryKey(info);
		}
		// 筛选条件不存在时，插入
		else
		{
			blo.insert(info);
		}
	}
}