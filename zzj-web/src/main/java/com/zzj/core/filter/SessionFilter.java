/**
 * Project Name:zzj-web
 * File Name:SessionFilter.java
 * Package Name:com.zzj.core.filter
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

/**
 * <p><strong>类名: </strong></p>SessionFilter <br/>
 * <p><strong>功能说明: </strong></p>会话过滤器. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午6:44:24 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.6	 
 */
public class SessionFilter {
	protected FilterConfig filterConfig;
	private String redirectURL;
	private List<String> notCheckURLList;
	private String sessionKey;

	public SessionFilter() {
		filterConfig = null;
		redirectURL = null;
		notCheckURLList = new ArrayList<String>();
		sessionKey = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey  = filterConfig.getInitParameter("checkSessionKey");

		String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
		if (notCheckURLListStr != null) {
			notCheckURLListStr = StringUtils.replace(notCheckURLListStr, "*", "");
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			for (; st.hasMoreTokens(); notCheckURLList.add(st.nextToken().trim()));
		}
	}

	public void destroy() {
		notCheckURLList.clear();
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if (sessionKey == null) {
			filterChain.doFilter(request, response);
			return;
		}
		if (!notCheckRequestURIIntFilterList(request)) {
			if (session.getAttribute(sessionKey) == null) {
				response.sendRedirect(request.getContextPath() + redirectURL);
				return;
			}else
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
	}

	/**
	 * 检查该URI是否不需要进行session验证
	 * @param request http请求对象
	 * @return boolean true:不需要session验证; false:需要session验证
	 * */
	private boolean notCheckRequestURIIntFilterList(HttpServletRequest request) {
		String uri = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");
		boolean result = false;
		if (notCheckURLList != null && notCheckURLList.size() > 0) {
			for (int i = 0; i < notCheckURLList.size(); i++) {
				if (uri.indexOf((String) notCheckURLList.get(i)) == 0) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
}

