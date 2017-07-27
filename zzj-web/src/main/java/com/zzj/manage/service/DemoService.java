/**
 * Project Name:zzj-web
 * File Name:LoginService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import javax.servlet.http.HttpServletRequest;

import com.zzj.manage.modal.DemoVO;

/**
 * <p><strong>类名: </strong></p>LoginService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月12日下午11:37:02 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface DemoService {

	/**
	 * Demo表查询处理<br/>
	 * @return DemoVO  Demo表记录
	 * @throws Exception
	 */
	public DemoVO searchDemo(HttpServletRequest request, int key) throws Exception;

	/**
	 * 
	 * Demo表保存处理<br/>
	 * @param  demo Demo数据
	 * @throws Exception
	 */
	public void saveDemo(HttpServletRequest request, DemoVO demo) throws Exception;

	/**
	 * 
	 * Demo表追加处理<br/>
	 * @param  vo Demo数据
	 * @throws Exception
	 */
	public void addDemo(HttpServletRequest request, DemoVO demo) throws Exception;
}

