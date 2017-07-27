/**
 * Project Name:zzj-db
 * File Name:MstRankInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>MstRankInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MstRankInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstRankInfo implements Serializable{
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3811063170010835522L;

	/**
	 * 职称code
	 */
	private String rankCd;

	/**
	 * 职称名
	 */
    private String rankName;

    /**
	 * 创建人id
	 */
    private String createId;

    /**
	 * 创建时间
	 */
    private Date createTime;

    public String getRankCd() {
        return rankCd;
    }

    public void setRankCd(String rankCd) {
        this.rankCd = rankCd;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}