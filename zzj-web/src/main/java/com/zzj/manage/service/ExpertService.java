/**
Master * Project Name:zzj-web
 * File Name:ExpertService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartRequest;

import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.model.ExpertInfoEditVO;
import com.zzj.db.model.ExpertInfoListVO;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>ExpertService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装专家模块，专家列表显示，专家编辑. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日上午10:54:07 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface ExpertService {
	
	/**
	 * Expert表保存处理<br/>
	 * @param  vo ExpertInfo数据
	 * @param  request 请求实例
	 * @param  isAdd 区分编辑与添加操作，1：添加，0：编辑
	 * @return boolean 是否成功
	 * @throws Exception
	 */
	public boolean saveExpert(ExpertInfoEditVO vo, HttpServletRequest request, Integer isAdd) throws Exception ;

	/**
	 * Expert表查询处理<br/>
	 * @param  request 请求实例
	 * @return PageResult<ExpertInfoListVO> 分页处理结果
	 */
	public PageResult<ExpertInfoListVO> getResultList(HttpServletRequest request) throws Exception;

	/**
	 * 根据主键查询Expert表记录<br/>
	 * @param  request 请求实例
	 * @param  expertId 主键
	 * @return ExpertInfoEditVO 专家信息
	 * @throws Exception
	 */
	public ExpertInfoEditVO selectByPrimaryKey(HttpServletRequest request, String expertId) throws Exception ;

	/**
	 * 根据主键删除Expert表记录<br/>
	 * @param  ids 专家id、当前用户id
	 */
	public void deleteExpertById(String[] ids);

	/**
	 * 构造专家页面信息<br/>
	 * @param  无
	 * @return ExpertInfoEditVO 专家信息
	 */
	public ExpertInfoEditVO initPageResource();

	/**
	 * 专家编辑页面城市联动菜单异步请求<br/>
	 * @param  request 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	public List<MstAreaInfo> getCityC(HttpServletRequest request);

	/**
	 * 
	 * 专家编辑页面专家职称异步请求<br/>
	 * @return String 返回专家职称信息，以空格隔开的字符串
	 */
	public String getRankName();

	/**
	 * 专家编辑页面专家头像异步请求。<br/>
	 * @param  file 文件对象
	 * @param  req 请求实例
	 * @param  itemName 图片空间区分
	 * @return Map<String, String> 返回专家头像地址
	 */
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req, String itemName);

	/**
	 * 专家编辑页面专家头像异步删除。<br/>
	 * @param  path 文件路径
	 * @return boolean 返回删除是否成功
	 * @throws Exception
	 */
	public boolean delPic(String path) throws Exception;

	/**
	 * 刷新画面。
	 * @param ExpertInfoEditVO expertInfo 专家信息
	 * @param HttpServletRequest request 请求实例
	 */
	public void load(ExpertInfoEditVO expertInfo, HttpServletRequest request) throws Exception;

	/**
	 * 取得全部专家记录（根据专家一览页面的检索条件）。
	 * @param HttpServletRequest request 请求实例
	 * @return List<ExpertInfoListVO> 专家列表
	 */
	public List<ExpertInfoListVO> searchAll(HttpServletRequest request) throws Exception;

	/**
	 * 获得导出内容。
	 * @param List<ExpertInfoListVO> resultList 专家一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	public List<ArrayList<String>> getOutputContent(List<ExpertInfoListVO> resultList, List<MstCodeInfo> mstCodeInfos);

	/**
	 * 知识编辑页面专家姓名异步请求。<br/>
	 * @return String 返回专家姓名信息，以4个空格隔开的字符串
	 */
	public String getExpertName();

	/**
	 * 根据专家id和分页信息查询专家文章列表。<br/>
	 * @param request 请求实例
	 * @param expertId 专家id
	 * @return Map<String,Object> 用于数据库查询
	 */
	public PageResult<NewsInfo> findNewsByExpertIdAndPage (HttpServletRequest request, String expertId) throws Exception;

	/**
	 * 进行专家文章删除。<br/>
	 * @param  request http请求实例
	 * @param  newsCd 知识id
	 * @return 删除文章数量
	 */
	public int delExpertArticle(HttpServletRequest request, String newsCd);
}

