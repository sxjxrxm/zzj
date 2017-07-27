/**
 * Project Name:zzj-web
 * File Name:AuthServiceImpl.java
 * Package Name:com.zzj.core.web.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.core.web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zzj.core.web.service.AuthService;
import com.zzj.db.dto.MstScreenInfo;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>AuthService <br/>
 * <p><strong>功能说明: </strong></p>页面访问权限Service<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午6:52:03 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class AuthServiceImpl implements AuthService {

	/**
	 * 验证是否有权限访问该页面<br/>
	 * @param  uri 访问路径
	 * @param  request http请求对象
	 * @return boolean 是否有权限（true:有;false:无）
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isAuth(HttpServletRequest request, String uri) throws Exception{

		boolean authResult = false;
		
		if (uri.endsWith(ZzjConstants.INDEX_URL)) return true;
		
		uri = uri.substring(1, uri.lastIndexOf('/'));
		
		// 从Session中取得权限一览表
		if(null == request.getSession().getAttribute(ZzjConstants.SYS_SESSION_ROLE)){
			return authResult;
		}
		List<MstScreenInfo> screenList = (List<MstScreenInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_ROLE);
		
		// 遍历权限一览，判断当前访问是否满足权限要求
		for (MstScreenInfo mstScreenInfo : screenList) {
			// 取得页面链接
			String link = mstScreenInfo.getLinkUrl();
			// 判断链接是否为空
			if(StringUtil.isNullOrEmpty(link)) {
				// 为空的场合，继续检查下一个
				continue;
			}
			// 判断当前访问是否满足权限要求
			if(link.contains(uri)) {
				// 满足的场合，返回true，且退出循环
				authResult = true;
				break;
			}
		}
		
		return authResult;
	}

}

