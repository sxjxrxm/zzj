/**
 * Project Name:zzj-web
 * File Name:InformationService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.model.NewsInfoEditVO;
import com.zzj.db.model.NewsInfoListVO;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>InformationService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装知识模块，知识列表显示，知识编辑. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日下午5:18:36 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface InformationService {

	/**
	 * news_info表查询处理<br/>
	 * @param  request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @return PageResult<NewsInfoListVO> 信息列表
	 */
	PageResult<NewsInfoListVO> getResultList(HttpServletRequest request, Date startDate, Date endDate) throws Exception;

	/**
	 * 根据主键删除news_info表记录<br/>
	 * @param  ids newsCd、当前用户id
	 */
	void deleteNewsById(String[] ids);

	/**
	 * 根据主键查询NewsInfo表记录<br/>
	 * @param  request 请求实例
	 * @param  newsCd 主键
	 * @return NewsInfoEditVO 信息详细
	 * @throws Exception
	 */
	NewsInfoEditVO selectByPrimaryKey(HttpServletRequest request, String newsCd) throws Exception ;

	/**
	 * 构造知识页面信息<br/>
	 * @param  无
	 * @return NewsInfoEditVO 信息详细
	 */
	NewsInfoEditVO initPageResource();

	/**
	 * NewsInfo表保存处理<br/>
	 * @param  newsInfo 数据
	 * @param  request 请求实例
	 * @param  isAdd 区分编辑与添加操作，1：添加，0：编辑
	 * @throws Exception
	 */
	boolean saveNews(NewsInfoEditVO newsInfo, HttpServletRequest request, Integer isAdd) throws Exception;

	/**
	 * 知识编辑页面图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	Map<String, String> queryPic(MultipartRequest file, HttpServletRequest request);

	/**
	 * 知识编辑页面文件异步删除<br/>
	 * @param  name 文件名
	 * @param  path 文件路径
	 * @return boolean 返回删除是否成功
	 * @throws Exception
	 */
	boolean delFile(String name, String path) throws Exception;

	/**
	 * 知识编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @return String 返回图片保存的url
	 * @throws Exception
	 */
	Map<String, String> textEditorImg(MultipartRequest file) throws Exception;

	/**
	 * 知识编辑页面pdf异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	Map<String, String> queryPdf(MultipartRequest file, HttpServletRequest req);

	/**
	 * 取得全部知识记录（根据知识一览页面的检索条件）
	 * @param HttpServletRequest request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @return List<NewsInfoListVO> 知识列表
	 */
	List<NewsInfoListVO> searchAll(HttpServletRequest request, Date startDate, Date endDate) throws Exception;

	/**
	 * 获得导出内容
	 * @param List<NewsInfoListVO> resultList 知识一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getOutputContent(List<NewsInfoListVO> resultList, List<MstCodeInfo> mstCodeInfos);

}

