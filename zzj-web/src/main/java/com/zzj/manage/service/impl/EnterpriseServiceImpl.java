/**
 * Project Name:zzj-web
 * File Name:EnterpriseServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.EnterpriseBlo;
import com.zzj.db.blo.MstAreaInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.CorpInfo;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.EnterpriseVO;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.EnterpriseService;
import com.zzj.util.Base64;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>EnterpriseServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>政企管理业务数据相关操作处理. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月6日下午3:55:09 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	/**
	 * 政企管理业务数据库操作类
	 */
	@Autowired
	private EnterpriseBlo enterpriseBlo;
	
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	
	/**
	 * 系统城市配置模块业务数据库操作类
	 */
	@Autowired
	private MstAreaInfoBlo mstAreaInfoBlo;
	
	/**
	 * 取得政企一览全部记录
	 * @param enterpriseVO 查询条件
	 * @return 画面表示用数据对象
	 */
	@Override
	public List<EnterpriseVO> selectByParament (EnterpriseVO enterpriseVO) {
		
		// 获得该专家所在的省信息，查询对应的市级信息
		String cityPRegionCode = enterpriseVO.getCityP();
		if (StringUtil.isNotBlank(cityPRegionCode)) {
			Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(enterpriseVO.getCityP());
			List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
			enterpriseVO.setAreaInfosCityC(areaInfosCityC);
		}
		
		// 领域查询结果添加
		List<EnterpriseVO> list = enterpriseBlo.selectByParament(enterpriseVO);
		
		for (EnterpriseVO eVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_01);
			map1.put("topicCd", eVO.getUserId());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if (topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			eVO.setFieldCd(topicFieldInfo);		
		}

		return list;
	}	
	
	/**
	 * 取得政企一览符合条件记录的总数
	 * @param enterpriseVO 查询条件
	 * @return 画面表示用数据对象
	 */
	@Override
	public PageResult<EnterpriseVO> searchPagging (EnterpriseVO enterpriseVO) {
		// 取得符合条件的总数
		Integer totalCount = enterpriseBlo.selectTotalCount(enterpriseVO);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<EnterpriseVO>();
		}
		if (enterpriseVO.getDbIndex() >= totalCount)
		{
			int flag = totalCount % enterpriseVO.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / enterpriseVO.getPageSize();
			enterpriseVO.setPageNo((flag == 0) ? num : num + 1);
			enterpriseVO.setDbIndex((enterpriseVO.getPageNo() - 1) * enterpriseVO.getPageSize());
		}
		if (enterpriseVO.getDbIndex() < 0)
		{
			enterpriseVO.setDbIndex(0);
			enterpriseVO.setPageNo(1);
		}

		// 分页数据查询
		List<EnterpriseVO> list = enterpriseBlo.selectPagging(enterpriseVO);

		// 领域查询结果添加
		for (EnterpriseVO eVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_01);
			map1.put("topicCd", eVO.getUserId());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if (topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			eVO.setFieldCd(topicFieldInfo);
		}
		
		// 构造分页结果集
		PageResult<EnterpriseVO> pageResult = new PageResult<EnterpriseVO>();
		
		pageResult.setPageNo(enterpriseVO.getDbIndex() / enterpriseVO.getPageSize() + 1);
		pageResult.setPageSize(enterpriseVO.getPageSize());
		int flag = totalCount % enterpriseVO.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / enterpriseVO.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	
	/**
	 * 取得政企审核使用记录
	 * @param userId 查询对象
	 * @return EnterpriseVO 画面表示用政企数据对象
	 */
	@Override
	public EnterpriseVO selectBykey (String userId) {
		
		// 领域查询结果添加
		EnterpriseVO list = enterpriseBlo.selectByKey(userId);
		
		// 初期化一级省份信息
		list.setAreaInfosCityP(this.selectByArea());
		
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_01);
			map1.put("topicCd", list.getUserId());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if (topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			list.setFieldCd(topicFieldInfo);	
			
			// 获得该专家所在的省信息，查询对应的市级信息
			String cityPRegionCode = list.getCityP();
			if (StringUtil.isNotBlank(cityPRegionCode)) {
				Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(list.getCityP());
				List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
				list.setAreaInfosCityC(areaInfosCityC);
			}
			return list;
	}
	
	/**
	 *保存政企审核结果
	 *@param  corpInfo 保存对象
	 *return 更新结果数
	 */
	@Override
	 public int upDataByKey (CorpInfo corpInfo) {
			 
		return  enterpriseBlo.updateByKey(corpInfo);
	 }

	/**
	 * 取得城市一级记录
	 * @param corpInfo 查询对象
	 * @return 画面表示用数据对象
	 */
	@Override
	public List<MstAreaInfo> selectByArea() {
		
		// 构造城市信息
		Double parentId = (double) 1;// 省级记录的parentId为1
		List<MstAreaInfo> areaInfosCityP = mstAreaInfoBlo.selectAllByParentId(parentId);
		
		return areaInfosCityP;
	}
	
	/**
	 * 城市联动菜单异步请求<br/>
	 * @param  request 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	@Override
	public List<MstAreaInfo> getCityC (HttpServletRequest request) {
		String cityPCode = request.getParameter("cityPCode");
		if (StringUtil.isBlank(cityPCode)) {
			return null;
		}
		Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(cityPCode);
		List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
		return areaInfosCityC;
	}
	
	/**
	 * 查询省份对应的城市信息<br/>
	 * @param  enterpriseVO 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	@Override
	public List<MstAreaInfo> getCityC1 (EnterpriseVO enterpriseVO) {
		String cityPCode = enterpriseVO.getCityP();
		if (StringUtil.isBlank(cityPCode)) {
			return null;
		}
		Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(cityPCode);
		List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
		return areaInfosCityC;
	}
	
	/**
	 * 保存政企一览查询条件 
	 * @param  request 请求实例
	 * @return enterpriseVO一览查询条件
	 * @throws IOException 
	 * @throws Exception 
	 */
	@Override
	public Object savePara (HttpServletRequest request, String add) throws Exception {

		// 构造返回政企一览选项信息 【edit:审核预览、save：审核保存、back：返回】。
		//用户名
		String userName = null;
		//审核状态
		String[] staList = null;
		//领域
		String[] tFTList = null;
		//单位性质
		String corpType = null;
		//常驻省
		String cityP = null;
		//常驻市
		String cityC = null;
		//从列表进编辑页面
		if(StringUtil.isNotBlank(add) && "edit".equals(add)){
			userName = request.getParameter("userName");
			staList = request.getParameterValues("statusList");
			tFTList = request.getParameterValues("techFieldTypeList");
			corpType = request.getParameter("corpType");
			cityP = request.getParameter("cityP");
			cityC = request.getParameter("cityC");
		}else{
			//保存或返回列表页
			String selectKey = request.getParameter("selectKey");
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				userName = keyVO.getUserName();
				staList = keyVO.getAuditStatus();
				tFTList = keyVO.getField();
				corpType = keyVO.getCorpType();
				cityP = keyVO.getCityP();
				cityC = keyVO.getCityC();
			}
		}
		//构造modo
		KeyVO vo = new KeyVO();
		EnterpriseVO enterpriseVO = new EnterpriseVO();
		List<String> temp = null;
		
		// 用户名称
		enterpriseVO.setUserName(userName);
		vo.setUserName(userName);
		// 审核状态
		if (StringUtil.isNotBlank(staList)) {
			temp = new ArrayList<String>();
			for (int i = 0; i < staList.length; i++) {
				temp.add(staList[i]);
			}
			enterpriseVO.setStatusList(temp);
			vo.setAuditStatus(staList);
		} else {
			enterpriseVO.setStatusList(null);
			vo.setAuditStatus(null);
		}
		// 领域
		if (StringUtil.isNotBlank(tFTList)) {
			temp = new ArrayList<String>();
			for (int i = 0; i < tFTList.length; i++) {
				temp.add(tFTList[i]);
			}
			enterpriseVO.setTechFieldTypeList(temp);
			vo.setField(tFTList);
		} else {
			enterpriseVO.setTechFieldTypeList(null);
			vo.setField(null);
		}
		// 企业性质
		enterpriseVO.setCorpType(corpType);
		vo.setCorpType(corpType);
		// 所在城市
		enterpriseVO.setCityP(cityP);
		vo.setCityP(cityP);
		enterpriseVO.setCityC(cityC);
		vo.setCityC(cityC);
		//从编辑页返回列表页
		if(StringUtil.isNotBlank(add) && "back".equals(add)){
			return enterpriseVO;
		}else{
			//从列表页进编辑页或保存
			//序列化
			byte[] data = SerializUtils.serializ(vo);  
			String selectKey2 = Base64.encode(data);
			return selectKey2;
		}
	}

	/**
	 *保存政企审核结果
	 * @param  request 请求实例
	 * @param  corpInfoList 使用对象类
	 * @param  userId 使用用户ID
	 * @throws Exception
	 */
	@Override
	public void save (HttpServletRequest request, EnterpriseVO corpInfoList, String userId) throws Exception {
		// 获得更新数据
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		Date updateTime = new Date();
		userId = corpInfoList.getUserId();
		Integer status = corpInfoList.getStatus();
		String refuseMemo = null;
		// 错误信息
		ValidateErrorException exception = null;
		// 判断审核状态
		if (status == ZzjConstants.STATUS_0 || status==null) {
			exception = new ValidateErrorException("E1000001", new Object[] {"审核状态"}, "enterprise/enterprise_edit", null);
		} else {
			// 审核状态为“拒绝”
			if (status == ZzjConstants.STATUS_2) {
				// 获取拒绝理由
				refuseMemo = corpInfoList.getRefuseMemo();
				if (StringUtil.isBlank(refuseMemo)) {
					String id = null;
					if (corpInfoList.getRefuseMemoType() == ZzjConstants.REFUSE_MEMO_TYPE_1) {
						id = "sel";
					} else {
						id = "refuseMemo2";
					}
					exception = new ValidateErrorException("E1000001", new Object[] {"拒绝理由"}, "enterprise/enterprise_edit", id);
				}
			} else {
				refuseMemo = "";
			}
		}
	
		// 初期化更新的对象类
		 CorpInfo corpInfo = new EnterpriseVO();
		 corpInfo.setUserId(userId);
		 corpInfo.setStatus(status);
		 corpInfo.setRefuseMemo(refuseMemo);
		 corpInfo.setAuditId(updateId);
		 corpInfo.setAuditTime(updateTime);
		 corpInfo.setUpdateId(updateId);
		 corpInfo.setUpdateTime(updateTime);
		 
		// 根据用户ID查询用户信息
		corpInfoList = this.selectBykey(corpInfo.getUserId());
		// 显示用户信息
		request.setAttribute("corpInfoList", corpInfoList);

		if (exception != null) {
			throw exception;
		} else {
			// 更新用户信息及更新APP用户表的角色flag为政企3
			int updateInt = this.upDataByKey(corpInfo);

			// 更新数据是否成功
			if (updateInt != 0) {
				// 保存成功
				request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
			}
			corpInfoList = this.selectBykey(corpInfo.getUserId());
			request.setAttribute("corpInfoList", corpInfoList);
		}
	}

	/**
	 * 政企一览数据做成（CSV）
	 * @param  request 请求实例
	 * @param  response 返回实例
	 * @param  corpInfo 使用对象类
	 * @throws Exception
	 */
	@Override
	public void outputCSV (HttpServletRequest request, HttpServletResponse response, EnterpriseVO corpInfo) throws Exception {

		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_MSTCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> techCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);

		// 数据检索
		List<EnterpriseVO> corpInfoList = this.selectByParament(corpInfo);
		
		// 数据做成
		List<ArrayList<String>> list= new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("政企一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("用户名称");
		temp.add("领域");
		temp.add("单位性质");
		temp.add("工作单位");
		temp.add("职务/职称");
		temp.add("审核状态");
		list.add(temp);
		for (EnterpriseVO n :corpInfoList) {
			temp = new ArrayList<String>();
			// 用户名称
			temp.add(n.getUserName());
			// 领域
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f :n.getFieldCd()) {
				for (MstCodeInfo code : techCodeInfos) {
					if (f.getTechFieldCd().equals(code.getCode())) {
						sb.append(code.getCodeName());
					}
				}
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					for (MstCodeInfo code : rishCodeInfos) {
						if (f.getRschFieldCd().equals(code.getCode())) {
							sb.append(code.getCodeName());
						}
					}
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			// 单位性质
			StringBuffer strCP = new StringBuffer();
			for (MstCodeInfo code : mstCodeInfos) {
				if (ZzjConstants.COEP_TYPE.equals(code.getCodeType()) && n.getCorpType().equals(code.getCode())) {
					strCP.append(code.getCodeName());
				}
			}
			temp.add(strCP.toString());
			// 工作单位
			temp.add(n.getCompany());
			// 职务/职称
			temp.add(n.getRank());
			// 审核状态
			StringBuffer sb1 = new StringBuffer();
			String sb2 = n.getStatus().toString();
			for (MstCodeInfo code : mstCodeInfos) {
				if (ZzjConstants.AUDIT_STATUS.equals(code.getCodeType()) && sb2.equals(code.getCode())) {
					sb1.append(code.getCodeName());
				}
			}
			temp.add(sb1.toString());
			
			list.add(temp);
		}
		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_ENTERPRISE + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, ZzjConstants.TMP_PATH, title);
		// 文件导出
		CSVUtils.exportToExcel(ZzjConstants.TMP_PATH + File.separator + title, response, false);
	}

	/**
	 * 跳转到政企一览页面。<br/>
	 * @param  request 请求实例
	 * @param  corpInfo 使用的对象类
	 * @return 无
	 * @throws Exception
	 */
	@Override
	public void toEnterprise (HttpServletRequest request, EnterpriseVO corpInfo) throws Exception {
		
		// 设定页码
		String doSearch = request.getParameter("doSearch");
		String pageNo = request.getParameter("needsPageNo");
		// 分页数据查询整合
		if (StringUtil.isNotBlank(doSearch) || StringUtil.isNotBlank(pageNo)) {

			pageNo = request.getParameter("pageNo");
			doSearch = request.getParameter("doSearch");
			
			// 构造分页页码信息
			if (!StringUtil.isNotBlank(doSearch)) {
				pageNo = request.getParameter("needsPageNo");
			}
			if (StringUtil.isNotBlank(pageNo)) {
				corpInfo.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
				corpInfo.setPageNo(Integer.valueOf(pageNo));
				corpInfo.setDbIndex((Integer.valueOf(pageNo) - 1) * corpInfo.getPageSize());
			}
			else {
				corpInfo.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
				corpInfo.setDbIndex(0);
				corpInfo.setPageNo(1);
			}

			request.setAttribute("needsPageNo", pageNo);
			// 查询分页数据
			PageResult<EnterpriseVO> resultList = this.searchPagging(corpInfo);
			// 结果显示
			request.setAttribute("resultList", resultList);
			
			// 查询无数据
			if (resultList.getItems() == null || resultList.getItems().size() == 0)
			{
				request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
			}
		}
		request.setAttribute("doSearch", "0");
		// 获取省份对应的城市信息
		corpInfo.setAreaInfosCityC(this.getCityC1(corpInfo));
		// 选项回显
		request.setAttribute("info", corpInfo);
		
	}

}

