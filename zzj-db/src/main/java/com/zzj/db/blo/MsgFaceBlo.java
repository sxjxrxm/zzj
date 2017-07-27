/**
 * Project Name:zzj-db
 * File Name:MsgFaceBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MsgFaceMapper;
import com.zzj.db.dto.MsgFace;
import com.zzj.db.model.CourseMsgVO;

/**
 * <p><strong>类名: </strong></p>MsgFaceBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装在线课堂表情模块，数据库操作类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午3:49:14 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MsgFaceBlo {
	
	/**
	 * MsgFaceBlo表操作Mapper
	 */
	@Autowired
	private MsgFaceMapper mapper;
	
	
	/**
     * 插入表情
     * @param  record 记录
     * @return Integer 插入结果
     */
    public Integer insertSelective(MsgFace record){
    	return mapper.insertSelective(record);
    }

    /**
     * 根据mid查询记录
     * @param  mid 查询条件
     * @return List<CourseMsgVO> 记录集合
     */
	public List<CourseMsgVO> selectByMid(Long mid) {
		return mapper.selectByMid(mid);
	}
}

