/**
 * Project Name:zzj-db
 * File Name:SlideShowInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.SlideShowInfo;
/**
 * <p><strong>类名: </strong></p>Slide_Show_Info表操作接口<br/>
 * <p><strong>功能说明: </strong></p>Slide_Show_Info表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午11:34:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface SlideShowInfoMapper {
	/**
	 * 根据主键删除App_User_Info中指定记录<br/>
	 * @param  slideCd 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(Integer slideCd);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(SlideShowInfo record);
    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insertSelective(SlideShowInfo record);
    /**
	 * 根据主键查询Slide_Show_Info中指定记录<br/>
	 * @param  slideCd 主键
	 * @return SlideShowInfo 取得结果
	 */
    SlideShowInfo selectByPrimaryKey(Integer slideCd);
    /**
   	 * 根据条件更新Slide_Show_Info中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(SlideShowInfo record);
    /**
	 * 根据主键更新Slide_Show_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(SlideShowInfo record);
    /**
	 * 查询Slide_Show_Info中全部记录<br/>
	 * @param 无
	 * @return List<SlideShowInfo> 查询结果集
	 */
    List<SlideShowInfo> selectAllSlides();
    /**
   	 * 根据条件更新Slide_Show_Info中指定记录<br/>
   	 * @param  slideShowInfo 更新记录对应
   	 * @return Integer 更新结果
   	 */
	Integer updateByPrimaryKeySele(SlideShowInfo slideShowInfo);
}