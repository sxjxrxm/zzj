/**
 * Project Name:zzj-db
 * File Name:UserDownloadInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>UserDownloadInfo表对象定义类br/>
 * <p><strong>功能说明: </strong></p>定义UserDownloadInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class UserDownloadInfoKey implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1720295146696615849L;

	/**
    * 用户ID
    */
    private String userId;

    /**
    * 主题编号
    */
    private String topicCd;

    /**
     * 下载序号
     */
    private Integer downloadNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicCd() {
        return topicCd;
    }

    public void setTopicCd(String topicCd) {
        this.topicCd = topicCd;
    }

    public Integer getDownloadNo() {
        return downloadNo;
    }

    public void setDownloadNo(Integer downloadNo) {
        this.downloadNo = downloadNo;
    }
}