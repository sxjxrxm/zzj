/**
 * Project Name:zzj-web
 * File Name:VersionServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.VersionInfoBlo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.VersionInfo;
import com.zzj.manage.service.VersionService;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>VersionService <br/>
 * <p><strong>功能说明: </strong></p>版本控制模块 <br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:09:22 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class VersionServiceImpl implements VersionService {

	/**
	 * 版本数据库操作类
	 */
	@Autowired
	private VersionInfoBlo versionInfoBlo;
	
	/**
	 * 加载页面数据。<br/>
	 * @param  request 请求实例
	 */
	@Override
	public void load(HttpServletRequest request) {
		String appType = request.getParameter("appType");

		if (StringUtil.isBlank(appType)) {
			return;
		}
		
		VersionInfo info = null;
		if (StringUtil.isNumber(appType)) {
			switch (Integer.parseInt(appType)) {
			case 1:
				info = versionInfoBlo.selectByAppType(ZzjConstants.APP_TYPE_ANDROID);
				break;
			case 2:
				info = versionInfoBlo.selectByAppType(ZzjConstants.APP_TYPE_IOS);
				break;
			default:
				break;
			}
		}
		if (info == null) {
			info = new VersionInfo();
		}
		if (info.getAppType() == null) {
			info.setAppType(Integer.parseInt(appType));
		}
		
		request.setAttribute("versionInfo", info);
	}

	
	/**
	 * 保存apk
	 * @param  request 请求实例
	 * @return 保存结果 true 成功 false 失败
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean save(HttpServletRequest request) throws Exception {
		VersionInfo record = this.parse(request);
		if (record == null 
				|| record.getAppType() == null 
				|| (record.getAppType() != ZzjConstants.APP_TYPE_ANDROID 
				&& record.getAppType() != ZzjConstants.APP_TYPE_IOS)) {
			return false;
		}
		
		VersionInfo temp = versionInfoBlo.selectByAppType(record.getAppType());
		if (temp != null) {
			temp.setDeleteFlag(1);
			versionInfoBlo.updateByPrimaryKeySelective(temp);
		}
		
		int i = versionInfoBlo.insertSelective(record);
		return i == 1 ? true : false;
	}

	/**
	 * 解析请求实例
	 * @param  request 请求实例
	 * @return VersionInfo record
	 * @throws Exception 
	 */
	private VersionInfo parse(HttpServletRequest request) throws Exception {
		
		String appTypeStr = request.getParameter("appType");
		String versionNo = request.getParameter("versionNo");
		String versionAddress1 = request.getParameter("versionAddress1");
//		String createTimeStr = request.getParameter("createTime");
		String versionAddress2 = request.getParameter("versionAddress2");
		String modifyMemo = request.getParameter("modifyMemo");
		
		// 输入信息的校验
		// 错误信息
		ValidateErrorException exception = null;
//		SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
		VersionInfo record = new VersionInfo();
		
		if (StringUtil.isBlank(appTypeStr) || !StringUtil.isNumber(appTypeStr)) {
			exception = new ValidateErrorException("E1000001", new Object[] {"APP客户端"}, "version/version_edit", "appType");
		}
		if (StringUtil.isBlank(versionNo)) {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"版本号"}, "version/version_edit", "versionNo");
		}
		Integer appType = Integer.parseInt(appTypeStr);
		if (ZzjConstants.APP_TYPE_ANDROID == appType) {
			if (StringUtil.isBlank(versionAddress1)) {
				exception = this.rebuildException(exception, "E1000001", new Object[] {"APK"}, "version/version_edit", "versionAddress1");
			} else {
				record.setVersionAddress(versionAddress1);
				record.setCreateTime(new Date());
			}
		}
//		if (StringUtil.isNotBlank(createTimeStr)) {
//			try {
//				record.setCreateTime(sdf.parse(createTimeStr));
//			} catch (ParseException e) {
//				exception = this.rebuildException(exception, "E1000001", new Object[] {"APK"}, "version/version_edit", "createTime");
//			}
//		}
		if (ZzjConstants.APP_TYPE_IOS == appType) {
			if (StringUtil.isBlank(versionAddress2)) {
				exception = this.rebuildException(exception, "E1000001", new Object[] {"IOS链接"}, "version/version_edit", "versionAddress2");
			} else {
				record.setVersionAddress(versionAddress2);
				record.setCreateTime(new Date());
			}
		}
		if (StringUtil.isBlank(modifyMemo)) {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"更新内容"}, "version/version_edit", "modifyMemo");
		}
		
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		
		record.setCreateId(user.getUserId());
		record.setAppType(appType);
		record.setVersionNo(versionNo);
		record.setModifyMemo(modifyMemo);
		record.setDeleteFlag(0);
		
		request.setAttribute("versionInfo", record);
		
		// 有异常发生
		if (exception != null) {
			throw exception;
		}
		
		return record;
	}


	/**
	 * 页面Apk异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@Override
	public Map<String, String> queryApk(MultipartRequest file, HttpServletRequest req) {
		// 获得请求Apk
		MultipartFile multipartFile = file.getFile("apkData");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		String versionNo = req.getParameter("versionNo");
		
		try {
			// 执行Apk上传
			realPath = UploadUtils.uploadApk(multipartFile, versionNo);
			// web服务器存储路径
			resultMap.put("path", realPath);
			// 取得Apk URL链接
//			resultMap.put("url", StringUtil.getApkURL(realPath));
			// 获取当前日期并格式化
			SimpleDateFormat dateFormater = new SimpleDateFormat(ZzjConstants.datePattern);
			resultMap.put("time", dateFormater.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回Apk地址
		return resultMap;
	}

	/**
	 * 重构ValidateErrorException实例。<br/>
	 * @param  exception ValidateErrorException
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorItemKey 错误项目ID
	 */
	private ValidateErrorException rebuildException (ValidateErrorException exception, String errorCode, Object[] msgArgs, String errorPage, String errorItemKey) {
		if (exception == null) {
			exception = new ValidateErrorException(errorCode, msgArgs, errorPage, errorItemKey);
		} else {
			exception.addError(errorCode, msgArgs, errorItemKey);
		}
		return exception;
	}
	
}

