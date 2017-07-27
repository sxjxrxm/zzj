/**
 * Project Name:zzj-db
 * File Name:NewsInfoEditVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.NewsInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>NewsInfoEditVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是用于封装知识编辑/添加页面显示数据. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月1日上午11:45:44 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class NewsInfoEditVO extends NewsInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8925166237915952376L;

	/**
	 * 领域编码
	 */
	private List<TopicFieldInfoKey> fieldCd;
	
	/**
	 * 领域编码（不属于该专家的领域）
	 */
	private List<TopicFieldInfoKey> otherFieldCd;
	
	/**
	 * 推荐，置顶信息
	 */
	private String recommendKbn;
	
	/**
	 * 推荐语
	 */
	private String recommendMsg;

    /**
    * 详情图片地址URL
    */
    private String bigIconUrl;

    /**
    * 列表图片地址URL
    */
    private String littleIconUrl;
    
    /**
     * pdf地址URL
     */
    private String newsAddressUrl;
	
    /**
     * 专家作者1姓名
     */
     private String sourceOwnerName;

     /**
      * 专家作者2姓名
      */
     private String expertCd2Name;

     /**
      * 专家作者3姓名
      */
     private String expertCd3Name;

     /**
      * 专家作者4姓名
      */
     private String expertCd4Name;

     /**
      * 专家作者5姓名
      */
     private String expertCd5Name;
    
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
	 * 返回otherFieldCd的值
	 * @return List<TopicFieldInfoKey> otherFieldCd的值
	 */
	public List<TopicFieldInfoKey> getOtherFieldCd() {
		return otherFieldCd;
	}

	/**
	 * 设置otherFieldCd的值
	 * @param  otherFieldCd otherFieldCd的值
	 */
	public void setOtherFieldCd(List<TopicFieldInfoKey> otherFieldCd) {
		this.otherFieldCd = otherFieldCd;
	}

	/**
	 * 返回recommendKbn的值
	 * @return String recommendKbn的值
	 */
	public String getRecommendKbn() {
		return recommendKbn;
	}

	/**
	 * 设置recommendKbn的值
	 * @param  recommendKbn recommendKbn的值
	 */
	public void setRecommendKbn(String recommendKbn) {
		this.recommendKbn = recommendKbn;
	}

	/**
	 * 返回recommendMsg的值
	 * @return String recommendMsg的值
	 */
	public String getRecommendMsg() {
		return recommendMsg;
	}

	/**
	 * 设置recommendMsg的值
	 * @param  recommendMsg recommendMsg的值
	 */
	public void setRecommendMsg(String recommendMsg) {
		this.recommendMsg = recommendMsg;
	}

	/**
	 * 返回bigIconUrl的值
	 * @return String bigIconUrl的值
	 */
	public String getBigIconUrl() {
		return bigIconUrl;
	}

	/**
	 * 设置bigIconUrl的值
	 * @param  bigIconUrl bigIconUrl的值
	 */
	public void setBigIconUrl(String bigIconUrl) {
		this.bigIconUrl = bigIconUrl;
	}

	/**
	 * 返回littleIconUrl的值
	 * @return String littleIconUrl的值
	 */
	public String getLittleIconUrl() {
		return littleIconUrl;
	}

	/**
	 * 设置littleIconUrl的值
	 * @param  littleIconUrl littleIconUrl的值
	 */
	public void setLittleIconUrl(String littleIconUrl) {
		this.littleIconUrl = littleIconUrl;
	}

	/**
	 * 返回newsAddressUrl的值
	 * @return String newsAddressUrl的值
	 */
	public String getNewsAddressUrl() {
		return newsAddressUrl;
	}

	/**
	 * 设置newsAddressUrl的值
	 * @param  newsAddressUrl newsAddressUrl的值
	 */
	public void setNewsAddressUrl(String newsAddressUrl) {
		this.newsAddressUrl = newsAddressUrl;
	}

	/**
	 * 返回sourceOwnerName的值
	 * @return String sourceOwnerName的值
	 */
	public String getSourceOwnerName() {
		return sourceOwnerName;
	}

	/**
	 * 设置sourceOwnerName的值
	 * @param  sourceOwnerName sourceOwnerName的值
	 */
	public void setSourceOwnerName(String sourceOwnerName) {
		this.sourceOwnerName = sourceOwnerName;
	}

	/**
	 * 返回expertCd2Name的值
	 * @return String expertCd2Name的值
	 */
	public String getExpertCd2Name() {
		return expertCd2Name;
	}

	/**
	 * 设置expertCd2Name的值
	 * @param  expertCd2Name expertCd2Name的值
	 */
	public void setExpertCd2Name(String expertCd2Name) {
		this.expertCd2Name = expertCd2Name;
	}

	/**
	 * 返回expertCd3Name的值
	 * @return String expertCd3Name的值
	 */
	public String getExpertCd3Name() {
		return expertCd3Name;
	}

	/**
	 * 设置expertCd3Name的值
	 * @param  expertCd3Name expertCd3Name的值
	 */
	public void setExpertCd3Name(String expertCd3Name) {
		this.expertCd3Name = expertCd3Name;
	}

	/**
	 * 返回expertCd4Name的值
	 * @return String expertCd4Name的值
	 */
	public String getExpertCd4Name() {
		return expertCd4Name;
	}

	/**
	 * 设置expertCd4Name的值
	 * @param  expertCd4Name expertCd4Name的值
	 */
	public void setExpertCd4Name(String expertCd4Name) {
		this.expertCd4Name = expertCd4Name;
	}

	/**
	 * 返回expertCd5Name的值
	 * @return String expertCd5Name的值
	 */
	public String getExpertCd5Name() {
		return expertCd5Name;
	}

	/**
	 * 设置expertCd5Name的值
	 * @param  expertCd5Name expertCd5Name的值
	 */
	public void setExpertCd5Name(String expertCd5Name) {
		this.expertCd5Name = expertCd5Name;
	}
	
}

