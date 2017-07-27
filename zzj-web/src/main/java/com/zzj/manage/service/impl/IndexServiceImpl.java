/**
 * Project Name:zzj-web
 * File Name:IndexServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.blo.MstCodeInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.manage.service.IndexService;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>IndexServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>这个类是系统主页模块的代码. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午4:06:44 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class IndexServiceImpl implements IndexService {

	/**
	 * 系统配置信息操作类
	 */
	@Autowired
	private MstCodeInfoBlo blo;
	
	/**
	 * 将系统配置信息存入session，页面显示调用<br/>
	 * @param request http请求实例
	 * @return true 保存成功， false 保存失败
	 */
	@Override
	public boolean saveMstCodeInfoToSessionSuccess(HttpServletRequest request) {

		List<MstCodeInfo> mstCodeInfos = blo.getAllMstCodeInfo();
		if(mstCodeInfos != null && mstCodeInfos.size() > 0) {
			request.getSession().setAttribute(ZzjConstants.SYS_SESSION_MSTCODEINFOS, mstCodeInfos);
		}
		else {
			return false;
		}
		MstCodeInfo code = new MstCodeInfo();
		code.setCodeType(ZzjConstants.TECH_FIELD_TYPE);
		List<MstCodeInfo> techfieldCodeInfos = blo.selectSelective(code);
		if(techfieldCodeInfos != null && techfieldCodeInfos.size() > 0) {
			request.getSession().setAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS, techfieldCodeInfos);
		}
		else {
			return false;
		}
		code.setCodeType(ZzjConstants.RSCH_FIELD_TYPE);
		List<MstCodeInfo> rschfieldCodeInfos = blo.selectSelective(code);
		if(techfieldCodeInfos != null && techfieldCodeInfos.size() > 0) {
			request.getSession().setAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS, rschfieldCodeInfos);
		}
		else {
			return false;
		}
		return true;
	}

}

