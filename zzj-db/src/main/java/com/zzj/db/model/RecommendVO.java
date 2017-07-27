/**
 * Project Name:zzj-db
 * File Name:RecommendVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;


import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>RecommendVO <br/>
 * <p><strong>功能说明: </strong></p>推荐置顶管理页面用VO<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月6日下午5:06:35 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class RecommendVO extends RecommendInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8229236258141571838L;
	/**
	 * 查询结果主题
	 */
	private String RecommendInfoTopic;
	/**
	 * 查询结果费用:0免费1付费
	 */
	private int RecommendInfoPayment;
	/**
	 * 查询结果领域
	 */
	private List<TopicFieldInfoKey> RecommendInfoResultfields;
	/**
	 * 查询结果更新人姓名
	 */
	private String ResultUpdateName;
	/**
	 * 查询结果更新时间（String）
	 */
	private String ResultUpdateTime;
	/**
	 * 返回recommendInfoTopic的值
	 * @return String recommendInfoTopic的值
	 */
	public String getRecommendInfoTopic() {
		return RecommendInfoTopic;
	}
	/**
	 * 设置recommendInfoTopic的值
	 * @param  recommendInfoTopic recommendInfoTopic的值
	 */
	public void setRecommendInfoTopic(String recommendInfoTopic) {
		RecommendInfoTopic = recommendInfoTopic;
	}
	/**
	 * 返回recommendInfoPayment的值
	 * @return int recommendInfoPayment的值
	 */
	public int getRecommendInfoPayment() {
		return RecommendInfoPayment;
	}
	/**
	 * 设置recommendInfoPayment的值
	 * @param  recommendInfoPayment recommendInfoPayment的值
	 */
	public void setRecommendInfoPayment(int recommendInfoPayment) {
		RecommendInfoPayment = recommendInfoPayment;
	}
	/**
	 * 返回recommendInfoResultfields的值
	 * @return List<TopicFieldInfoKey> recommendInfoResultfields的值
	 */
	public List<TopicFieldInfoKey> getRecommendInfoResultfields() {
		return RecommendInfoResultfields;
	}
	/**
	 * 设置recommendInfoResultfields的值
	 * @param  recommendInfoResultfields recommendInfoResultfields的值
	 */
	public void setRecommendInfoResultfields(List<TopicFieldInfoKey> recommendInfoResultfields) {
		RecommendInfoResultfields = recommendInfoResultfields;
	}
	/**
	 * 返回serialversionuid的值
	 * @return long serialversionuid的值
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 返回resultUpdateName的值
	 * @return String resultUpdateName的值
	 */
	public String getResultUpdateName() {
		return ResultUpdateName;
	}
	/**
	 * 设置resultUpdateName的值
	 * @param  resultUpdateName resultUpdateName的值
	 */
	public void setResultUpdateName(String resultUpdateName) {
		ResultUpdateName = resultUpdateName;
	}
	/**
	 * 返回resultUpdateTime的值
	 * @return String resultUpdateTime的值
	 */
	public String getResultUpdateTime() {
		return ResultUpdateTime;
	}
	/**
	 * 设置resultUpdateTime的值
	 * @param  resultUpdateTime resultUpdateTime的值
	 */
	public void setResultUpdateTime(String resultUpdateTime) {
		ResultUpdateTime = resultUpdateTime;
	}
	

}

