/**
 * Project Name:zzj-db
 * File Name:MstRankInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstRankInfoMapper;
import com.zzj.db.dto.MstRankInfo;

/**
 * <p><strong>类名: </strong></p>MstRankInfo <br/>
 * <p><strong>功能说明: </strong></p>MstRankInfo表相关数据操作处理 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午1:27:35 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstRankInfoBlo {
	/**
	 * MstRankInfo表操作Mapper
	 */
	@Autowired
	private MstRankInfoMapper mapper;
	
	/**
	 * 专家编辑页面职称联想输入信息<br/>
	 * @param 无
	 * @return 查询结果
	 */
	public String selectAllRankName() {
		// 根据业务需要，查找所有职称记录，前台联想输入使用
		List<MstRankInfo> mstRankInfo = mapper.selectAllRankName();
		StringBuffer sBuffer = new StringBuffer();
		if (mstRankInfo != null && mstRankInfo.size() > 0) {
			for (int i = 0; i < mstRankInfo.size(); i++) {
				sBuffer.append(mstRankInfo.get(i).getRankName()).append("    ");
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 根据职称名称查找记录
	 * @param  rankName 职称名称
	 * @return int  记录数
	 */
	public int selectByRankName(String rankName) {
		return mapper.selectByRankName(rankName);
	}

	/**
	 * 查找职称表中rankCd的最大值
	 * @param  无
	 * @return int  rankCd的最大值
	 */
	public int selectMaxRankCd() {
		return mapper.selectMaxRankCd();
	}

	/**
	 * 有条件的插入指定记录<br/>
	 * @param  record MstRankInfo记录
	 * @return Integer 插入结果
	 */
	public int addMstRankInfo(MstRankInfo record) {
		return mapper.insertSelective(record);
	}

}

