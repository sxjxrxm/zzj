/**
 * Project Name:zzj-html
 * File Name:HtmlInfoService.java
 * Package Name:com.zzj.html.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p><strong>类名: </strong></p>HtmlInfoService <br/>
 * <p><strong>功能说明: </strong></p>知识H5页面操作Service<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月16日上午10:36:26 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface HtmlService {

	/**
	 * 知识H5页面生成处理
	 * @param infoId 知识ID
	 * @return String 知识H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateInfoHtml(HttpServletRequest request, String infoId) throws Exception;

	/**
	 * 专家简介H5页面生成处理
	 * @param expertId 专家ID
	 * @return String 专家简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateExpertHtml(HttpServletRequest request, String expertId) throws Exception;

	/**
	 * 视频简介H5页面生成处理
	 * @param videoId 视频ID
	 * @return String 视频简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateVideoHtml(HttpServletRequest request, String videoId) throws Exception;

	/**
	 * 直播简介H5页面生成处理
	 * @param liveId 直播ID
	 * @return String 直播简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateLiveHtml(HttpServletRequest request, String liveId) throws Exception;

	/**
	 * 课堂简介H5页面生成处理
	 * @param courseId 课堂ID
	 * @return String 课堂简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateCourseHtml(HttpServletRequest request, String courseId) throws Exception;
	
	/**
	 * 知识H5页面URL生成处理
	 * @param infoId 知识ID
	 * @return String 知识H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateInfoHtmlUrl(String infoId) throws Exception;

	/**
	 * 专家简介H5页面URL生成处理
	 * @param expertId 专家ID
	 * @return String 专家简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateExpertHtmlUrl(String expertId) throws Exception;

	/**
	 * 视频简介H5页面URL生成处理
	 * @param videoId 视频ID
	 * @return String 视频简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateVideoHtmlUrl(String videoId) throws Exception;

	/**
	 * 直播简介H5页面URL生成处理
	 * @param liveId 直播ID
	 * @return String 直播简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateLiveHtmlUrl(String liveId) throws Exception;

	/**
	 * 课堂简介H5页面URL生成处理
	 * @param courseId 课堂ID
	 * @return String 课堂简介H5页面URL链接 
	 * @throws Exception
	 */
	public abstract String generateCourseHtmlUrl(String courseId) throws Exception;

	/**
	 * 生成所有HTML页面
	 * @return String 生成结果
	 * @throws Exception
	 */
	public abstract String generateAllHtml() throws Exception;
}

