/**
 * Project Name:zzj-db
 * File Name:MstFieldInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>MstFieldInfoKey表对象定义类（主键） <br/>
 * <p><strong>功能说明: </strong></p>定义MstFieldInfoKey表相关（主键）字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstFieldInfoKey implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6761536050103839918L;

	/**
    * 技术领域编码
    */
    private String techFieldCd;

    /**
    * 研究领域编码
    */
    private String rschFieldCd;

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