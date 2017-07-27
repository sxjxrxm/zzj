/**
 * Project Name:zzj-web
 * File Name:SlideService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.SlideShowInfo;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.SlideResultVO;
import com.zzj.db.model.SlideVO;

/**
 * <p><strong>类名: </strong></p>SlideService <br/>
 * <p><strong>功能说明: </strong></p>轮播管理操作模块相关业务逻辑处理实现类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:58:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface SlideService {
	/**
	 * 根据条件查询Slide_Show_Info表中相应记录<br/>
	 * @return List<SlideVO> Slide_Show_Info表记录集
	 */
	List<SlideVO> selectAllSlides();
	/**
	 * 保存排序：根据条件更新Slide_Show_Info表中相应记录<br/>
	 * @param  sortNo 表示顺
	 * @param  slideCd 轮播编码
	 * @param  updateId 更新者Id
	 * @return Integer  受更新影响条目数
	 */
	Integer saveSortNo(String sortNo, String topicCd, String busiType);
	/**
	 * 删除轮播：根据主键修改Slide_Show_Info表中相应记录的deleteFlag<br/>
	 * @param  slideCd 轮播编码
	 * @param  updateId 更新者Id
	 * @return Integer  受更新影响条目数
	 */
	Integer deleteSlides(String slideCd, String updateId);
	/**
	 * 轮播编辑查询：根据busi到不同表查询对应记录<br/>
	 * @param  topic 主题编码
	 * @param  techFields 领域编码
	 * @param  busi 业务编码
	 * @param  pageNumber 页码
	 * @return List 查询结果集
	 */
	PageResult<SlideResultVO> selectBusis(String topic, String[] techField, String busis, String pageNumber);
	/**
	 * 查询系统配置表中全部机能<br/>
	 * @return List 查询结果集
	 */
	List<MstCodeInfo> searchBusis();
	/**
	 * 添加轮播记录<br/>
	 * @param  topicCd 主题编码
	 * @param  topicName 主题名称
	 * @param  busiType 业务类别编码
	 * @param  bigIcon 图片地址
	 * @param  updateId 更新者Id
	 * @return boolean 添加是否成功结果
	 */
	boolean saveSlides(String topicCd, String topicName, String busiType, String bigIcon, String updateId);
	/**
	 * 根据逐渐查询单条记录<br/>
	 * @param updateId 
	 * @return SlideShowInfo 查询结果
	 * @throws Exception
	 */
	SlideShowInfo sreachSlide(String slideCd) throws Exception;
	/**
	 * 设置信息<br/>
	 * @param request http请求实例
	 * @throws Exception 
	 */
	void setParamter(HttpServletRequest request) throws Exception;
	/**
	 * 设置信息<br/>
	 * @param request http请求实例
	 * @param updateId 更新着ID
	 * @return SlideShowInfo 信息
	 * @throws Exception 
	 */
	SlideShowInfo setSlideInfo(HttpServletRequest request, String updateId) throws Exception;
	/**
	 * 编辑轮播记录：根据isZzj进行添加或更新轮播记录<br/>
	 * @param isAdd 
	 * @param slideCd
	 * @param slideShowInfo
	 * @return Integer 添加是否成功结果
	 */
	Integer editSlide(String isAdd, String slideCd, SlideShowInfo slideShowInfo);
	
}

