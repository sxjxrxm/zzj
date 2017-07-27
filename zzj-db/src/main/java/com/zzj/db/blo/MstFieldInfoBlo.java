/**
 * Project Name:zzj-db
 * File Name:MstFieldInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstFieldInfoMapper;
import com.zzj.db.dto.MstFieldInfo;
import com.zzj.db.dto.MstFieldInfoKey;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>MstFieldInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>MstFieldInfoBlo表相关数据操作处理 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:55:35 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstFieldInfoBlo {
	/**
	 * MstFieldInfoBlo表操作Mapper
	 */
	@Autowired
	private MstFieldInfoMapper mapper;
	
	/**
	 * MstFieldInfo记录取得处理
	 * @param  key 检索条件
	 * @return MstFieldInfo MstFieldInfo记录
	 */
	public MstFieldInfo getMstFieldInfo(MstFieldInfoKey key) {
		MstFieldInfo mstFieldInfo = mapper.selectByPrimaryKey(key);
		
		return mstFieldInfo;
	}

	/**
	 * MstFieldInfo记录保存处理
	 * @param  mstFieldInfo MstFieldInfo记录
	 * @return int 保存结果
	 */
	public int saveMstFieldInfo(MstFieldInfo mstFieldInfo) {
		int result = 0;
		if(mstFieldInfo != null) {
			result = mapper.updateByPrimaryKey(mstFieldInfo);
		}
		
		return result;
	}

	/**
	 * MstFieldInfo记录追加处理
	 * @param  mstFieldInfo MstFieldInfo记录
	 * @return int 追加结果
	 */
	public int addMstFieldInfo(MstFieldInfo mstFieldInfo) {
		int result = 0;
		if(mstFieldInfo != null) {
			result = mapper.insert(mstFieldInfo);
		}
		
		return result;
	}

	/**
	 * 获取所有MstFieldInfo可用记录
	 * @param 无
	 * @return List<TopicFieldInfoKey> 所有TopicFieldInfoKey可用记录
	 * @throws Exception
	 */
	public List<TopicFieldInfoKey> selectAll() {
		return mapper.selectAll();
	}
	
	/**
	 * 获取对应技术领域的MstFieldInfo可用记录
	 * @param techFieldCd 技术领域编码
	 * @return List<MstFieldInfo> 所有MstFieldInfo可用记录
	 */
	public List<MstFieldInfo> selectByField(String techFieldCd) {
		return mapper.selectByField(techFieldCd);
	}
	
	/**
	 * 技术领域对应的研究领域物理删除
	 * @param  techField 选择的技术领域
	 * @return int 保存结果
	 */
	public int updateByTechField(String techField) {
		int result = 0;
		result = mapper.updateByTechField(techField);		
		return result;
	}
}