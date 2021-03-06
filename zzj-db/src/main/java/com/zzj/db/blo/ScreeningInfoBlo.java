/**
 * Project Name:zzj-db
 * File Name:ScreeningInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import com.zzj.db.dto.ScreeningInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.ScreeningInfoMapper;

/**
 * <p><strong>类名: </strong></p>ScreeningInfoBlo<br/>
 * <p><strong>功能说明: </strong></p>ScreeningInfo表操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月22日下午1:56:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */

@Service
public class ScreeningInfoBlo {
	
	@Autowired
	private ScreeningInfoMapper screeningInfoMapper;

	/**
	 * 选择全部机能的筛选条件<br/>
	 * @param 无
   	 * @return List<ScreeningInfo> 全部机能的筛选条件
	 */
    public List<ScreeningInfo> selectAllScreeningInfo()
    {   	
		List<ScreeningInfo> list = screeningInfoMapper.selectAllScreeningInfo();
		return list;
    }
    
    /**
	 * 插入指定记录<br/>
	 * @param  record ScreeningInfo记录
	 * @return int 插入结果
	 */
    public int insert(ScreeningInfo record)
    {
		return screeningInfoMapper.insert(record);
    }

	/**
	 * 根据主键查询ScreeningInfo中指定记录<br/>
	 * @param  busiType busiType
	 * @return ScreeningInfo 取得结果
	 */
    public ScreeningInfo selectByPrimaryKey(String busiType)
    {
		ScreeningInfo screeningInfo = screeningInfoMapper.selectByPrimaryKey(busiType);
		return screeningInfo;
    }

	/**
	 * 根据主键更新ScreeningInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
	 */
    public int updateByPrimaryKey(ScreeningInfo record)
    {
		return screeningInfoMapper.updateByPrimaryKey(record);
    }    
}