/**
 * Project Name:zzj-web
 * File Name:AdServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.db.blo.CommercialInfoBlo;
import com.zzj.db.blo.MstSequenceInfoBlo;
import com.zzj.db.dto.CommercialInfo;
import com.zzj.db.model.CommercialVO;
import com.zzj.manage.service.AdService;
import com.zzj.util.StringUtil;

/**
 * <p><strong>类名: </strong></p>AdServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>广告管理操作模块相关业务逻辑处理实现类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月3日下午5:04:21 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class AdServiceImpl implements AdService{
	/**
	 *commercial业务数据库操作类
	 */
	@Autowired
	private CommercialInfoBlo  commercialInfoBlo;
	/**
	 *取得主键用blo
	 */
	@Autowired
	private MstSequenceInfoBlo  mstSequenceInfoBlo;
	/**
	 *广告编辑方法<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer editAd(String name, String brief, String userId, String isAdd, String imgPath,String commercialCd) {
		// 定义返回结果 
		Integer  x = -1;
		// 定义CommercialInfo表示编辑条件
		CommercialInfo commercialInfo = new CommercialInfo();
		// 为编辑条件赋值
		commercialInfo.setCommercialBrief(brief);
		commercialInfo.setCommercialName(name);
		if(!"http://localhost/img/".equals(imgPath)){
			commercialInfo.setIconAddress(imgPath);
		}		
		// 判断isAdd以确定执行添加或者修改
		if("1".equals(isAdd))
		{
			// 得到主键
			String newCommercialCd = mstSequenceInfoBlo.selectSequenceInfo("AD_NO");
			commercialInfo.setCommercialCd(newCommercialCd);
			commercialInfo.setCreateId(userId);
			commercialInfo.setCreateTime(new Date());
			commercialInfo.setDeleteFlag(false);
			// 调用blo添加记录
			x = commercialInfoBlo.insert(commercialInfo);
		}
		else{
			commercialInfo.setCommercialCd(commercialCd);
			commercialInfo.setUpdateId(userId);
			commercialInfo.setUpdateTime(new Date());
			// 调用blo更新记录
			x = commercialInfoBlo.update(commercialInfo);
		}
		return x;
	}
	/**
	 *广告编辑查询方法<br/>
	 * @param  request http请求实例
	 * @return 返回受影响条目数
	 * @throws Exception
	 */
	@Override
	public CommercialVO editSearch(String id, String isAdd) {
		// 实例化CommercialVO表示返回结果
		CommercialVO commercialVO = 	new CommercialVO();
		// 实例化CommercialInfo表示查询结果
		CommercialInfo commercialInfo = commercialInfoBlo.editSearch(id);
		String address = "";
		if(commercialInfo.getIconAddress() != null){
			try {
				address = StringUtil.getImageURL(commercialInfo.getIconAddress());
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}		
		if(commercialInfo != null) {
			BeanUtils.copyProperties(commercialInfo, commercialVO);
			commercialVO.setIconAddress(address);
			commercialVO.setIsAdd(Integer.parseInt(isAdd));
		}
		return commercialVO;
	}
	/**
	 *广告删除方法<br/>
	 * @param  request http请求实例
	 * @return 返回受影响条目数
	 * @throws Exception
	 */
	@Override
	public Integer deleteAd(String commercialCd) {
		// 实例化CommercialInfo表示欲删除记录
		CommercialInfo commercialInfo = new CommercialInfo();
		// 赋值
		commercialInfo.setCommercialCd(commercialCd);
		commercialInfo.setDeleteFlag(true);
		// 调用blo删除
		Integer x = commercialInfoBlo.deleteAd(commercialInfo);
		return x;
	}
}

