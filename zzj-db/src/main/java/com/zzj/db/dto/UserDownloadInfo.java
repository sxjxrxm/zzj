/**
 * Project Name:zzj-db
 * File Name:UserDownloadInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;

/**
 * <p><strong>类名: </strong></p>UserDownloadInfo表对象定义类br/>
 * <p><strong>功能说明: </strong></p>定义UserDownloadInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class UserDownloadInfo extends UserDownloadInfoKey {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5957265832128016604L;

	/**
    * 内容分类
    */
    private String busiType;

    /**
    * 主题名称
    */
    private String topicName;

    /**
    * 行为时间
    */
    private Date handleTime;

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}