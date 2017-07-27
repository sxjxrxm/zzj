/**
 * Project Name:zzj-db
 * File Name:NewsInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>NewsInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义NewsInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日上午9:40:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class NewsInfo implements Serializable{
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2780139888051345969L;

	/**
    * 资讯编码
    */
    private String newsCd;

    /**
    * 资讯分类  1：政策；2：案例；3：知识；4：文章
    */
    private Integer newsType;

    /**
     * 知识标签
     */
    private String newsNick;

    /**
    * 资讯主题
    */
    private String newsName;

    /**
    * 资讯简介
    */
    private String newsBrief;

    /**
    * 文件名称
    */
    private String newsFile;

    /**
    * 文件地址
    */
    private String newsAddress;

    /**
    * 费用分类  0：免费，1：付费 default '0'
    */
    private Integer paymentKbn;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 优惠价格
    */
    private BigDecimal priceDiscount;

    /**
    * 详情图片地址
    */
    private String bigIcon;

    /**
    * 列表图片地址
    */
    private String littleIcon;

    /**
    * 来源分类  1：为平台；2：为专家
    */
    private Integer sourceType;

    /**
    * 来源为平台时，为空；为专家时，保存专家编码；为其他时，保存作者
    */
    private String sourceOwner;

    /**
     * 专家作者2
     */
    private String expertCd2;

    /**
     * 专家作者3
     */
    private String expertCd3;

    /**
     * 专家作者4
     */
    private String expertCd4;

    /**
     * 专家作者5
     */
    private String expertCd5;

    /**
     * 由所有领域名称#知识主题#知识简介构成
	 * 如：电子信息安全领域智慧工程安全领域#大数据的应用之我见#中国最权威的大数据论述资料
     */
    private String keyword;

    /**
     * 分享链接地址
     */
    private String shareUrl;

    /**
     * 0：待审核，1：已审核
     */
    private Integer status;

    /**
     * 审核人ID
     */
    private String auditId;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 拒绝理由
     */
    private String refuseMemo;

    /**
    * 逻辑删除flag  0：可用，1：不可用 default '0'
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

    public String getNewsCd() {
        return newsCd;
    }

    public void setNewsCd(String newsCd) {
        this.newsCd = newsCd;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getNewsNick() {
        return newsNick;
    }

    public void setNewsNick(String newsNick) {
        this.newsNick = newsNick;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsBrief() {
        return newsBrief;
    }

    public void setNewsBrief(String newsBrief) {
        this.newsBrief = newsBrief;
    }

    public String getNewsFile() {
        return newsFile;
    }

    public void setNewsFile(String newsFile) {
        this.newsFile = newsFile;
    }

    public String getNewsAddress() {
        return newsAddress;
    }

    public void setNewsAddress(String newsAddress) {
        this.newsAddress = newsAddress;
    }

    public Integer getPaymentKbn() {
        return paymentKbn;
    }

    public void setPaymentKbn(Integer paymentKbn) {
        this.paymentKbn = paymentKbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(BigDecimal priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public String getLittleIcon() {
        return littleIcon;
    }

    public void setLittleIcon(String littleIcon) {
        this.littleIcon = littleIcon;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceOwner() {
        return sourceOwner;
    }

    public void setSourceOwner(String sourceOwner) {
        this.sourceOwner = sourceOwner;
    }

    public String getExpertCd2() {
        return expertCd2;
    }

    public void setExpertCd2(String expertCd2) {
        this.expertCd2 = expertCd2;
    }

    public String getExpertCd3() {
        return expertCd3;
    }

    public void setExpertCd3(String expertCd3) {
        this.expertCd3 = expertCd3;
    }

    public String getExpertCd4() {
        return expertCd4;
    }

    public void setExpertCd4(String expertCd4) {
        this.expertCd4 = expertCd4;
    }

    public String getExpertCd5() {
        return expertCd5;
    }

    public void setExpertCd5(String expertCd5) {
        this.expertCd5 = expertCd5;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getRefuseMemo() {
        return refuseMemo;
    }

    public void setRefuseMemo(String refuseMemo) {
        this.refuseMemo = refuseMemo;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}