/**
 * Project Name:zzj-db
 * File Name:MstAreaInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstAreaInfoMapper;
import com.zzj.db.dto.MstAreaInfo;

/**
 * <p><strong>类名: </strong></p>MstAreaInfo <br/>
 * <p><strong>功能说明: </strong></p>MstAreaInfo表相关数据操作处理 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月25日下午5:55:35 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstAreaInfoBlo {
	/**
	 * MstAreaInfo表操作Mapper
	 */
	@Autowired
	private MstAreaInfoMapper mapper;

//	/**
//	 * 根据专家编辑页面城市显示市级城市信息<br/>
//	 * @throws Exception
//	 */
//	public List<MstAreaInfo> selectAll() {
//		// 根据业务需要，查找所有parentId不为0和1的记录
//		return mapper.selectAllCity();
//	}

	/**
	 * 根据专家编辑页面城市显示信息<br/>
	 * 根据父级id获得相应的下级信息<br/>
	 * @param Double parentId 查询条件
	 * @return List<MstAreaInfo> 查询结果
	 */
	public List<MstAreaInfo> selectAllByParentId(Double parentId) {
		return mapper.selectAllByParentId(parentId);
	}

	/**
	 * 根据区域代码获得区域id，用户后续查询操作<br/>
	 * @param  regionCode 区域代码
	 * @return Double  区域id
	 */
	public Double selectRegionIdByRegionCode(String regionCode) {
		return mapper.selectRegionIdByRegionCode(regionCode);
	}
}

