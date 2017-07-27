/**
 * Project Name:zzj-web
 * File Name:CommonService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartRequest;

/**
 * <p><strong>类名: </strong></p>CommonService <br/>
 * <p><strong>功能说明: </strong></p>共通业务处理接口<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午1:26:04 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface CommonService {

	/**
	 * 编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @param uploadType 上传类型  
	 * content: 内容详情（包括简介，列表图片，详情图片等）；
	 * avatar: 头像；
	 * file: 文件；
	 * other: 其他功能图片（如客服图片等）<br/>
	 * @param moduleCode 功能模块编码(业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告)
	 * @return String 文件保存相对路径 "html/02/BD/90999999/或z:/html/03/img/2016/12/18/7e215a9b2e4046e397f4d36c603d7a5d.jpg)"
	 * @throws Exception
	 */
	Map<String, String> textEditorImg(MultipartRequest file, String uploadType, String moduleCode) throws Exception;

	/**
	 * 获取用户签名
	 * @param  userId 用户名
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public String getUserSig(String userId) throws Exception;
	
	/**
	 * 判断签名是否过期,过期重新生成并插入数据库;没过期从数据库中取
	 * @param  userId 用户名
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public String checkUserSig(String userId) throws Exception;
	
	/**
	 * 独立模式腾讯云同步
	 * @param identifier 用户id(必填)
	 * @param nick 昵称(选填)
	 * @param faceUrl 头像(选填)
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public void accountImport(String identifier, String nick, String faceUrl) throws Exception;
	
	/**
	 * 设置用户资料
	 * @param identifier 用户id(必填)
	 * @param nick 昵称(选填)
	 * @param faceUrl 头像(选填)
	 * @param selfSignature 定义字段
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public void SetAccountInfo(String identifier, String nick, String faceUrl, String selfSignature) throws Exception;
	/**
	 * 设置群组中成员名片
	 * @param groupId 组id
	 * @param memberId 用户id
	 * @param nameCard 群名片
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public void SetGroupInfo(String groupId, String memberId, String nameCard) throws Exception;
//	/**
//	 * 查看群组中成员的信息
//	 * @param groupId 组id
//	 * @param memberId 成员id
//	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
//	 * @throws Exception
//	 */
//	public void GetGroupInfo(String groupId, String memberId) throws Exception;
	
	/**
	 * 修改群组在腾讯云的名称
	 * @param  roomId groupId
	 * @param  courseName 新的群组名称
	 * @return 无
	 * @throws Exception 
	 */
	public void changeGroupName(String roomId, String courseName) throws Exception;
	
	/**
	 * 根据id获得用户的用户名
	 * @param  JSONObject sendMsg
	 * @return Map<String, String>
	 * @throws Exception 
	 */
	public Map<String, String> getNicksByAccounts(JSONObject sendMsg) throws Exception;
}

