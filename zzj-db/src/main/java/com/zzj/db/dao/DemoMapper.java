/**
 * Project Name:zzj-db
 * File Name:DemoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import com.zzj.db.dto.Demo;

/**
 * <p><strong>类名: </strong></p>Demo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>Demo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日下午9:24:30 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface DemoMapper {

	/**
	 * 根据主键删除Demo中指定记录<br/>
	 * @param  a 字段1
	 * @return Integer 删除结果
	 * @throws Exception
	 */
    int deleteByPrimaryKey(Integer a);

	/**
	 * 插入指定记录<br/>
	 * @param  record demo记录
	 * @return Integer 插入结果
	 * @throws Exception
	 */
    int insert(Demo record);

	/**
	 * 有条件的插入指定记录<br/>
	 * @param  record demo记录
	 * @return Integer 插入结果
	 * @throws Exception
	 */
    int insertSelective(Demo record);

	/**
	 * 根据主键查询Demo中指定记录<br/>
	 * @param  a 字段1
	 * @return Demo 取得结果
	 * @throws Exception
	 */
    Demo selectByPrimaryKey(Integer a);

	/**
	 * 根据条件更新Demo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 * @throws Exception
	 */
    int updateByPrimaryKeySelective(Demo record);

	/**
	 * 根据主键更新Demo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 * @throws Exception
	 */
    int updateByPrimaryKey(Demo record);
}