/**
 * Project Name:zzj-db
 * File Name:VersionInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

/**
 * <p><strong>类名: </strong></p>VersionInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义VersionInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class VersionInfo extends VersionInfoKey{
    /**
	 * serialVersionUID。
	 */
	private static final long serialVersionUID = 7356456945434846491L;

    /**
    * 文件名称
    */
    private String versionNo;

    /**
    * 文件地址
    */
    private String versionAddress;
    
    /**
     * 更新内容
     */
    private String modifyMemo;

    /**
    * 逻辑删除flag  0：可用，1：不可用 default '0'
    */
    private Integer deleteFlag;

    /**
    * 创建人ID
    */
    private String createId;

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionAddress() {
        return versionAddress;
    }

    public void setVersionAddress(String versionAddress) {
        this.versionAddress = versionAddress;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

	/**
	 * 返回modifyMemo的值
	 * @return String modifyMemo的值
	 */
	public String getModifyMemo() {
		return modifyMemo;
	}

	/**
	 * 设置modifyMemo的值
	 * @param  modifyMemo modifyMemo的值
	 */
	public void setModifyMemo(String modifyMemo) {
		this.modifyMemo = modifyMemo;
	}

}