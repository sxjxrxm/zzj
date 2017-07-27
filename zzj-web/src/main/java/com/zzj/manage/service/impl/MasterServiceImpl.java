/**
 * Project Name:zzj-web
 * File Name:MasterServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.core.exception.BusinessException;
import com.zzj.db.blo.MstCodeInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstCodeInfoKey;
import com.zzj.manage.service.MasterService;
import com.zzj.util.StringUtil;

/**
 * <p><strong>类名: </strong></p>MasterServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装基础信息模块. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午3:58:10 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MasterServiceImpl implements MasterService {
	/**
	 * MstCodeInfo业务数据库操作类
	 */
	@Autowired
	private MstCodeInfoBlo mstCodeInfoBlo;
	/**
	 * 根据条件取得相应mstcodeinfo记录
	 * @param codeType 编码类型
	 * @return List<MstCodeInfo> 画面表示用code记录集
	 */
	@Override
	public List<MstCodeInfo> searchCodes(String codeType) {
		// 定义List表示结果集
		List<MstCodeInfo> codeList = new ArrayList<>();
		// 定义一个code表示查询条件
		MstCodeInfo code = new MstCodeInfo();
		// 为查询条件添加属性
		if (!(StringUtil.isBlank(codeType))) {
			code.setCodeType(codeType);
		}
		// 调用mstCodeInfoBlo查询code
		codeList = mstCodeInfoBlo.selectSelective(code);
		return codeList;
	}
	/**
	 * 删除:根据条件更新Mst_Code_Info表中相应记录的deleteflag字段<br/>
	 * @param codeType 编码类型
	 * @param code 编码
	 * @return Integer 更新条目数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer deleteCode(String codeType, String code) {
		// 实例化一个MstCodeInfo 用作更新条件
		MstCodeInfo codeInfo = new MstCodeInfo();
		// 为查询条件赋予属性
		codeInfo.setCodeType(codeType);
		codeInfo.setCode(code);
		codeInfo.setDeleteFlag(1);
		// 调用blo对code更新
		Integer x = mstCodeInfoBlo.updateByPrimaryKeySelective(codeInfo);
		if(x == 1){
			return x;
		}
		else{
			throw new BusinessException("删除操作错误");
		}
	}
	/**
	 * 修改查询:根据主键查询出对应记录<br/>
	 * @param codeType 编码类型
	 * @param code 编码
	 * @return MstCodeInfo 查询结果记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public MstCodeInfo editCodeSearch(String codeType, String code) {
		// 实例化一个联合主键用做查询条件
		MstCodeInfoKey mstCodeInfoKey = new MstCodeInfoKey();
		// 为查询条件赋值
		mstCodeInfoKey.setCodeType(codeType);
		mstCodeInfoKey.setCode(code);
		// 调用blo查询
		MstCodeInfo mstCodeInfo = mstCodeInfoBlo.selectByPrimaryKey(mstCodeInfoKey);
		if(mstCodeInfo != null){
			return mstCodeInfo;
		}
		else{
			return null;
		}
		
	}
	/**
	 * 更新数据库<br/>
	 * @param isAdd 操作类型
	 * @param codeInfo 更新信息
	 * @return Integer 查询结果记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer editCode(String isAdd, MstCodeInfo codeInfo) {
		
		// 为编辑条件赋予属性
		codeInfo.setDeleteFlag(0);
		codeInfo.setUpdateTime(new Date());
		
		// 定义变量表示受影响行数
		Integer x = 0;
		// 判断添加或编辑
		if("1".equals(isAdd)){
			// 调用blo添加记录
			x = mstCodeInfoBlo.insertSelective(codeInfo);
		}
		else{
			x = mstCodeInfoBlo.updateByPrimaryKeySelective(codeInfo); 
		}
		if(x == 1){
			return x;
		}
		else{
			throw new BusinessException("编辑操作");
		}
	}
	
}

