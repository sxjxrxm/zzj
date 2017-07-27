/**
 * Project Name:zzj-db
 * File Name:MstScreenInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
/**
 * <p><strong>类名: </strong></p>页面配置表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义页面配置表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstScreenInfoKey implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8848367644687677470L;

	/**
    * 页面编码
    */
    private String screenId;

    /**
    * 父页面编码
    */
    private String parentScreenId;

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getParentScreenId() {
        return parentScreenId;
    }

    public void setParentScreenId(String parentScreenId) {
        this.parentScreenId = parentScreenId;
    }
}