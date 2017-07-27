/**
 * Project Name:zzj-web
 * File Name:DemoServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.DemoBlo;
import com.zzj.db.dto.Demo;
import com.zzj.manage.modal.DemoVO;
import com.zzj.manage.service.DemoService;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>DemoServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>Demo处理业务实现类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日下午8:46:17 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class DemoServiceImpl implements DemoService {
	
	/**
	 * Demo业务数据库操作类
	 */
	@Autowired
	private DemoBlo blo;

	/**
	 * 取得Demo记录
	 * @return DemoVO 画面表示用Demo记录
	 * @throws Exception
	 */
	@Override
	public DemoVO searchDemo(HttpServletRequest request, int key) throws Exception {
		Demo demo = blo.getDemo(key);
		DemoVO vo = null;
		if(demo != null) {
			vo = new DemoVO();
			BeanUtils.copyProperties(demo, vo);
		} else{
			request.setAttribute(ZzjConstants.SYS_WARNING_KEY, "没有查询到数据");
		}
		return vo;
	}

	/**
	 * 保存Demo记录
	 * @param  vo demo记录
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveDemo(HttpServletRequest request, DemoVO vo) throws Exception {
		if(vo != null) {
			Demo demo = new Demo();
			BeanUtils.copyProperties(vo, demo);
			blo.saveDemo(demo);
			
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, "保存成功");
		}
	}

	/**
	 * 追加Demo记录
	 * @param  vo demo记录
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addDemo(HttpServletRequest request, DemoVO vo) throws Exception {
		if(vo != null) {
			Demo demo = new Demo();
			BeanUtils.copyProperties(vo, demo);
			
			if(StringUtil.isNullOrEmpty(vo.getF())) {
				// 返回画面原来参数
				request.setAttribute("demo", vo);
				
				ValidateErrorException vErrorException = new ValidateErrorException("错误测试，留在指定页面", "demo/demo_edit", "a");
				vErrorException.addError("错误测试，留在指定页面1", "b");
				vErrorException.addError("错误测试，留在指定页面2", "c");
				vErrorException.addError("错误测试，留在指定页面3", "d");
				vErrorException.addError("错误测试，留在指定页面4", "e");
				
				throw vErrorException;
			}
			blo.addDemo(demo);
		}
	}
}

