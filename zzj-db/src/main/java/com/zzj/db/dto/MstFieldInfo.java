/**
 * Project Name:zzj-db
 * File Name:MstFieldInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;

/**
 * <p><strong>类名: </strong></p>MstFieldInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MstFieldInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstFieldInfo extends MstFieldInfoKey {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4864664502504550751L;

	/**
    * 逻辑删除flag
    */
    private Integer deleteFlag;

    /**
    * 更新时间
    */
    private Date updateTime;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}