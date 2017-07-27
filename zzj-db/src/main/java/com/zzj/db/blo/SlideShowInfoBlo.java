/**
 * Project Name:zzj-db
 * File Name:SlideShowInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.SlideShowInfoMapper;
import com.zzj.db.dto.SlideShowInfo;

/**
 * <p><strong>类名: </strong></p>SlideShowInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>SlideShowInfo业务数据相关操作处理.<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日下午5:58:42 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class SlideShowInfoBlo {
	/**
	 * Slide_Show_Info表操作Mapper
	 */
	@Autowired
	private SlideShowInfoMapper slideShowInfoMapper;
	/**
	 * 查询Slide_Show_Info表全部记录<br/>
	 * @param  无
	 * @return List<SlideShowInfo> 查询结果
	 */
	public List<SlideShowInfo> selectAllSlides() {
		return slideShowInfoMapper.selectAllSlides();
	}
	/**
	 * 根据条件修改Slide_Show_Info表对应记录sortNo字段<br/>
	 * @param  slideShowInfo 查询记录对应
	 * @return Integer 保存结果
	 */
	public Integer saveSortNo(SlideShowInfo slideShowInfo) {
		return slideShowInfoMapper.updateByPrimaryKeySelective(slideShowInfo);
	}
	/**
	 * 根据条件修改Slide_Show_Info表对应记录deleteFlag字段<br/>
	 * @param  slideShowInfo 查询记录对应
	 * @return Integer 删除结果
	 */
	public Integer deleteSlides(SlideShowInfo slideShowInfo) {
		return slideShowInfoMapper.updateByPrimaryKeySelective(slideShowInfo);
	}
	/**
	 * 根据主键查询表Slide_Show_Info中对应记录<br/>
	 * @param  Map<String,Object> map 查询条件
	 * @return SlideShowInfo 查询结果
	 */
	public SlideShowInfo select(Map<String,Object> map) {
		//SlideShowInfo slideShowInfo = slideShowInfoMapper.selectByPrimaryKey(map);
		//return slideShowInfo;
		return null;
	}
	/**
	 * 向Slide_Show_Info表中添加记录<br/>
	 * @param  slideShowInfo 添加记录
	 * @return Integer 受影响行数
	 */
	public Integer saveSlides(SlideShowInfo slideShowInfo) {
		Integer x = slideShowInfoMapper.insert(slideShowInfo);
		return x;
	}
	/**
	 * 根据逐渐查询单条记录<br/>
	 * @param slideCd 
	 * @return SlideShowInfo 查询结果
	 */
	public SlideShowInfo sreachSlide(Integer slideCd) {
		return slideShowInfoMapper.selectByPrimaryKey(slideCd);
	}
	/**
	 * 根据条件更新记录<br/>
	 * @param slideShowInfo 记录 
	 * @return Integer 查询结果
	 */
	public Integer updateSlide(SlideShowInfo slideShowInfo) {
		return slideShowInfoMapper.updateByPrimaryKeySele(slideShowInfo);
	}
}

