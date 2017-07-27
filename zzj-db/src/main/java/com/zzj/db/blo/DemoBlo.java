/**
 * Project Name:zzj-db
 * File Name:DemoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.DemoMapper;
import com.zzj.db.dto.Demo;

/**
 * <p><strong>类名: </strong></p>Demo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>Demo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日下午9:24:30 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class DemoBlo {

	/**
	 * Demo表操作Mapper
	 */
	@Autowired
	private DemoMapper mapper;

	/**
	 * Demo记录取得处理
	 * @param  key 检索条件
	 * @return Demo Demo记录
	 * @throws Exception
	 */
	public Demo getDemo(int key) {
		Demo demo = mapper.selectByPrimaryKey(key);
		
		return demo;
	}

	/**
	 * Demo记录保存处理
	 * @param  demo Demo记录
	 * @return int 保存结果
	 * @throws Exception
	 */
	public int saveDemo(Demo demo) {
		int result = 0;
		if(demo != null) {
			result = mapper.updateByPrimaryKey(demo);
		}
		
		return result;
	}

	/**
	 * Demo记录追加处理
	 * @param  demo Demo记录
	 * @return int 追加结果
	 * @throws Exception
	 */
	public int addDemo(Demo demo) {
		int result = 0;
		if(demo != null) {
			result = mapper.insert(demo);
		}
		
		return result;
	}
}

