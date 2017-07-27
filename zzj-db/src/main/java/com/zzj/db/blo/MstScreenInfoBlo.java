/**
 * Project Name:zzj-db
 * File Name:MstScreenInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstScreenInfoMapper;
import com.zzj.db.dto.MstScreenInfo;
import com.zzj.db.dto.MstScreenInfoKey;

/**
 * <p><strong>类名: </strong></p>MstScreenInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>页面权限相关操作处理类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月20日下午4:25:50 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstScreenInfoBlo {

	/**
	 * Mst_Code_Info表操作Mapper
	 */
	@Autowired
	private MstScreenInfoMapper mstScreenInfoMapper;
	
	/**
	 * 根据页面编码和父页面编码删除指定记录
	 * @param  screenId 页面编码
	 * @param  parentScreenId 父页面编码
	 * @return int 删除结果
	 */
    public int deleteByPrimaryKey(String screenId, String parentScreenId){
    	MstScreenInfoKey key = new MstScreenInfoKey();
    	key.setScreenId(screenId);
    	key.setParentScreenId(parentScreenId);
    	return mstScreenInfoMapper.deleteByPrimaryKey(key);
    }

	/**
	 * 插入指定记录
	 * @param  record 要插入的页面配置表情报对象
	 * @return int 插入结果
	 */
    public int insert(MstScreenInfo record){
    	return mstScreenInfoMapper.insert(record);
    }

	/**
	 * 有条件的插入指定记录<br/>
	 * 空的字段不插入
	 * @param  record 要插入的页面配置表情报对象
	 * @return int 插入结果
	 */
    public int insertSelective(MstScreenInfo record){
    	return mstScreenInfoMapper.insertSelective(record);
    }

	/**
	 * 根据页面编码和父页面编码取得指定记录
	 * @param  screenId 页面编码
	 * @param  parentScreenId 父页面编码
	 * @return MstScreenInfo 取得的页面配置表对象
	 */
    public MstScreenInfo selectByPrimaryKey(String screenId, String parentScreenId){
    	MstScreenInfoKey key = new MstScreenInfoKey();
    	key.setScreenId(screenId);
    	key.setParentScreenId(parentScreenId);
    	return mstScreenInfoMapper.selectByPrimaryKey(key);
    }

	/**
	 * 有条件的更新指定记录<br/>
	 * 空的字段不更新
	 * @param  record 要更新的页面配置表情报对象
	 * @return int 更新结果
	 */
    public int updateByPrimaryKeySelective(MstScreenInfo record){
    	return mstScreenInfoMapper.updateByPrimaryKeySelective(record);
    }

	/**
	 * 更新指定记录<br/>
	 * @param  record 要更新的页面配置表情报对象
	 * @return int 更新结果
	 */
    public int updateByPrimaryKey(MstScreenInfo record){
    	return mstScreenInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
   	 * 查询指定角色的访问页面列表<br/>
   	 * @param  roleId 角色编码
   	 * @return List<MstScreenInfo> 访问权限列表
   	 */
    public List<MstScreenInfo> selectRolePermission(String roleId){
    	return mstScreenInfoMapper.selectRolePermission(roleId);
    }
    /**
   	 * 查询全部记录<br/>
   	 * @param  无
   	 * @return List<MstScreenInfo> 访问权限列表
   	 */
    public List<MstScreenInfo> selectAllScreen(){
    	return mstScreenInfoMapper.selectAllScreen();
    }
}

