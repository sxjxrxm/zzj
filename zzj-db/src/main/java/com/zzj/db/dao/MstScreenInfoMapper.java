/**
 * Project Name:zzj-db
 * File Name:MstScreenInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstScreenInfo;
import com.zzj.db.dto.MstScreenInfoKey;

/**
 * <p><strong>类名: </strong></p>页面配置表操作接口<br/>
 * <p><strong>功能说明: </strong></p>页面配置表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午9:36:35 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstScreenInfoMapper {
	
	/**
	 * 根据页面编码和父页面编码删除指定记录
	 * @param  key 页面配置表主键对象（页面编码和父页面编码）
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(MstScreenInfoKey key);

	/**
	 * 插入指定记录
	 * @param  record 要插入的页面配置表情报对象
	 * @return int 插入结果
	 */
    int insert(MstScreenInfo record);

	/**
	 * 有条件的插入指定记录<br/>
	 * 空的字段不插入
	 * @param  record 要插入的页面配置表情报对象
	 * @return int 插入结果
	 */
    int insertSelective(MstScreenInfo record);

	/**
	 * 根据页面编码和父页面编码取得指定记录
	 * @param  key 页面配置表主键对象（页面编码和父页面编码）
	 * @return MstScreenInfo 取得的页面配置表对象
	 */
    MstScreenInfo selectByPrimaryKey(MstScreenInfoKey key);

	/**
	 * 有条件的更新指定记录<br/>
	 * 空的字段不更新
	 * @param  record 要更新的页面配置表情报对象
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstScreenInfo record);

	/**
	 * 更新指定记录<br/>
	 * @param  record 要更新的页面配置表情报对象
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MstScreenInfo record);
    
    /**
   	 * 查询指定角色的访问页面列表<br/>
   	 * @param  roleId 角色编码
   	 * @return List<MstScreenInfo> 访问权限列表
   	 */
     List<MstScreenInfo> selectRolePermission(String roleId);

	/**
	 * 查询表中全部记录<br/>
	 * @param 无
	 * @return List<MstScreenInfo> 访问权限列表
	 */
	List<MstScreenInfo> selectAllScreen();
}