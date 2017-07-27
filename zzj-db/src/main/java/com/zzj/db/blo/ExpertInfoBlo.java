/**
 * Project Name:zzj-db
 * File Name:ExpertInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.AppUsersInfoMapper;
import com.zzj.db.dao.ExpertInfoMapper;
import com.zzj.db.dao.MstCodeInfoMapper;
import com.zzj.db.dao.MstUsersInfoMapper;
import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.ExpertInfoEditVO;
import com.zzj.db.model.ExpertInfoListVO;
import com.zzj.db.model.SlideResultVO;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;


/**
 * <p><strong>类名: </strong></p>ExpertInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>Expert_Info表相关数据操作处理 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午6:55:35 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class ExpertInfoBlo {
	/**
	 * ExpertInfo表操作Mapper
	 */
	@Autowired
	private ExpertInfoMapper expertInfoMapper;
	
	/**
	 * AppUsersInfoMapper表操作Mapper
	 */
	@Autowired
	private AppUsersInfoMapper appUsersInfoMapper;
	
	/**
	 * MstUsersInfoMapper表操作Mapper
	 */
	@Autowired
	private MstUsersInfoMapper mstUsersInfoMapper;
	
	/**
	 * MstCodeInfoMapper表操作Mapper
	 */
	@Autowired
	private MstCodeInfoMapper mstCodeInfoMapper;
	
	/**
	 * 根据专家一览页面查询条件带分页查询Expert表记录<br/>
	 * @param  map 查询条件及分页信息
	 * @return List<ExpertInfoListVO> 查询结果
	 */
	public List<ExpertInfoListVO> selectSelectiveByPage(Map<String,Object> map) {
		List<ExpertInfoListVO> expertInfos = expertInfoMapper.selectSelectiveByPage(map);
		return expertInfos;
	}

	/**
	 * 根据专家一览页面查询条件查询Expert表记录数量<br/>
	 * @param  map 查询条件及分页信息
	 * @return int 查询结果
	 */
	public int selectTotalCount(Map<String,Object> map) {
		return expertInfoMapper.selectTotalCount(map);
	}
	
	/**
	 * 根据主键查询Expert表记录<br/>
	 * @param  expertId 主键
	 * @return ExpertInfoEditVO 查询结果
	 */
	public ExpertInfoEditVO selectByPrimaryKey(String expertId) {
		ExpertInfoEditVO expertInfoEditVO=expertInfoMapper.selectByPrimaryKey(expertId);
		
		if (expertInfoEditVO != null && expertInfoEditVO.getStatus() == ZzjConstants.STATUS_2 && StringUtil.isNotBlank(expertInfoEditVO.getRefuseMemo())) {
			if (StringUtil.isNumber(expertInfoEditVO.getRefuseMemo())) {
				// 查询系统配置表中拒绝理由对应code值的最大值
				int maxRefuseCode = mstCodeInfoMapper.selectMaxRefuseCode();
				if (Integer.parseInt(expertInfoEditVO.getRefuseMemo()) > 0 && Integer.parseInt(expertInfoEditVO.getRefuseMemo()) <= maxRefuseCode) {
					expertInfoEditVO.setRefuseMemoType(ZzjConstants.REFUSE_MEMO_TYPE_1);
				} else {
					expertInfoEditVO.setRefuseMemoType(ZzjConstants.REFUSE_MEMO_TYPE_2);
				}
				
			} else {
				expertInfoEditVO.setRefuseMemoType(ZzjConstants.REFUSE_MEMO_TYPE_2);
			}
		}
		
		return expertInfoEditVO;
	}

	/**
	 * 根据主键删除Expert表记录<br/>
	 * @param  ids 专家id、当前用户id
	 * @return boolean 删除结果
	 */
	public boolean deleteExpertById(String[] ids) {
		ExpertInfo expertInfo = new ExpertInfo();
		expertInfo.setExpertId(ids[0]);
		expertInfo.setUpdateId(ids[1]);
		expertInfo.setUpdateTime(new Date());
		expertInfo.setDeleteFlag(1);
		
		AppUsersInfo appUsersInfo = new AppUsersInfo();
		appUsersInfo.setUserId(ids[0]);
		appUsersInfo.setUpdateId(ids[1]);
		appUsersInfo.setUpdateTime(new Date());
		appUsersInfo.setDeleteFlag(1);
		appUsersInfoMapper.updateByPrimaryKeySelective(appUsersInfo);
		
		MstUsersInfo mstUsersInfo = new MstUsersInfo();
		mstUsersInfo.setUserId(ids[0]);
		mstUsersInfo.setUpdateId(ids[1]);
		mstUsersInfo.setUpdateTime(new Date());
		mstUsersInfo.setDeleteFlag(1);
		mstUsersInfoMapper.updateByPrimaryKeySelective(mstUsersInfo);
		
		int deleteNum = expertInfoMapper.updateByPrimaryKeySelective(expertInfo);
		return deleteNum == 1 ? true : false;
	}

	/**
	 * 根据专家记录更新Expert表记录<br/>
	 * @param  expertRecord 专家记录
	 * @param  appUsersInfo app用户记录
	 * @param  temp1 dbapp用户记录
	 * @param  mstUsersInfo 系统用户记录
	 * @return int 更新结果
	 */
	public int saveExpert(ExpertInfo expertRecord, AppUsersInfo appUsersInfo, AppUsersInfo temp1,
			MstUsersInfo mstUsersInfo) {
		if (expertRecord == null || StringUtil.isBlank(expertRecord.getExpertId()) || 
				appUsersInfo == null || StringUtil.isBlank(appUsersInfo.getUserId()) ||
				mstUsersInfo == null || StringUtil.isBlank(mstUsersInfo.getUserId())) {
			return 0;
		}
		
		if (expertRecord.getStatus() == ZzjConstants.STATUS_1)
		{
			if (temp1 == null) {
				// 后台追加专家后，插入前台用户表
				appUsersInfo.setCreateId(appUsersInfo.getUpdateId());
				appUsersInfo.setCreateTime(appUsersInfo.getUpdateTime());
				appUsersInfoMapper.insertSelective(appUsersInfo);
				
				// 插入后台用户表
				mstUsersInfo.setCreateId(mstUsersInfo.getUpdateId());
				mstUsersInfo.setCreateTime(mstUsersInfo.getUpdateTime());
				mstUsersInfoMapper.insertSelective(mstUsersInfo);
			} else if(!temp1.getRoleId().equals(ZzjConstants.EXPERT_ID_IN_USERS_INFO)) {
				// 普通用户成为专家时，更新成专家角色
				temp1.setRoleId(ZzjConstants.EXPERT_ID_IN_USERS_INFO);
				temp1.setUpdateId(appUsersInfo.getUpdateId());
				temp1.setUpdateTime(appUsersInfo.getUpdateTime());
				appUsersInfoMapper.updateByPrimaryKeySelective(temp1);
				
				// 插入后台用户表的密码和前台密码一致
				mstUsersInfo.setPassword(temp1.getPassword());
				mstUsersInfo.setCreateId(mstUsersInfo.getUpdateId());
				mstUsersInfo.setCreateTime(mstUsersInfo.getUpdateTime());
				mstUsersInfoMapper.insertSelective(mstUsersInfo);
			} else {
				// 后台用户修改头像操作
				temp1.setAvator(appUsersInfo.getAvator());
				temp1.setUserName(appUsersInfo.getUserName());
				temp1.setUpdateId(appUsersInfo.getUpdateId());
				temp1.setUpdateTime(appUsersInfo.getUpdateTime());
				appUsersInfoMapper.updateByPrimaryKeySelective(temp1);
				
				mstUsersInfoMapper.updateByPrimaryKeySelective(mstUsersInfo);
			}
		}
		
		return expertInfoMapper.updateByPrimaryKeySelective(expertRecord);
	}

	/**
	 * 根据专家记录插入Expert表记录<br/>
	 * @param  expertRecord 专家记录
	 * @param  appUsersInfo app用户记录
	 * @param  mstUsersInfo 系统用户记录
	 * @return int 插入结果
	 */
	public int addExpert(ExpertInfo expertRecord, AppUsersInfo appUsersInfo, MstUsersInfo mstUsersInfo) {
		if (expertRecord == null || StringUtil.isBlank(expertRecord.getExpertId()) || 
				appUsersInfo == null || StringUtil.isBlank(appUsersInfo.getUserId()) ||
				mstUsersInfo == null || StringUtil.isBlank(mstUsersInfo.getUserId())) {
			return 0;
		}
		// 判断是否存在该专家记录
		ExpertInfoEditVO expertInfoEditVO = expertInfoMapper.selectByPrimaryKey(expertRecord.getExpertId());
		if (expertInfoEditVO != null) {
			return 0;
		}
			
		if (expertRecord.getStatus() == ZzjConstants.STATUS_1)
		{
			// 后台追加专家后，插入前台用户表
			appUsersInfo.setCreateId(appUsersInfo.getUpdateId());
			appUsersInfo.setCreateTime(appUsersInfo.getUpdateTime());
			appUsersInfoMapper.insertSelective(appUsersInfo);
			
			// 插入后台用户表
			mstUsersInfo.setCreateId(mstUsersInfo.getUpdateId());
			mstUsersInfo.setCreateTime(mstUsersInfo.getUpdateTime());
			mstUsersInfoMapper.insertSelective(mstUsersInfo);
		}
		
		return expertInfoMapper.insertSelective(expertRecord);
	}
	
	/**
	 * 根据研究领域和主题查询对应专家<br/>
	 * @param  slideResultVO 轮播记录
	 * @return List<ExpertInfo> 查询结果
	 */
	public List<ExpertInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<ExpertInfo> ExpertInfos = expertInfoMapper.slideEditSearch(slideResultVO);
		return ExpertInfos;
	}

	/**
	 * 根据专家姓名查找对应专家id，暂不考虑重名，如果重名返回第一个id <br/>
	 * @param  expertName 专家姓名
	 * @return String 查询结果
	 */
	public String selectIdByExpertName(String expertName) {
		List<String> ids = expertInfoMapper.selectIdByExpertName(expertName);
		if (ids == null || ids.size() < 1) {
			return null;
		}
		return ids.get(0);
	}

	/**
	 * 取得全部专家记录（根据专家一览页面的检索条件）
	 * @param Map<String, Object> map 查询参数
	 * @return List<ExpertInfoListVO> 专家列表
	 */
	public List<ExpertInfoListVO> selectAll(Map<String, Object> map) {
		List<ExpertInfoListVO> expertInfos = expertInfoMapper.selectAll(map);
		return expertInfos;
	}

	/**
	 * 知识编辑页面专家姓名联想输入信息<br/>
	 * @param 无
	 * @return String 查询结果
	 */
	public String selectAllExpertName() {
		// 根据业务需要，查找所有职称记录，前台联想输入使用
		List<String> names = expertInfoMapper.selectAllExpertName();
		StringBuffer sBuffer = new StringBuffer();
		if (names != null && names.size() > 0) {
			for (int i = 0; i < names.size(); i++) {
				sBuffer.append(names.get(i)).append("    ");
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  expertInfo 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(ExpertInfo expertInfo) {
		return expertInfoMapper.updateByPrimaryKeySelective(expertInfo);
	}

	/**
	 * 根据专家id获得专家姓名，获取不到则直接返回
	 * @param  expertId 专家id
	 * @return String  专家姓名
	 */
	public String selectNameByExpertId(String expertId) {
		return expertInfoMapper.selectNameByExpertId(expertId);
	}

	/**
	 * 根据专家姓名查找对应专家id<br/>
	 * @param  expertName 专家姓名
	 * @return List<String> 查询结果
	 */
	public List<String> selectIdsByExpertName(String expertName) {
		List<String> ids = expertInfoMapper.selectIdByExpertName(expertName);
		return ids;
	}
	
	/**
	 * 查询是否是专家 是返回1 不是返回null
	 * @param  maps 检索条件
	 * @return Integer 查询结果
	 */
	public Integer selectIsExpertById(Map<String, Object> maps){
		return expertInfoMapper.selectIsExpertById(maps);
	}
	
	/**
	 * 取得专家相关信息
	 * @param  expertId 专家ID
	 * @return ExpertInfoEditVO 专家信息
	 */
	public ExpertInfoEditVO selectExpertInfo(String expertId){
		return expertInfoMapper.selectExpertInfo(expertId);
	}
}

