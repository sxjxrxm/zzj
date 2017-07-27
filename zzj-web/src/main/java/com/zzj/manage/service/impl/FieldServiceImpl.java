/**
 * Project Name:zzj-web
 * File Name:FilterServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.db.blo.MstCodeInfoBlo;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstFieldInfo;
import com.zzj.db.model.FieldResultVO;
import com.zzj.manage.service.FieldService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>FieldServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>领域配置业务实现类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日上午9:20:17 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class FieldServiceImpl implements FieldService {
	
	/**
	 * 领域业务数据库操作类
	 */
	@Autowired
	private MstFieldInfoBlo blo;
	
	/**
	 * 系统信息数据库操作类
	 */
	@Autowired
	private MstCodeInfoBlo mstCodeInfoBlo;

	/**
	 * 取得画面表示用内容
	 * @param techFieldCd 技术领域编码
	 * @return FieldResultVO 画面表示用内容
	 */
	@Override
	public FieldResultVO searchFieldList(String techFieldCd) {
		
		// 取得对应的研究领域
		List<MstFieldInfo> rschFieldInfo = blo.selectByField(techFieldCd);
		
		// 取得所有的研究领域
		MstCodeInfo mstCodeInfoJkn = new MstCodeInfo();
		mstCodeInfoJkn.setCodeType("rschFieldType");
		List<MstCodeInfo> rschFieldList = mstCodeInfoBlo.selectSelective(mstCodeInfoJkn);
		
	    // 获得不属于该需求的研究领域
		List<MstCodeInfo> otherRschFieldInfo = new ArrayList<MstCodeInfo>();
		boolean isContains = false;
		for (MstCodeInfo m :rschFieldList) {
			isContains = false;
			for (MstFieldInfo t : rschFieldInfo) {
				if (m.getCode().equals(t.getRschFieldCd())) {
					isContains = true;
				}
			}
			if (!isContains) {
				otherRschFieldInfo.add(m);
			}
		}
		
		FieldResultVO fieldResultVO = new FieldResultVO();
		fieldResultVO.setOtherRschFieldInfo(otherRschFieldInfo);
		fieldResultVO.setRschFieldInfo(rschFieldInfo);
			
		return fieldResultVO;
	}

	/**
	 * 保存筛选条件。
	 * @param  request http请求实例
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveField(HttpServletRequest request) {
		// 选择的技术领域
		String techFieldCd = request.getParameter("techFieldCd");
		// 选择的研究领域
		String[] fieldCd = request.getParameterValues("fieldCd");
		
		// 即存研究领域逻辑删除
		MstFieldInfo mstFieldInfo = new MstFieldInfo();
		blo.updateByTechField(techFieldCd);
		
		if(StringUtil.isNotBlank(fieldCd))
		{
			// 保存研究领域
			for (int i = 0; i<fieldCd.length; i++) {
				// 根据主键查询是否存在
				mstFieldInfo = new MstFieldInfo();
				mstFieldInfo.setTechFieldCd(techFieldCd);
				mstFieldInfo.setRschFieldCd(fieldCd[i]);
				mstFieldInfo.setDeleteFlag(0);
				MstFieldInfo temp = blo.getMstFieldInfo(mstFieldInfo);
				if (temp != null) {
					// 如果存在，删除flag更新为0				
					blo.saveMstFieldInfo(mstFieldInfo);
				} else {
					// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
					blo.addMstFieldInfo(mstFieldInfo);
				}
			}
		}
	}
	
	/**
	 * 保存领域表。
	 * @param  String 技术领域编码
	 */
	@Override
	public void insertTechField(String techFieldCd) {
		MstFieldInfo mstFieldInfo = new MstFieldInfo();
		mstFieldInfo.setTechFieldCd(techFieldCd);
		mstFieldInfo.setRschFieldCd(ZzjConstants.BLANK);
		mstFieldInfo.setDeleteFlag(0);
		MstFieldInfo temp = blo.getMstFieldInfo(mstFieldInfo);
		if (temp != null) {
			// 如果存在，删除flag更新为0				
			blo.saveMstFieldInfo(mstFieldInfo);
		} else {
			// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
			blo.addMstFieldInfo(mstFieldInfo);
		}
	}
	
	/**
	 * 取得画面表示用内容
	 * @param techFieldCd 技术领域编码
	 * @return FieldResultVO 领域结果信息
	 */
	@Override
	public FieldResultVO searchRschFieldList(String techFieldCd) {
		// 取得所有的研究领域
		MstCodeInfo mstCodeInfoJkn = new MstCodeInfo();
		mstCodeInfoJkn.setCodeType("rschFieldType");
		List<MstCodeInfo> rschFieldList = mstCodeInfoBlo.selectSelective(mstCodeInfoJkn);
		
		FieldResultVO fieldResultVO = new FieldResultVO();
		fieldResultVO.setOtherRschFieldInfo(rschFieldList);
		return fieldResultVO;
	}
	
	/**
	 * 更新数据库<br/>
	 * @param codeInfo 更新信息
	 * @return Integer 查询结果记录
	 */
	@Override
	public Integer insert(MstCodeInfo codeInfo) {		
		// 更新数据库
		return mstCodeInfoBlo.insertSelective(codeInfo);
	}
	
	/**
	 * 取得画面表示用内容
	 * @param  request http请求实例
	 * @return Map<String, Object> 编码信息
	 */
	@Override
	public Map<String, Object> setCodeInfo(HttpServletRequest request) {
		
		// 类别
		String codeType = request.getParameter("codeType");
		// 项目编码
		String code = request.getParameter("code");
		// 项目名称
		String codeName = request.getParameter("codeName");

		MstCodeInfo codeInfo = new MstCodeInfo();
		StringBuilder message = new StringBuilder(); 
		if (StringUtil.isNotBlank(codeType))
		{
			codeInfo.setCodeType(codeType);
		}

		if (StringUtil.isNotBlank(code))
		{
			codeInfo.setCode(code);
		}
		if (StringUtil.isNotBlank(codeName))
		{
			codeInfo.setCodeName(codeName);
		}
		else {
			message.append(PropertyUtil.getMessageContent("E1000001", new Object[] {"项目名称"}));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (message.length() > 0)
		{
			map.put("message", message.toString());
		}
		else
		{
			if (ZzjConstants.TECH_FIELD_TYPE.equals(codeInfo.getCodeType()))
			{
				codeInfo.setCodeTypeName("技术领域");
			}
			if (ZzjConstants.RSCH_FIELD_TYPE.equals(codeInfo.getCodeType()))
			{
				codeInfo.setCodeTypeName("研究领域");
			}	
			codeInfo.setDeleteFlag(0);
			codeInfo.setUpdateTime(new Date());
			map.put("message", ZzjConstants.SUCCESS);
			map.put("data", codeInfo);
		}
		
		return map;
	}
}