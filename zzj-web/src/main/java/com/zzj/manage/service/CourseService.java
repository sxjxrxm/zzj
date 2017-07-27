/**
 * Project Name:zzj-web
 * File Name:UserService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.CourseInfoVO;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>CourseService业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>CourseService业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface CourseService {
	/**
	 * 分页取得课堂记录
	 * @param Map<String, Object> 画面表示用课堂记录
	 * @return PageResult<CourseInfoVO> 课堂列表
	 */
	PageResult<CourseInfoVO> searchPagging(Map<String, Object> record);
	/**
	 * 取得全部课堂记录
	 * @param Map<String, Object> 画面表示用课堂记录
	 * @return List<CourseInfoVO> 课堂列表
	 */
	List<CourseInfoVO> searchAll(Map<String, Object> record);
	/**
	 * 根据主键删除记录
	 * @param CourseInfoVO record 更新信息
	 * @return int 更新结果条目数
	 */
	int delete(CourseInfoVO record);
	/**
	 * 关闭课堂。
	 * @param CourseInfoVO record 更新信息
	 * @return int 执行结果
	 * @throws Exception 
	 */
	int closeCourse(CourseInfoVO record) throws Exception;
	/**
	 * 获得导出内容
	 * @param List<CourseInfoVO> resultList 课堂一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getOutputContent(List<CourseInfoVO> resultList, List<MstCodeInfo> mstCodeInfos);
	/**
	 * 刷新画面
	 * @param HttpServletRequest request 请求实例
	 * @param String courseCd 课堂编码
	 * @throws Exception 
	 */
	void load(HttpServletRequest request, String courseCd) throws Exception;
	/**
	 * 根据主键查询表记录<br/>
	 * @param  courseCd 主键
	 * @return CourseInfoVO 课堂信息
	 * @throws Exception 
	 */
	CourseInfoVO selectByPrimaryKey(String courseCd) throws Exception;
	/**
	 * 根据主键更新记录
	 * @param  CourseInfoVO record 课堂信息
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	int save(CourseInfoVO record) throws Exception;
	/**
	 * 获得不属于该课堂的领域
	 * @param List<TopicFieldInfoKey> 属于该课堂的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该课堂的领域
	 */
	List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo);
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @return Map<String, Object> 用于数据库查询
	 * @throws Exception
	 */
	Map<String, Object> selectKeyMap(HttpServletRequest request, String edit) throws Exception;
	/**
	 * 图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req);
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request
	 *            请求实例
	 * @return CourseInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	CourseInfoVO getSaveCourseInfo(HttpServletRequest request, String pagging) throws Exception;
	/**
	 * 知识编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @return String 返回图片保存的url
	 * @throws Exception
	 */
	String textEditorImg(MultipartRequest file) throws Exception;
	/**
	 * 设置更新信息<br/> 
	 * @param request 请求实例
	 * @return CourseInfoVO
	 */
	CourseInfoVO setUpdateInfo(HttpServletRequest request, String courseCd);
	/**
	 * 刷新在线课堂页面
	 * @param HttpServletRequest request 请求实例
	 * @param String courseCd 课堂编码
	 * @throws Exception 
	 */
	void loadLiveCourse(HttpServletRequest request, String courseCd) throws Exception;
	
	/**
	 * 跳转到在线课堂页面。<br/>
	 * @param  请求实例
	 * @param  响应实例
	 * @param  courseCd 课堂编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	void showMoreMsg(HttpServletRequest request, HttpServletResponse response, String courseCd) throws Exception;
	
//	/**
//	 * 在线课堂发送文本消息。<br/>
//	 * @param  请求实例
//	 * @param  courseCd 课堂编码
//	 * @return 发送结果及消息
//	 * @throws Exception
//	 */
//	Map<String, Object> sendMsg(HttpServletRequest request, String courseCd) throws Exception;
}

