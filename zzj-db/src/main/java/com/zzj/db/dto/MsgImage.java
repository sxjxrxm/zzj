/**
 * Project Name:zzj-db
 * File Name:MsgImage.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
/**
 * <p><strong>类名: </strong></p>MsgImage表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MsgImage表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MsgImage implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6122826847354988705L;

	/**
	 * 主键
	 */
	private Long id;

    /**
    * 消息表主键
    */
    private Long mid;

    /**
    * 图片序列号
    */
    private String uuid;

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
    * 消息顺序
    */
    private Integer msgSeq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getImageformat() {
        return imageformat;
    }

    public void setImageformat(Integer imageformat) {
        this.imageformat = imageformat;
    }

    public Integer getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(Integer originalSize) {
        this.originalSize = originalSize;
    }

    public Integer getOriginalWidth() {
        return originalWidth;
    }

    public void setOriginalWidth(Integer originalWidth) {
        this.originalWidth = originalWidth;
    }

    public Integer getOriginalHeight() {
        return originalHeight;
    }

    public void setOriginalHeight(Integer originalHeight) {
        this.originalHeight = originalHeight;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Integer getBigSize() {
        return bigSize;
    }

    public void setBigSize(Integer bigSize) {
        this.bigSize = bigSize;
    }

    public Integer getBigWidth() {
        return bigWidth;
    }

    public void setBigWidth(Integer bigWidth) {
        this.bigWidth = bigWidth;
    }

    public Integer getBigHeight() {
        return bigHeight;
    }

    public void setBigHeight(Integer bigHeight) {
        this.bigHeight = bigHeight;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public Integer getSmallSize() {
        return smallSize;
    }

    public void setSmallSize(Integer smallSize) {
        this.smallSize = smallSize;
    }

    public Integer getSmallWidth() {
        return smallWidth;
    }

    public void setSmallWidth(Integer smallWidth) {
        this.smallWidth = smallWidth;
    }

    public Integer getSmallHeight() {
        return smallHeight;
    }

    public void setSmallHeight(Integer smallHeight) {
        this.smallHeight = smallHeight;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public Integer getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(Integer msgSeq) {
        this.msgSeq = msgSeq;
    }
}