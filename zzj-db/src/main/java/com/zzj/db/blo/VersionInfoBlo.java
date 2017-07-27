/**
 * Project Name:zzj-db
 * File Name:VersionInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.VersionInfoMapper;
import com.zzj.db.dto.VersionInfo;

/**
 * <p><strong>类名: </strong></p>VersionService <br/>
 * <p><strong>功能说明: </strong></p>版本控制模块 <br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:09:22 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class VersionInfoBlo {
	/**
	 * Video_Infor表操作Mapper
	 */
	@Autowired
	private VersionInfoMapper versionInfoMapper;
	/**
	 * 根据主键查询Version_Info中指定记录<br/>
	 * @param  appType 查询条件
	 * @return VersionInfo 取得结果
	 */
	public VersionInfo selectByAppType(Integer appType) {
		return versionInfoMapper.selectByAppType(appType);
	}
	
	/**
	 * 根据主键更新记录
	 * @param record 更新记录对应
	 * @return int 更新结果条目数
	 */
	public int updateByPrimaryKeySelective(VersionInfo record) {
		return versionInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 根据条件插入指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
	public int insertSelective(VersionInfo record) {
		return versionInfoMapper.insertSelective(record);
	}

}

