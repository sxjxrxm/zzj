/**
 * Project Name:zzj-web
 * File Name:ExpertInfoEditVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>ExpertInfoEditVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是用于封装专家编辑/添加页面显示数据. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午2:42:15 <br/>
 * @author 任晓茂
 * @version 1.0
 * @since JDK 1.8
 */
public class ExpertInfoEditVO extends ExpertInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4351245966626895305L;

	/**
	 * 领域编码
	 */
	private List<TopicFieldInfoKey> fieldCd;
	
	/**
	 * 领域编码（不属于该专家的领域）
	 */
	private List<TopicFieldInfoKey> otherFieldCd;
	
	/**
	 * 所有省级信息
	 */
	private List<MstAreaInfo> areaInfosCityP;
	
	/**
	 * 专家所在省对应的所有市级信息
	 */
	private List<MstAreaInfo> areaInfosCityC;
	
	/**
	 * 推荐，置顶信息
	 */
	private String recommendKbn;
	
	/**
	 * 推荐语
	 */
	private String recommendMsg;
	
	/**
	 * 拒绝消息类型，1：系统配置表code 2：自定义拒绝理由
	 */
	private Integer refuseMemoType;
	
	/**
	 * 头像地址
	 */
	private String avatorAddress;
	
	/**
	 * 头像请求地址
	 */
	private String avatorAddressUrl;
	
	/**
	 * 专家背景图片地址
	 */
	private String backgroundAdress;
	
	/**
	 * 专家背景图片URL
	 */
	private String backgroundAdressUrl;


	/**
	 * 返回fieldCd的值
	 * 
	 * @return List<TopicFieldInfoKey> fieldCd的值
	 */
	public List<TopicFieldInfoKey> getFieldCd() {
		return fieldCd;
	}

	/**
	 * 设置fieldCd的值
	 * 
	 * @param fieldCd
	 *            fieldCd的值
	 */
	public void setFieldCd(List<TopicFieldInfoKey> fieldCd) {
		this.fieldCd = fieldCd;
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
	 * 返回areaInfosCityP的值
	 * @return List<MstAreaInfo> areaInfosCityP的值
	 */
	public List<MstAreaInfo> getAreaInfosCityP() {
		return areaInfosCityP;
	}

	/**
	 * 设置areaInfosCityP的值
	 * @param  areaInfosCityP areaInfosCityP的值
	 */
	public void setAreaInfosCityP(List<MstAreaInfo> areaInfosCityP) {
		this.areaInfosCityP = areaInfosCityP;
	}

	/**
	 * 返回areaInfosCityC的值
	 * @return List<MstAreaInfo> areaInfosCityC的值
	 */
	public List<MstAreaInfo> getAreaInfosCityC() {
		return areaInfosCityC;
	}

	/**
	 * 设置areaInfosCityC的值
	 * @param  areaInfosCityC areaInfosCityC的值
	 */
	public void setAreaInfosCityC(List<MstAreaInfo> areaInfosCityC) {
		this.areaInfosCityC = areaInfosCityC;
	}

	/**
	 * 返回avatorAddress的值
	 * @return String avatorAddress的值
	 */
	public String getAvatorAddress() {
		return avatorAddress;
	}

	/**
	 * 设置avatorAddress的值
	 * @param  avatorAddress avatorAddress的值
	 */
	public void setAvatorAddress(String avatorAddress) {
		this.avatorAddress = avatorAddress;
	}

	/**
	 * 返回avatorAddressUrl的值
	 * @return String avatorAddressUrl的值
	 */
	public String getAvatorAddressUrl() {
		return avatorAddressUrl;
	}

	/**
	 * 设置avatorAddressUrl的值
	 * @param  avatorAddressUrl avatorAddressUrl的值
	 */
	public void setAvatorAddressUrl(String avatorAddressUrl) {
		this.avatorAddressUrl = avatorAddressUrl;
	}

	/**
	 * 返回backgroundAdress的值
	 * @return String backgroundAdress的值
	 */
	public String getBackgroundAdress() {
		return backgroundAdress;
	}

	/**
	 * 设置backgroundAdress的值
	 * @param  backgroundAdress backgroundAdress的值
	 */
	public void setBackgroundAdress(String backgroundAdress) {
		this.backgroundAdress = backgroundAdress;
	}

	/**
	 * 返回backgroundAdressUrl的值
	 * @return String backgroundAdressUrl的值
	 */
	public String getBackgroundAdressUrl() {
		return backgroundAdressUrl;
	}

	/**
	 * 设置backgroundAdressUrl的值
	 * @param  backgroundAdressUrl backgroundAdressUrl的值
	 */
	public void setBackgroundAdressUrl(String backgroundAdressUrl) {
		this.backgroundAdressUrl = backgroundAdressUrl;
	}

	/**
	 * 返回refuseMemoType的值
	 * @return Integer refuseMemoType的值
	 */
	public Integer getRefuseMemoType() {
		return refuseMemoType;
	}

	/**
	 * 设置refuseMemoType的值
	 * @param  refuseMemoType refuseMemoType的值
	 */
	public void setRefuseMemoType(Integer refuseMemoType) {
		this.refuseMemoType = refuseMemoType;
	}


}
