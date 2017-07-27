/**
 * Project Name:zzj-db
 * File Name:MstCodeInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;

/**
 * <p><strong>类名: </strong></p>MstCodeInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MstCodeInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstCodeInfo extends MstCodeInfoKey {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2115377882817193586L;

	/**
    * 编码类别名
    */
    private String codeTypeName;

    /**
    * 编码名称
    */
    private String codeName;

    /**
    * 逻辑删除flag 0：可用，1：不可用
    */
    private Integer deleteFlag;

    /**
    * 更新时间
    */
    private Date updateTime;

    public String getCodeTypeName() {
        return codeTypeName;
    }

    public void setCodeTypeName(String codeTypeName) {
        this.codeTypeName = codeTypeName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

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