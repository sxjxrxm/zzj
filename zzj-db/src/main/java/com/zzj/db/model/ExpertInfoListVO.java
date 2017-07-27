/**
 * Project Name:zzj-web
 * File Name:ExpertInfoListVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.dto.UserHandleInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>ExpertInfoListVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是用于封装专家一览页面列表显示数据. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午2:42:15 <br/>
 * @author 任晓茂
 * @version 1.0
 * @since JDK 1.8
 */
public class ExpertInfoListVO extends ExpertInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3657906598445270543L;

	/**
	 * 推荐区分 1：推荐，2：置顶
	 */
	private List<RecommendInfoKey> recommendKbn;

	/**
	 * 领域编码
	 */
	private List<TopicFieldInfoKey> fieldCd;

	/**
	 * 文章数量
	 */
	private Integer articleCount;

	/**
	 * 待审核文章数量
	 */
	private Integer pendingReviewArticleCount;
	
	/**
	 * 用于前台页面显示的数据
	 * 封装了该专家对应的下载数、浏览数、点赞数、收藏数
	 */
	private UserHandleInfo userHandleInfo;
	
	/**
	 * 返回recommendKbn的值
	 * @return Integer recommendKbn的值
	 */
	public List<RecommendInfoKey> getRecommendKbn() {
		return recommendKbn;
	}

	/**
	 * 设置recommendKbn的值
	 * @param  recommendKbn recommendKbn的值
	 */
	public void setRecommendKbn(List<RecommendInfoKey> recommendKbn) {
		this.recommendKbn = recommendKbn;
	}

	/**
	 * 返回fieldCd的值
	 * @return List<TopicFieldInfoKey> fieldCd的值
	 */
	public List<TopicFieldInfoKey> getFieldCd() {
		return fieldCd;
	}

	/**
	 * 设置fieldCd的值
	 * @param  fieldCd fieldCd的值
	 */
	public void setFieldCd(List<TopicFieldInfoKey> fieldCd) {
		this.fieldCd = fieldCd;
	}

	/**
	 * 返回articleCount的值
	 * @return Integer articleCount的值
	 */
	public Integer getArticleCount() {
		return articleCount;
	}

	/**
	 * 设置articleCount的值
	 * @param  articleCount articleCount的值
	 */
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	/**
	 * 返回pendingReviewArticleCount的值
	 * @return Integer pendingReviewArticleCount的值
	 */
	public Integer getPendingReviewArticleCount() {
		return pendingReviewArticleCount;
	}

	/**
	 * 设置pendingReviewArticleCount的值
	 * @param  pendingReviewArticleCount pendingReviewArticleCount的值
	 */
	public void setPendingReviewArticleCount(Integer pendingReviewArticleCount) {
		this.pendingReviewArticleCount = pendingReviewArticleCount;
	}

	/**
	 * 返回userHandleInfo的值
	 * @return UserHandleInfo userHandleInfo的值
	 */
	public UserHandleInfo getUserHandleInfo() {
		return userHandleInfo;
	}

	/**
	 * 设置userHandleInfo的值
	 * @param  userHandleInfo userHandleInfo的值
	 */
	public void setUserHandleInfo(UserHandleInfo userHandleInfo) {
		this.userHandleInfo = userHandleInfo;
	}

}
