/**
 * Project Name:zzj-web
 * File Name:RecommendService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.model.RecommendVO;

/**
 * <p><strong>类名: </strong></p>RecommendService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装推荐置顶模块代码. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:35:29 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface RecommendService {

	/**
	 * 根据请求实例将消息置顶操作
	 * @param  request 请求实例，包含更新的信息及操作人的信息
	 * @param  code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
	 * @return String  执行操作的结果
	 * @author   任晓茂
	 */
	String recommendExecute(HttpServletRequest request, Integer code);
	/**
	 * 根据busi机能字段和recommendKbn推荐置顶查询对应记录
	 * @param recommendKbn 推荐或置顶 
	 * @param busi 机能
	 * @return List<RecommendVO> 查询结果集
	 */
	List<RecommendVO> selectRecommends(String recommendKbn, String busi);
	/**
	 * 批量取消方法：根据逐渐和推荐置顶状态更改相应记录删除状态<br/>
	 * @param  request http请求实例
	 * @param  topicCd 主题编码
	 * @param  kbn 区分
	 * @param  updateId 更新着ID
	 * @param  busiType 业务类型
	 * @return Integer 执行结果
	 * @author   刘研
	 */
	Integer delRecommends(String topicCd, String kbn, String updateId, String busiType);
	/**
	 * 保存排序方法：根据逐渐和推荐置顶状态更改相应记录显示顺序<br/>
	 * @param  request http请求实例
	 * @param  topicCd 主题编码
	 * @param  kbn 区分
	 * @param  busiType 业务类型
	 * @param  sortNo 表示顺
	 * @return Integer 执行结果
	 * @author   刘研
	 */
	Integer saveSortNo(String topicCd, String kbn, String updateId, String busiType, String sortNo);
	/**
	 * 取得推荐语。
	 * @param  request http请求实例
	 * @return 推荐语
	 */
	String selectRecommendMsg(HttpServletRequest request);
}

