/**
 * Project Name:zzj-db
 * File Name:ExpertInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>ExpertInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义ExpertInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午6:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ExpertInfo implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 344140711276784770L;

	/**
    * 专家ID
    */
    private String expertId;

    /**
    * 专家名
    */
    private String expertName;

    /**
    * 头衔职称
    */
    private String rank;
    
    /**
     * 头衔职称2
     */
    private String rank2;
    
    /**
     * 头衔职称3
     */
    private String rank3;
    
    /**
     * 头衔职称4
     */
    private String rank4;

    /**
    * 工作年限
    */
    private String experience;

    /**
    * 邮箱
    */
    private String email;
    
    /**
     * 手机号
     */
    private String phone;

    /**
    * 所在省
    */
    private String cityP;
    
    /**
     * 所在市
     */
    private String cityC;
    
    /**
     * 所在区
     */
    private String cityD;
    
    /**
     * 专家简介
     */
    private String expertBrief;
	
	/**
	 * 专家背景图片地址
	 */
	private String backgroundAdress;
    
    /**
     * 工作单位
     */
    private String company;
    
    /**
     * 关键词
     * 由地域名称#所有领域名称#专家名#专家简介构成
	 * 如：北京市朝阳区#电子信息安全领域智慧工程安全领域#刘博士#中国发表相关文章最多的专家
     */
    private String keyword;
    
    /**
     * 分享链接地址
     */
    private String shareUrl;

    /**
    * 审核状态  0：待审核，1：已审核
    */
    private Integer status;
    
    /**
     * 审核拒绝理由：refuseType 从系统配置表对应
     */
    private String refuseMemo;

    /**
    * 审核人ID
    */
    private String auditId;

    /**
    * 审核时间
    */
    private Date auditTime;

    /**
    * 逻辑删除flag  0：可用，1：不可用  default '0'
    */
    private Integer deleteFlag;
    
    /**
     * 置顶flag  0：不置顶，1：置顶  default '0'
     */
    private Integer topFlag;

    /**
    * 创建人ID
    */
    private String createId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最后更新人ID
    */
    private String updateId;

    /**
    * 最后更新时间
    */
    private Date updateTime;
    
    /**
	 * 返回expertId的值
	 * @return String expertId的值
	 */
    public String getExpertId() {
        return expertId;
    }

    /**
	 * 设置expertId的值
	 * @param  expertId expertId的值
	 */
    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    /**
	 * 返回expertName的值
	 * @return String expertName的值
	 */
    public String getExpertName() {
        return expertName;
    }

    /**
	 * 设置expertName的值
	 * @param  expertName expertName的值
	 */
    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    /**
	 * 返回rank的值
	 * @return String rank的值
	 */
    public String getRank() {
        return rank;
    }

    /**
	 * 设置rank的值
	 * @param  rank rank的值
	 */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
	 * 返回rank2的值
	 * @return String rank2的值
	 */
	public String getRank2() {
		return rank2;
	}

	/**
	 * 设置rank2的值
	 * @param  rank2 rank2的值
	 */
	public void setRank2(String rank2) {
		this.rank2 = rank2;
	}

	/**
	 * 返回rank3的值
	 * @return String rank3的值
	 */
	public String getRank3() {
		return rank3;
	}

	/**
	 * 设置rank3的值
	 * @param  rank3 rank3的值
	 */
	public void setRank3(String rank3) {
		this.rank3 = rank3;
	}

	/**
	 * 返回rank4的值
	 * @return String rank4的值
	 */
	public String getRank4() {
		return rank4;
	}

	/**
	 * 设置rank4的值
	 * @param  rank4 rank4的值
	 */
	public void setRank4(String rank4) {
		this.rank4 = rank4;
	}

	/**
	 * 返回experience的值
	 * @return String experience的值
	 */
    public String getExperience() {
        return experience;
    }

    /**
	 * 设置experience的值
	 * @param  experience experience的值
	 */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
	 * 返回email的值
	 * @return String email的值
	 */
    public String getEmail() {
        return email;
    }

    /**
	 * 设置email的值
	 * @param  email email的值
	 */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
	 * 返回phone的值
	 * @return String phone的值
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置phone的值
	 * @param  phone phone的值
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

    /**
	 * 返回status的值
	 * @return String status的值
	 */
    public Integer getStatus() {
        return status;
    }

    /**
	 * 返回refuseMemo的值
	 * @return String refuseMemo的值
	 */
	public String getRefuseMemo() {
		return refuseMemo;
	}

	/**
	 * 设置refuseMemo的值
	 * @param  refuseMemo refuseMemo的值
	 */
	public void setRefuseMemo(String refuseMemo) {
		this.refuseMemo = refuseMemo;
	}

	/**
   	 * 设置status的值
   	 * @param  status status的值
   	 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
	 * 返回auditId的值
	 * @return String auditId的值
	 */
    public String getAuditId() {
        return auditId;
    }

    /**
   	 * 设置auditId的值
   	 * @param  auditId auditId的值
   	 */
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    /**
	 * 返回auditTime的值
	 * @return Date auditTime的值
	 */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
   	 * 设置auditTime的值
   	 * @param  auditTime auditTime的值
   	 */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
	 * 返回deleteFlag的值
	 * @return Integer deleteFlag的值
	 */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
   	 * 设置deleteFlag的值
   	 * @param  deleteFlag deleteFlag的值
   	 */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
	 * 返回createId的值
	 * @return String createId的值
	 */
    public String getCreateId() {
        return createId;
    }

    /**
   	 * 设置createId的值
   	 * @param  createId createId的值
   	 */
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    /**
	 * 返回createTime的值
	 * @return Date createTime的值
	 */
    public Date getCreateTime() {
        return createTime;
    }

    /**
   	 * 设置createTime的值
   	 * @param  createTime createTime的值
   	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
	 * 返回updateId的值
	 * @return String updateId的值
	 */
    public String getUpdateId() {
        return updateId;
    }

    /**
   	 * 设置updateId的值
   	 * @param  updateId updateId的值
   	 */
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    /**
	 * 返回updateTime的值
	 * @return Date updateTime的值
	 */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
   	 * 设置updateTime的值
   	 * @param  updateTime updateTime的值
   	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/**
	 * 返回cityP的值
	 * @return String cityP的值
	 */
	public String getCityP() {
		return cityP;
	}

	/**
	 * 设置cityP的值
	 * @param  cityP cityP的值
	 */
	public void setCityP(String cityP) {
		this.cityP = cityP;
	}

	/**
	 * 返回cityC的值
	 * @return String cityC的值
	 */
	public String getCityC() {
		return cityC;
	}

	/**
	 * 设置cityC的值
	 * @param  cityC cityC的值
	 */
	public void setCityC(String cityC) {
		this.cityC = cityC;
	}

	/**
	 * 返回cityD的值
	 * @return String cityD的值
	 */
	public String getCityD() {
		return cityD;
	}

	/**
	 * 设置cityD的值
	 * @param  cityD cityD的值
	 */
	public void setCityD(String cityD) {
		this.cityD = cityD;
	}

	/**
	 * 返回expertBrief的值
	 * @return String expertBrief的值
	 */
	public String getExpertBrief() {
		return expertBrief;
	}

	/**
	 * 设置expertBrief的值
	 * @param  expertBrief expertBrief的值
	 */
	public void setExpertBrief(String expertBrief) {
		this.expertBrief = expertBrief;
	}

	/**
	 * 返回keyword的值
	 * @return String keyword的值
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置keyword的值
	 * @param  keyword keyword的值
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 返回company的值
	 * @return String company的值
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 设置company的值
	 * @param  company company的值
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 返回shareUrl的值
	 * @return String shareUrl的值
	 */
	public String getShareUrl() {
		return shareUrl;
	}

	/**
	 * 设置shareUrl的值
	 * @param  shareUrl shareUrl的值
	 */
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	/**
	 * 返回topFlag的值
	 * @return Integer topFlag的值
	 */
	public Integer getTopFlag() {
		return topFlag;
	}

	/**
	 * 设置topFlag的值
	 * @param  topFlag topFlag的值
	 */
	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}

	/**
	 * 返回backgroundAdress的值
	 * @return String backgroundAdress的值
	 */
	public String getBackgroundAdress() {
		return backgroundAdress;
	}

	/**
	 * 设置backgroundAdress的值
	 * @param  backgroundAdress backgroundAdress的值
	 */
	public void setBackgroundAdress(String backgroundAdress) {
		this.backgroundAdress = backgroundAdress;
	}
    
}