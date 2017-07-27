/**
 * Project Name:zzj-db
 * File Name:TopicFieldInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>TopicFieldInfoKey表对象定义类（主键） <br/>
 * <p><strong>功能说明: </strong></p>定义TopicFieldInfoKey表相关（主键）字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class TopicFieldInfoKey implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5522227719002627233L;

	/**
    * 业务分类：01专家，02知识，03需求，04E问，05E课堂，06E视频
    */
    private String busiType;

    /**
    * 主题编号：相应业务分类所对应的ID
    */
    private String topicCd;

    /**
    * 技术领域编码
    */
    private String techFieldCd;

    /**
    * 研究领域编码
    */
    private String rschFieldCd;

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getTopicCd() {
        return topicCd;
    }

    public void setTopicCd(String topicCd) {
        this.topicCd = topicCd;
    }

    public String getTechFieldCd() {
        return techFieldCd;
    }

    public void setTechFieldCd(String techFieldCd) {
        this.techFieldCd = techFieldCd;
    }

    public String getRschFieldCd() {
        return rschFieldCd;
    }

    public void setRschFieldCd(String rschFieldCd) {
        this.rschFieldCd = rschFieldCd;
    }
}