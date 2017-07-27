/**
 * Project Name:zzj-web
 * File Name:LiveService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.LiveInfoVO;

/**
 * <p><strong>类名: </strong></p>LiveService业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>直播业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午1:24:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface LiveService {

	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 */
	void selectKeySave(HttpServletRequest request) throws Exception;

	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param pagging 分页
	 * @return LiveInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	LiveInfoVO getSaveInfo(HttpServletRequest request, String string) throws Exception;
	
	/**
	 * 获得不属于该视频的领域
	 * @param List<TopicFieldInfoKey> 属于该视频的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该视频的领域
	 */
	public List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo);

	/**
	 * 根据主键更新记录
	 * @param  LiveInfoVO record 视频信息
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	int save(LiveInfoVO record) throws Exception;

	/**
	 * 生成推流地址及播放地址，刷新编辑页面。<br/>
	 * @param  record 直播信息
	 * @return LiveInfoVO 直播信息
	 * @throws Exception
	 */
	LiveInfoVO getPullAddress(LiveInfoVO record) throws Exception;
	/**
	 * 创建聊天室获取聊天室ID。<br/>
	 * 解散聊天室返回成功信息。
	 * @param  record 直播信息
	 * @return String 开启聊天室返回聊天室ID，解散聊天室返回成功信息。
	 * @throws Exception 
	 */
	String createOrDestroyRoom(LiveInfoVO record) throws Exception;

	/**
	 * 发布直播信息。<br/>
	 * @param  请求实例
	 * @return 返回页面名称
	 */
	LiveInfoVO publish(HttpServletRequest request);
}

