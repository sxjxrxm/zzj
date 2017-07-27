/**
 * Project Name:zzj-db
 * File Name:MstCodeInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstCodeInfoMapper;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstCodeInfoKey;

/**
 * <p><strong>类名: </strong></p>MstCodeInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>Mst_Code_Info表相关数据操作处理 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午6:55:35 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstCodeInfoBlo {
	/**
	 * Mst_Code_Info表操作Mapper
	 */
	@Autowired
	private MstCodeInfoMapper mstCodeInfoMapper;

	/**
	 * 获取表Mst_Code_Info所有可用记录
	 * @param 无
	 * @return MstCodeInfo MstCodeInfo记录
	 */
	public List<MstCodeInfo> getAllMstCodeInfo() {
		List<MstCodeInfo> mstCodeInfos=mstCodeInfoMapper.selectAll();
		return mstCodeInfos;
	}
	/**
	 * 根据条件查询APP_Users_Info对应<br/>
	 * @param  code 查询记录对应
	 * @return List<MstCodeInfo> 查询结果
	 */
	public List<MstCodeInfo> selectSelective(MstCodeInfo code) {
		List<MstCodeInfo> codeList=mstCodeInfoMapper.selectSelective(code);
		return codeList;
	}
	/**
	 * 根据条件更新Mst_Code_Info中指定记录<br/>
	 * @param  code 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(MstCodeInfo code) {
		int x=mstCodeInfoMapper.updateByPrimaryKeySelective(code);
		return x;
	}
	/**
	 * 主键查询Mst_Code_Info中指定记录<br/>
	 * @param  mstCodeInfoKey Mst_Code_Info记录联合主键 查询条件
	 * @return MstCodeInfo 查询结果记录
	 */
	public MstCodeInfo selectByPrimaryKey(MstCodeInfoKey mstCodeInfoKey) {
		MstCodeInfo codeInfo = mstCodeInfoMapper.selectByPrimaryKey(mstCodeInfoKey);
		return codeInfo;
	}
	/**
	 * 向表Mst_Code_Info中插入记录<br/>
	 * @param  codeInfo 插入记录
	 * @return int 受影响行数
	 */
	public int insert(MstCodeInfo codeInfo) {
		int x = mstCodeInfoMapper.insert(codeInfo);
		return x;
	}
	/**
	 * 根据主键修改表Mst_Code_Info中记录<br/>
	 * @param  codeInfo 修改记录
	 * @return int 受影响行数
	 */
	public int updateByPrimaryKey(MstCodeInfo codeInfo) {
		int x =mstCodeInfoMapper.updateByPrimaryKey(codeInfo);
		return x;
	}
	/**
	 * 有条件的向表Mst_Code_Info中插入记录<br/>
	 * @param  codeInfo 修改记录
	 * @return int 受影响行数
	 */
	public int insertSelective(MstCodeInfo codeInfo) {
		int x = mstCodeInfoMapper.insertSelective(codeInfo);
		return x;
	}
	/**
	 * 得到全部技能（不受deleteflag影响）<br/>
	 * @param  无
	 * @return List<MstCodeInfo> 查询结果
	 */
	public List<MstCodeInfo> getAllBusis() {
		List<MstCodeInfo> codeInfos = mstCodeInfoMapper.getAllBusis();		
		return codeInfos;
	}
}

