/**
 * Project Name:zzj-db
 * File Name:MstCodeInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>MstCodeInfoKey表对象定义类（主键） <br/>
 * <p><strong>功能说明: </strong></p>定义MstCodeInfoKey表相关（主键）字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstCodeInfoKey implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8516760699688577581L;

	/**
    * 编码类别
    */
    private String codeType;

    /**
    * 编码
    */
    private String code;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}