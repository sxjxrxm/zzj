/**
 * Project Name:zzj-db
 * File Name:ExpertArticleCountVO.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>ExpertArticleCountVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是用于封装专家一览页面列表显示数据. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月21日下午2:56:09 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ExpertArticleCountVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4936005556257031677L;
	
	/**
	 * 文章数量
	 */
	private Integer articleCount;

	/**
	 * 待审核文章数量
	 */
	private Integer pendingReviewArticleCount;

	/**
	 * 返回articleCount的值
	 * @return Integer articleCount的值
	 */
	public Integer getArticleCount() {
		return articleCount;
	}

	/**
	 * 添加初始值，前台页面显示方便
	 * 构造ExpertArticleCountVO实例。<br/>
	 * @param  变量名，做什么用的
	 * @throws Exception
	 */
	public ExpertArticleCountVO() {
		this.articleCount = 0;
		this.pendingReviewArticleCount = 0;
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

}

