/**
 * Project Name:zzj-db
 * File Name:CommercialInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.CommercialInfoMapper;
import com.zzj.db.dto.CommercialInfo;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>CommercialInfo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>CommercialInfo业务数据相关操作处理. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午1:48:48 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class CommercialInfoBlo {
	/**
	 * App_User_Info表操作Mapper
	 */
	@Autowired
	private CommercialInfoMapper commercialInfoMapper;
	/**
	 * 查询对应广告<br/>
	 * @param  slideResultVO 广告记录
	 * @return List<CommercialInfo> 广告列表
	 */
	public List<CommercialInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<CommercialInfo> commercialInfos = commercialInfoMapper.slideEditSearch(slideResultVO);
		return commercialInfos;
	}
	/**
	 * 插入记录<br/>
	 * @param  expertRecord 视频记录
	 * @return Integer 插入结果
	 */
	public Integer insert(CommercialInfo commercialInfo) {
		return commercialInfoMapper.insert(commercialInfo);
	}
	/**
	 * 根据主键查询记录<br/>
	 * @param  id 广告id
	 * @return CommercialInfo 广告记录
	 */
	public CommercialInfo editSearch(String id) {
		CommercialInfo commercialInfo = commercialInfoMapper.selectByPrimaryKey(id);
		return commercialInfo;
	}
	/**
	 * 根据条件更新Commercial记录<br/>
	 * @param  commercialInfo 广告记录
	 * @return Integer 更新记录
	 */
	public Integer update(CommercialInfo commercialInfo) {
		return commercialInfoMapper.updateByPrimaryKeySelective(commercialInfo);
	}
	/**
	 * 删除:根据条件更新Commercial记录的deleteFlag<br/>
	 * @param  commercialInfo 广告记录
	 * @return Integer 删除记录
	 */
	public Integer deleteAd(CommercialInfo commercialInfo) {
		return commercialInfoMapper.updateByPrimaryKeySelective(commercialInfo);
	}
}

