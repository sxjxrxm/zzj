/**
 * Project Name:zzj-db
 * File Name:PageResult.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>PageResult <br/>
 * <p><strong>功能说明: </strong></p>这个类分页参数. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日上午10:12:36 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class PageResult<T> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -645172409150253438L;
	
	/**
	 * 总记录数
	 */
	private long totalCount;
	
	/**
	 * 当前页号
	 */
	private int pageNo;
	
	/**
	 * 总页数
	 */
	private int totalPageCount;
	
	/**
	 * 页大小
	 */
	private int pageSize;
	
	/**
	 * 查询结果列表记录
	 */
	private List<T> items;

	/**
	 * 返回totalCount的值
	 * @return long totalCount的值
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * 构造PageResult实例。<br/>
	 * @throws Exception
	 */
	public PageResult() {
	}

	/**
	 * 构造PageResult实例。<br/>
	 * @param  初始化实例
	 * @throws Exception
	 */
	public PageResult(long totalCount, int pageNo, int pageSize, List<T> items) {
		// 构造查询列表对象
		this.items = (items == null) ? new ArrayList<T>() : items;
		// 构造总记录数
		this.totalCount = totalCount;
		// 构造当前页号，当前页号为非正整数时修改为1
		if(pageNo < 0) {
			this.pageNo = 1;
		}else {
			this.pageNo = pageNo;
		}
		// 构造页大小，传值非法时使用默认页大小
		if(pageSize < 0) {
			this.pageSize = ZzjConstants.DEFAULT_PAGESIZE;
		}else {
			this.pageSize = pageSize;
		}
		// 构造总页数
		if(totalCount != 0 && pageSize != 0){
			this.totalPageCount = (int) (totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1));
		}else{
			this.pageNo = 0;
			this.totalPageCount = 0;
		}
		
		// 当前页号大于总页数时，将当前页数修改为最后一页
		if(this.pageNo > this.totalPageCount) {
			this.pageNo = this.totalPageCount;
		}
	}
	
	
	/**
	 * 设置totalCount的值
	 * @param  totalCount totalCount的值
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 返回pageNo的值
	 * @return int pageNo的值
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置pageNo的值
	 * @param  pageNo pageNo的值
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 返回totalPageCount的值
	 * @return int totalPageCount的值
	 */
	public int getTotalPageCount() {
		return totalPageCount;
	}

	/**
	 * 设置totalPageCount的值
	 * @param  totalPageCount totalPageCount的值
	 */
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	/**
	 * 返回pageSize的值
	 * @return int pageSize的值
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置pageSize的值
	 * @param  pageSize pageSize的值
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 返回items的值
	 * @return List<T> items的值
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置items的值
	 * @param  items items的值
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	

}

