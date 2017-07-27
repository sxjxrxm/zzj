/**
 * Project Name:zzj-db
 * File Name:CourseMsgVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import com.zzj.db.dto.CourseInfo;

/**
 * <p><strong>类名: </strong></p>CourseMsgVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装在线课堂单条记录里的单类型信息. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月21日上午10:35:16 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CourseMsgVO extends CourseInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4887290906023884534L;
	
	// ********************共通*****************************

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 消息表主键
	 */
	private Long mid;

	/**
	 * 消息顺序
	 */
	private Integer msgSeq;
	
	/**
	 * 消息类型0:text,1:image,2:face,3:sound
	 */
	private Integer msgType;

	// ********************msg_face*****************************

	/**
	 * 表情索引，用户自定义
	 */
	private Integer faceIndex;

	/**
	 * 额外数据
	 */
	private String faceData;

	// ********************msg_text*****************************
	
	/**
	 * 文本消息
	 */
	private String text;
	
	// ********************msg_sound*****************************

	/**
	 * 语音序列号/图片序列号
	 */
	private String uuid;

	/**
	 * 语音数据大小
	 */
	private Integer size;

	/**
	 * 语音时长，单位:秒
	 */
	private Integer second;

	/**
	 * 语音地址
	 */
	private String address;

	// ********************msg_image*****************************

	/**
	 * 图片格式。BMP=1,JPG=2,GIF=3,其他=0
	 */
	private Integer imageformat;

	/**
	 * 原图大小
	 */
	private Integer originalSize;

	/**
	 * 原图宽度
	 */
	private Integer originalWidth;

	/**
	 * 原图高度
	 */
	private Integer originalHeight;

	/**
	 * 原图下载地址
	 */
	private String originalUrl;

	/**
	 * 大图大小
	 */
	private Integer bigSize;

	/**
	 * 大图宽度
	 */
	private Integer bigWidth;

	/**
	 * 大图高度
	 */
	private Integer bigHeight;

	/**
	 * 大图下载地址
	 */
	private String bigUrl;

	/**
	 * 小图大小
	 */
	private Integer smallSize;

	/**
	 * 小图宽度
	 */
	private Integer smallWidth;

	/**
	 * 小图高度
	 */
	private Integer smallHeight;

	/**
	 * 小图下载地址
	 */
	private String smallUrl;

	/**
	 * 返回id的值
	 * @return Long id的值
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置id的值
	 * @param  id id的值
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回mid的值
	 * @return Long mid的值
	 */
	public Long getMid() {
		return mid;
	}

	/**
	 * 设置mid的值
	 * @param  mid mid的值
	 */
	public void setMid(Long mid) {
		this.mid = mid;
	}

	/**
	 * 返回msgSeq的值
	 * @return Integer msgSeq的值
	 */
	public Integer getMsgSeq() {
		return msgSeq;
	}

	/**
	 * 设置msgSeq的值
	 * @param  msgSeq msgSeq的值
	 */
	public void setMsgSeq(Integer msgSeq) {
		this.msgSeq = msgSeq;
	}

	/**
	 * 返回msgType的值
	 * @return Integer msgType的值
	 */
	public Integer getMsgType() {
		return msgType;
	}

	/**
	 * 设置msgType的值
	 * @param  msgType msgType的值
	 */
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	/**
	 * 返回faceIndex的值
	 * @return Integer faceIndex的值
	 */
	public Integer getFaceIndex() {
		return faceIndex;
	}

	/**
	 * 设置faceIndex的值
	 * @param  faceIndex faceIndex的值
	 */
	public void setFaceIndex(Integer faceIndex) {
		this.faceIndex = faceIndex;
	}

	/**
	 * 返回faceData的值
	 * @return String faceData的值
	 */
	public String getFaceData() {
		return faceData;
	}

	/**
	 * 设置faceData的值
	 * @param  faceData faceData的值
	 */
	public void setFaceData(String faceData) {
		this.faceData = faceData;
	}

	/**
	 * 返回text的值
	 * @return String text的值
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置text的值
	 * @param  text text的值
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 返回uuid的值
	 * @return String uuid的值
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 设置uuid的值
	 * @param  uuid uuid的值
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 返回size的值
	 * @return Integer size的值
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * 设置size的值
	 * @param  size size的值
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * 返回second的值
	 * @return Integer second的值
	 */
	public Integer getSecond() {
		return second;
	}

	/**
	 * 设置second的值
	 * @param  second second的值
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}

	/**
	 * 返回address的值
	 * @return String address的值
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置address的值
	 * @param  address address的值
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 返回imageformat的值
	 * @return Integer imageformat的值
	 */
	public Integer getImageformat() {
		return imageformat;
	}

	/**
	 * 设置imageformat的值
	 * @param  imageformat imageformat的值
	 */
	public void setImageformat(Integer imageformat) {
		this.imageformat = imageformat;
	}

	/**
	 * 返回originalSize的值
	 * @return Integer originalSize的值
	 */
	public Integer getOriginalSize() {
		return originalSize;
	}

	/**
	 * 设置originalSize的值
	 * @param  originalSize originalSize的值
	 */
	public void setOriginalSize(Integer originalSize) {
		this.originalSize = originalSize;
	}

	/**
	 * 返回originalWidth的值
	 * @return Integer originalWidth的值
	 */
	public Integer getOriginalWidth() {
		return originalWidth;
	}

	/**
	 * 设置originalWidth的值
	 * @param  originalWidth originalWidth的值
	 */
	public void setOriginalWidth(Integer originalWidth) {
		this.originalWidth = originalWidth;
	}

	/**
	 * 返回originalHeight的值
	 * @return Integer originalHeight的值
	 */
	public Integer getOriginalHeight() {
		return originalHeight;
	}

	/**
	 * 设置originalHeight的值
	 * @param  originalHeight originalHeight的值
	 */
	public void setOriginalHeight(Integer originalHeight) {
		this.originalHeight = originalHeight;
	}

	/**
	 * 返回originalUrl的值
	 * @return String originalUrl的值
	 */
	public String getOriginalUrl() {
		return originalUrl;
	}

	/**
	 * 设置originalUrl的值
	 * @param  originalUrl originalUrl的值
	 */
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	/**
	 * 返回bigSize的值
	 * @return Integer bigSize的值
	 */
	public Integer getBigSize() {
		return bigSize;
	}

	/**
	 * 设置bigSize的值
	 * @param  bigSize bigSize的值
	 */
	public void setBigSize(Integer bigSize) {
		this.bigSize = bigSize;
	}

	/**
	 * 返回bigWidth的值
	 * @return Integer bigWidth的值
	 */
	public Integer getBigWidth() {
		return bigWidth;
	}

	/**
	 * 设置bigWidth的值
	 * @param  bigWidth bigWidth的值
	 */
	public void setBigWidth(Integer bigWidth) {
		this.bigWidth = bigWidth;
	}

	/**
	 * 返回bigHeight的值
	 * @return Integer bigHeight的值
	 */
	public Integer getBigHeight() {
		return bigHeight;
	}

	/**
	 * 设置bigHeight的值
	 * @param  bigHeight bigHeight的值
	 */
	public void setBigHeight(Integer bigHeight) {
		this.bigHeight = bigHeight;
	}

	/**
	 * 返回bigUrl的值
	 * @return String bigUrl的值
	 */
	public String getBigUrl() {
		return bigUrl;
	}

	/**
	 * 设置bigUrl的值
	 * @param  bigUrl bigUrl的值
	 */
	public void setBigUrl(String bigUrl) {
		this.bigUrl = bigUrl;
	}

	/**
	 * 返回smallSize的值
	 * @return Integer smallSize的值
	 */
	public Integer getSmallSize() {
		return smallSize;
	}

	/**
	 * 设置smallSize的值
	 * @param  smallSize smallSize的值
	 */
	public void setSmallSize(Integer smallSize) {
		this.smallSize = smallSize;
	}

	/**
	 * 返回smallWidth的值
	 * @return Integer smallWidth的值
	 */
	public Integer getSmallWidth() {
		return smallWidth;
	}

	/**
	 * 设置smallWidth的值
	 * @param  smallWidth smallWidth的值
	 */
	public void setSmallWidth(Integer smallWidth) {
		this.smallWidth = smallWidth;
	}

	/**
	 * 返回smallHeight的值
	 * @return Integer smallHeight的值
	 */
	public Integer getSmallHeight() {
		return smallHeight;
	}

	/**
	 * 设置smallHeight的值
	 * @param  smallHeight smallHeight的值
	 */
	public void setSmallHeight(Integer smallHeight) {
		this.smallHeight = smallHeight;
	}

	/**
	 * 返回smallUrl的值
	 * @return String smallUrl的值
	 */
	public String getSmallUrl() {
		return smallUrl;
	}

	/**
	 * 设置smallUrl的值
	 * @param  smallUrl smallUrl的值
	 */
	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

}

