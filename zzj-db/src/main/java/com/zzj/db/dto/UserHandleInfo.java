/**
 * Project Name:zzj-db
 * File Name:UserHandleInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>UserHandleInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义UserHandleInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class UserHandleInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2758494654273616391L;

	/**
    * 主题编号
    */
    private String topicCd;
    
	/**
     * 主题名称
     */
     private String topicName;

    /**
    * 内容分类
    */
    private String busiType;

    /**
    * 点赞数
    */
    private Integer likeCount;

    /**
    * 收藏数
    */
    private Integer collectCount;

    /**
    * 分享数
    */
    private Integer shareCount;

    /**
    * 下载数
    */
    private Integer downloadCount;

    /**
    * 浏览数
    */
    private Integer scanCount;

    /**
     * 
     * 构造NewsHandleInfo实例。<br/>
     * @param  出事值为空/0，方便页面显示使用
     * @throws Exception
     */
    public UserHandleInfo() {
		this.topicCd = "";
		this.busiType = "";
		this.likeCount = 0;
		this.collectCount = 0;
		this.shareCount = 0;
		this.downloadCount = 0;
		this.scanCount = 0;
	}

	public String getTopicCd() {
        return topicCd;
    }

    public void setTopicCd(String topicCd) {
        this.topicCd = topicCd;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getScanCount() {
        return scanCount;
    }

    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }

	/**
	 * 返回topicName的值
	 * @return String topicName的值
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * 设置topicName的值
	 * @param  topicName topicName的值
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}