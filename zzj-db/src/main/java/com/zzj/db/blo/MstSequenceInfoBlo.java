/**
 * Project Name:zzj-db
 * File Name:MstSequenceInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstSequenceInfoMapper;
import com.zzj.db.dto.MstSequenceInfo;

/**
 * <p><strong>类名: </strong></p>MstSequenceInfoBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>MstSequenceInfoBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月02日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstSequenceInfoBlo {

	/**
	 * MstSequenceInfo表操作Mapper
	 */
	@Autowired
	private MstSequenceInfoMapper mstSequenceInfoMapper;

	/**
	 * 获取自动编号SequenceInfo
	 * @param  String sequenceType 自动编码类别
	 * @return String SequenceInfo 自动编号
	 */
    public String selectSequenceInfo(String sequenceType){
    	MstSequenceInfo record = mstSequenceInfoMapper.selectByPrimaryKey(sequenceType);
    	record.setNowNo(record.getNextNo());
    	record.setUpdateTime(new Date());
    	mstSequenceInfoMapper.updateByPrimaryKeySelective(record);
    	return record.getKeySq();
    }
}

