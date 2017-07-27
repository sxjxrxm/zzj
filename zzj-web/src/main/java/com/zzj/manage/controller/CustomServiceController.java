/**
 * Project Name:zzj-web
 * File Name:CustomServiceController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstServiceInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.manage.service.CustomServiceService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ValidateUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>CustomServiceController <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装客服编辑，客服编辑的控制器. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日下午4:49:11 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping(value="customservice")
public class CustomServiceController extends BaseController {
	
	/**
	 * 客服编辑业务操作Service
	 */
	@Autowired
	CustomServiceService customServiceService;
	
	/**
	 * 客服编辑页面跳转<br/>
	 * @param  model 画面实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/edit.htm")
	public String toLoginCustomService (Model model) throws Exception {
		
		// 查询原客服信息记录
		MstServiceInfo mstServiceInfo = customServiceService.selcetOldMstService();
		if(mstServiceInfo != null) {
			// 取得图片URL链接
			String imgPath = StringUtil.getImageURL(mstServiceInfo.getQrcodeAddress());
			mstServiceInfo.setQrcodeAddressUrl(imgPath);
		}
		
		model.addAttribute ("mstServiceInfoList",mstServiceInfo);
		
		return "customservice/customservice_edit";
	}
	
	/**
	 * 插入新客服记录<br/>
	 * @param  mstServiceInfo 新增对象
	 * @param  model 画面实例
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/addEdit.htm")
	public String addNewCustomService (MstServiceInfo mstServiceInfo, Model model, HttpServletRequest request) throws Exception {
		
		// 获取用户信息
		MstUsersInfo existUser = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		mstServiceInfo.setUpdateId(existUser.getUserId());
		mstServiceInfo.setUpdateTime(new Date());
		// 插入更新的客服信息
		String email = mstServiceInfo.getEmail();
		String phoneNo = mstServiceInfo.getPhoneNo();
		String weChat=mstServiceInfo.getWeChat();
		String qrcodeAddress=mstServiceInfo.getQrcodeAddress();
		String memo = mstServiceInfo.getMemo();
			
		// 错误信息
		ValidateErrorException exception = null;
		
		// 客服邮箱_判断null
		if (StringUtil.isBlank(email)) {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"客服邮箱"}, "customservice/customservice_edit", "email");
		} else {
			// 客服邮箱_判断长度
			if (!(email.length() <= 50)) {
				exception = this.rebuildException(exception, "E1000005", new Object[] {"客服邮箱","50"}, "customservice/customservice_edit", "email");
			}
			// 客服邮箱_判断格式
			if (!ValidateUtils.validMail(email)) {
				exception = this.rebuildException(exception, "E1000004", new Object[] {"客服邮箱",""}, "customservice/customservice_edit", "email");
			}
		}

		boolean b=phoneNo.length() <=11;
/*		boolean b1=ValidateUtils.validCellphone(phoneNo);
		boolean b2=ValidateUtils.validTelphone(phoneNo);*/
		
		// 客服电话_判断null
		if (StringUtil.isBlank(phoneNo)) {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"客服电话"}, "customservice/customservice_edit", "phoneNo");
		} else {
			// 客服电话_判断长度
			if (!b) {
				exception = this.rebuildException(exception, "E1000005", new Object[] {"客服电话","11"}, "customservice/customservice_edit", "phoneNo");
			} 
/*			// 客服电话_判断格式
			if (b1 || b2) {
				exception = this.rebuildException(exception, "E1000004", new Object[] {"客服电话",""}, "customservice/customservice_edit", "phoneNo");
			}*/
		}

		// 微信公众号验证
		// 微信公众号_判断null
		if (StringUtil.isBlank(weChat)) {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"微信公众号"}, "customservice/customservice_edit", "weChat");
		} else {
			// 微信公众号_判断长度
			if (weChat.length() > 50) {
				exception = this.rebuildException(exception, "E1000005", new Object[] {"微信公众号","50"}, "customservice/customservice_edit", "weChat");
			}

		}
		
		// 二维码地址_判断null
		if (StringUtil.isBlank(qrcodeAddress)) {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"二维码"}, "customservice/customservice_edit", "preview");
		}

		// 备注_判断
		if (!(memo.length() <= 500)) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"备注","500"}, "customservice/customservice_edit", "memo");
		}
		
		// 取得图片URL链接
		String imgPath = StringUtil.getImageURL(mstServiceInfo.getQrcodeAddress());
		mstServiceInfo.setQrcodeAddressUrl(imgPath);
		
		if (exception != null) {
			// 选项回显
			request.setAttribute("mstServiceInfoList", mstServiceInfo);
			throw exception;
		} else {
			// 逻辑删除原客服信息
			customServiceService.delOldMstService(mstServiceInfo);
			// 插入新的客服信息
			customServiceService.addCustomService(mstServiceInfo);

			model.addAttribute("mstServiceInfoList", mstServiceInfo);
		}

		// 选项回显
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		return "customservice/customservice_edit";
	}
	
	/**
	 * 图片异步请求<br/>
	 * @param  multiPartRquest 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/upload.htm")
	public Map<String, String> queryPic (MultipartRequest multiPartRquest, HttpServletRequest req) throws Exception {
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		// 图片验证
		String message = UploadUtils.checkQRCodeImg(multipartFile);
		if (StringUtil.isNotBlank(message))
		{
			resultMap.put("message", message);
			return resultMap;
		}
		try {
			// web服务器存储路径
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_OTHER, ZzjConstants.BUSI_TYPE_99);
			resultMap.put("path", realPath);
			// 取得图片URL链接
			resultMap.put("url", StringUtil.getImageURL(realPath));
			resultMap.put("message", ZzjConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回图片地址
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

