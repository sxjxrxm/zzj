/**
 * Project Name:zzj-db
 * File Name:RecommendInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>RecommendInfoKey表对象定义类（主键） <br/>
 * <p><strong>功能说明: </strong></p>定义RecommendInfoKey表相关（主键）字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class RecommendInfoKey implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7082222973530497894L;

	/**
    * 业务分类：01专家，02知识，03需求，04E问，05E课堂，06E视频
    */
    private String busiType;

    /**
    * 推荐区分 1：推荐，2：置顶
    */
    private Integer recommendKbn;

    /**
    * 主题编号：相应业务分类所对应的ID
    */
    private String topicCd;

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Integer getRecommendKbn() {
        return recommendKbn;
    }

    public void setRecommendKbn(Integer recommendKbn) {
        this.recommendKbn = recommendKbn;
    }

    public String getTopicCd() {
        return topicCd;
    }

    public void setTopicCd(String topicCd) {
        this.topicCd = topicCd;
    }
}