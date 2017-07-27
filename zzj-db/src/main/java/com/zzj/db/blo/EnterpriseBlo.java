/**
 * Project Name:zzj-db
 * File Name:EnterpriseBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.AppUsersInfoMapper;
import com.zzj.db.dao.CorpInfoMapper;
import com.zzj.db.dao.MstCodeInfoMapper;
import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.dto.CorpInfo;
import com.zzj.db.model.EnterpriseVO;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>EnterpriseBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装政企管理模块，数据库操作类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月6日下午3:49:14 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class EnterpriseBlo {
	
	/**
	 * CorpInfo表操作Mapper
	 */
	@Autowired
	private CorpInfoMapper corpInfoMapper;
	
	/**
	 * AppUsersInfoMapper表操作Mapper
	 */
	@Autowired
	private AppUsersInfoMapper appUsersInfoMapper;
	
	/**
	 * MstCodeInfoMapper表操作Mapper
	 */
	@Autowired
	private MstCodeInfoMapper mstCodeInfoMapper;
	
	/**
	 * CorpInfo表操作Mapper
	 * @param enterpriseVO 检索条件
	 * @return List<EnterpriseVO>  查询结果
	 */
	public List<EnterpriseVO> selectByParament(EnterpriseVO enterpriseVO){
		
		return corpInfoMapper.selectByParament(enterpriseVO);
	}
	
	/**
	 * CorpInfo表操作Mapper
	 * @param enterpriseVO 检索条件
	 * @return Integer  结果总数
	 */
	public Integer selectTotalCount(EnterpriseVO enterpriseVO){
		
		return corpInfoMapper.selectTotalCount(enterpriseVO);
	}
	
	/**
	 * CorpInfo表操作Mapper 分页记录
	 * @param enterpriseVO 检索条件
	 * @return List<EnterpriseVO>  查询结果
	 */
	public List<EnterpriseVO> selectPagging(EnterpriseVO enterpriseVO){
		
		return corpInfoMapper.selectPagging(enterpriseVO);
	}
	
	/**
	 * CorpInfo表操作Mapper
	 * @param userId 主键
	 * @return EnterpriseVO  查询结果
	 */
	public EnterpriseVO selectByKey(String userId){
		EnterpriseVO enterpriseVO = corpInfoMapper.selectByPrimaryKey(userId);
		
		if (enterpriseVO != null && enterpriseVO.getStatus() == ZzjConstants.STATUS_2 && StringUtil.isNotBlank(enterpriseVO.getRefuseMemo())) {
			if (StringUtil.isNumber(enterpriseVO.getRefuseMemo())) {
				// 查询系统配置表中拒绝理由对应code值的最大值
				int maxRefuseCode = mstCodeInfoMapper.selectMaxRefuseCode();
				if (Integer.parseInt(enterpriseVO.getRefuseMemo()) > 0 && Integer.parseInt(enterpriseVO.getRefuseMemo()) <= maxRefuseCode) {
					enterpriseVO.setRefuseMemoType(ZzjConstants.REFUSE_MEMO_TYPE_1);
				} else {
					enterpriseVO.setRefuseMemoType(ZzjConstants.REFUSE_MEMO_TYPE_2);
				}
				
			} else {
				enterpriseVO.setRefuseMemoType(ZzjConstants.REFUSE_MEMO_TYPE_2);
			}
		}
		
		
		
		return enterpriseVO;
	}
	
	/**
	 * CorpInfo表操作Mapper
	 * @param record 检索条件
	 * @return int 更新结果
	 */
	public int updateByKey(CorpInfo record){
		int updateInt = corpInfoMapper.updateByPrimaryKeySelective(record);
		
		// 普通用户成为政企用户时，更新成政企角色
		AppUsersInfo temp1 = appUsersInfoMapper.selectByPrimaryKey(record.getUserId());
		if (record.getStatus() == ZzjConstants.STATUS_1) {
			temp1.setRoleId(ZzjConstants.CORP_ROLE_IN_APP_INFO);
		}
		
		temp1.setUpdateId(record.getUpdateId());
		temp1.setUpdateTime(record.getUpdateTime());
		appUsersInfoMapper.updateByPrimaryKeySelective(temp1);
		
		return updateInt ;
	}
}

