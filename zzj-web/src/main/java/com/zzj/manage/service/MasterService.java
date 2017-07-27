/**
Master * Project Name:zzj-web
 * File Name:MasterService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import com.zzj.db.dto.MstCodeInfo;

/**
 * <p><strong>类名: </strong></p>MasterService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装基础信息模块. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午2:24:07 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MasterService {
	/**
	 * 根据条件取得相应mstcodeinfo记录
	 * @param codeType 编码类型
	 * @return List<MstCodeInfo> 画面表示用code记录集
	 */
	List<MstCodeInfo> searchCodes(String codeType);
	/**
	 * 删除:根据条件更新Mst_Code_Info表中相应记录的deleteflag字段<br/>
	 * @param codeType 编码类型
	 * @param code 编码
	 * @return Integer 更新条目数
	 */
	Integer deleteCode(String codeType, String code);
	/**
	 * 修改查询:根据主键查询出对应记录<br/>
	 * @param codeType 编码类型
	 * @param code 编码
	 * @return MstCodeInfo 查询结果记录
	 */
	MstCodeInfo editCodeSearch(String codeType, String code);
	/**
	 * 更新数据库<br/>
	 * @param isAdd 操作类型
	 * @param codeInfo 更新信息
	 * @return Integer 查询结果记录
	 */
	Integer editCode(String isAdd, MstCodeInfo codeInfo);
}

