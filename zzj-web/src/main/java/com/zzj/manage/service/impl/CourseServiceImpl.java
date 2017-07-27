/**
 * Project Name:zzj-web
 * File Name:UserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.CourseInfoBlo;
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.MsgFaceBlo;
import com.zzj.db.blo.MsgImageBlo;
import com.zzj.db.blo.MsgLogBlo;
import com.zzj.db.blo.MsgSoundBlo;
import com.zzj.db.blo.MsgTextBlo;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.blo.MstSequenceInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.MsgLog;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.CourseInfoVO;
import com.zzj.db.model.CourseMsgVO;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.CourseService;
import com.zzj.manage.service.HtmlService;
import com.zzj.util.Base64;
import com.zzj.util.DateUtil;
import com.zzj.util.HttpUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>CourseServiceImpl业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>课堂管理业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月09日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class CourseServiceImpl implements CourseService {

	/**
	 * 业务数据库操作类
	 */
	@Autowired
	private CourseInfoBlo courseInfoBlo;
	
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	
	/**
	 * 推荐置顶模块业务数据库操作类
	 */
	@Autowired
	private RecommendInfoBlo recommendInfoBlo;
	
	/**
	 * 系统领域模块业务数据库操作类
	 */
	@Autowired
	private MstFieldInfoBlo mstFieldInfoBlo;
	
	/**
	 *取得主键用blo
	 */
	@Autowired
	private MstSequenceInfoBlo  mstSequenceInfoBlo;
	
	/**
	 * 专家模块业务数据库操作类
	 */
	@Autowired
	private ExpertInfoBlo expertInfoBlo;
	
	/**
	 * H5页面生成服务
	 */
	@Autowired
	private HtmlService htmlService;
	
	/**
	 * 课堂信息表数据库操作类
	 */
	@Autowired
	private MsgLogBlo msgLogBlo;
	/**
	 * 消息记录文本操作类
	 */
	@Autowired
	private MsgTextBlo msgTextBlo;
	
	/**
	 * 消息记录表情操作类
	 */
	@Autowired
	private MsgFaceBlo msgFaceBlo;
	
	/**
	 * 消息记录图片操作类
	 */
	@Autowired
	private MsgImageBlo msgImageBlo;
	
	/**
	 * 消息记录语音操作类
	 */
	@Autowired
	private MsgSoundBlo msgSoundBlo;
	
	/**
	 * 专家模块业务数据库操作类
	 */
	@Autowired
	private CommonServiceImpl common;	
	
	/**
	 * 取得全部课堂记录
	 * @param Map<String, Object> record 画面表示用需求记录
	 * @return List<CourseInfoVO> 课堂列表
	 */
	@Override
	public List<CourseInfoVO> searchAll(Map<String, Object> record)  {
		
		List<CourseInfoVO> list = courseInfoBlo.selectAll(record);
		
		for(CourseInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_04);
			map1.put("topicCd", infoVO.getCourseCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);
			
			// 获取对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			infoVO.setRecommend(recommendInfo);
			
		}
		return list;
	}
	/**
	 * 分页取得课堂记录
	 * @param Map<String, Object> 画面表示用课堂记录
	 * @return PageResult<CourseInfoVO> 课堂列表
	 */
	@Override
	public PageResult<CourseInfoVO> searchPagging(Map<String, Object> record)  {
		
		Map<String, Object> queryMap = (record == null) ? new HashMap<String, Object>() : record;
		Integer totalCount = courseInfoBlo.selectTotalCount(queryMap);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<CourseInfoVO>();
		}
		int dbIndex = (int) queryMap.get("dbIndex");
		// 根据总记录数重新校验当前页，当尾页只有一条记录，且执行删除操作时，此时保存的当前页不合法，需要进行减一操作
		if (dbIndex >= totalCount) {
			int flag = totalCount % ZzjConstants.DEFAULT_PAGESIZE;// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / ZzjConstants.DEFAULT_PAGESIZE;
			int pageNo = (flag == 0) ? num : num + 1;
			queryMap.put("pageNo", pageNo);
			queryMap.put("dbIndex", (pageNo - 1) * ZzjConstants.DEFAULT_PAGESIZE);
		}
		if (dbIndex < 0)
		{
			queryMap.put("pageNo", 1);
			queryMap.put("dbIndex", 0);
		}

		List<CourseInfoVO> list = courseInfoBlo.selectPagging(queryMap);
		
		for(CourseInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_04);
			map1.put("topicCd", infoVO.getCourseCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);
			
			// 获取对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			infoVO.setRecommend(recommendInfo);			
		}
	
		/**
		 * 构造分页结果集
		 */
		PageResult<CourseInfoVO> pageResult = new PageResult<CourseInfoVO>();
		dbIndex = (int) queryMap.get("dbIndex");
		pageResult.setPageNo(dbIndex / ZzjConstants.DEFAULT_PAGESIZE + 1);
		pageResult.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
		int flag = totalCount % ZzjConstants.DEFAULT_PAGESIZE;// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / ZzjConstants.DEFAULT_PAGESIZE;
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	
	/**
	 * 根据主键删除记录
	 * @param CourseInfoVO record 更新信息
	 * @return int 更新结果条目数
	 */
	@Override
	public int delete(CourseInfoVO record) {
		record.setDeleteFlag(1);		
		return courseInfoBlo.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 关闭课堂。
	 * @param courseCd 主键
	 * @return int 执行结果
	 * @throws Exception 
	 */
	@Override
	public int closeCourse(CourseInfoVO record) throws Exception {		
		// 关闭课堂
		String ret = this.createOrDestroyCourse(record);
		if (ZzjConstants.SUCCESS.equals(ret))
		{
			// 更新课堂信息
			record.setCourseType(ZzjConstants.COURSE_TYPE_1);
			return courseInfoBlo.updateByPrimaryKeySelective(record);
		}
		else
		{
			return -1;
		}
	}
	
	/**
	 * 获得导出内容
	 * @param List<CourseInfoVO> resultList 课堂一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContent(List<CourseInfoVO> resultList, List<MstCodeInfo> mstCodeInfos)  {
		
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("课堂一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("课堂主题");
		temp.add("课堂分类");
		temp.add("费用");
		temp.add("领域");
		temp.add("e课堂日期");
		temp.add("参与人数");
		list.add(temp);
		for (CourseInfoVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getCourseName());
			temp.add(n.getCourseTypeDisp());
			temp.add(n.getPaymentType());
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f :n.getFieldCd()) {
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			temp.add(DateUtil.getDate(n.getStartTime()));
			temp.add(n.getScanCount().toString());
			list.add(temp);
		}
		
		return list;
	}
	
	/**
	 * 根据CODE返回CODENAME<br/>
	 * @param List<MstCodeInfo> list 编码列表
	 * @param String codeType 类型
	 * @param String codeCd 编码
	 * @return MstCodeInfo 查询结果记录
	 */
	private MstCodeInfo getCodeName(List<MstCodeInfo> list, String codeType, String codeCd) {
		for (MstCodeInfo code : list) {
			if (codeType.equals(code.getCodeType()) && codeCd.equals(code.getCode()))
			{
				return code;
			}
		}
		return new MstCodeInfo();
	}
	
	/**
	 * 刷新画面
	 * @param HttpServletRequest request 请求实例
	 * @param String courseCd 课堂编码
	 * @throws Exception 
	 */
	@Override
	public void load(HttpServletRequest request, String courseCd) throws Exception {
		CourseInfoVO courseInfo = null;
		if (StringUtil.isNotBlank(courseCd)) {
			// 数据查询
			courseInfo = this.selectByPrimaryKey(courseCd);
		}
		else {
			courseInfo = new CourseInfoVO();
			courseInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(null));
		}
		request.setAttribute("courseInfo", courseInfo);
	}
	
	/**
	 * 根据主键查询表记录<br/>
	 * @param  courseCd 主键
	 * @return CourseInfoVO 课堂信息
	 * @throws Exception 
	 */
	@Override
	public CourseInfoVO selectByPrimaryKey(String courseCd) throws Exception {

		CourseInfoVO courseInfo = courseInfoBlo.selectByPrimaryKey(courseCd);
		
		if (courseInfo == null) {
			courseInfo = new CourseInfoVO();
			courseInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(null));
			return courseInfo;
		}
		
		// 通过ID获取名称
		courseInfo.setSpeakerOld(courseInfo.getSpeaker());
		courseInfo.setSpeaker(this.getExpertName(courseInfo.getSpeaker()));
		
		// 取得图片URL链接
		if (StringUtil.isNotBlank(courseInfo.getLittleIcon())) {
			courseInfo.setLittleIconUrl(StringUtil.getImageURL(courseInfo.getLittleIcon()));
		}
		if (StringUtil.isNotBlank(courseInfo.getBigIcon())) {
			courseInfo.setBigIconUrl(StringUtil.getImageURL(courseInfo.getBigIcon()));
		}
		
		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_04);
		map.put("topicCd", courseInfo.getCourseCd());
		
		// 获取对应的推荐置顶信息
		List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map);
		if(recommendInfo == null) {
			recommendInfo = new ArrayList<RecommendInfoKey>();
		} else {
			StringBuffer sb = new StringBuffer();
			for (RecommendInfoKey key : recommendInfo) {
				sb.append(key.getRecommendKbn());
			}
			// 可能的结果： (1,2,)  (1,)  (2,)，方便前台使用判断
			courseInfo.setRecommendKbn(sb.toString());
			if (sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1)) {
				// 改专家已被推荐，需要查询推荐语
				courseInfo.setRecommendMsg(recommendInfoBlo.findRecommendMsgByTypeAndCode(map));
			}
		}
		
		// 获取对应领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		courseInfo.setFieldCd(topicFieldInfo);
		// 获得不属于该课堂的领域
		courseInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(topicFieldInfo));
		
		return courseInfo;
	}
	
	/**
	 * 获得不属于该课堂的领域
	 * @param List<TopicFieldInfoKey> 属于该课堂的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该课堂的领域
	 */
	@Override
	public List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo)  {
		// 取得全部领域
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		if (topicFieldInfo == null)
		{
			return mstFieldInfo;
		}
	    // 获得不属于该课堂的领域
		List<TopicFieldInfoKey> otherTopicFieldInfo = new ArrayList<TopicFieldInfoKey>();
		boolean isContains = false;
		for (TopicFieldInfoKey m :mstFieldInfo) {
			isContains = false;
			for (TopicFieldInfoKey t : topicFieldInfo) {
				if (m.getTechFieldCd().equals(t.getTechFieldCd()) && 
						(m.getRschFieldCd().equals(t.getRschFieldCd()) || ZzjConstants.BLANK.equals(t.getRschFieldCd()))) {
					isContains = true;
				}
			}
			if (!isContains) {
				otherTopicFieldInfo.add(m);
			}
		}
		return otherTopicFieldInfo;
	}
	
	/**
	 * 刷新在线课堂页面。
	 * @param HttpServletRequest request 请求实例
	 * @param String courseCd 课堂编码
	 * @throws Exception 
	 */
	@Override
	public void loadLiveCourse(HttpServletRequest request, String courseCd) throws Exception {
		CourseInfoVO courseInfo = null;
		if (StringUtil.isNotBlank(courseCd)) {
			// 数据查询
			courseInfo = courseInfoBlo.selectByPrimaryKey(courseCd);
		}
		if (courseInfo == null || Objects.isNull(courseInfo.getCourseType())) {
			return;
		}
		/**
		 * 当前课堂
		 */
		// 当前用户的userId 和 userSig
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		request.setAttribute("userSig", common.checkUserSig(user.getUserId()));
		request.setAttribute("userId", user.getUserId());
		if (courseInfo.getCourseType() == ZzjConstants.COURSE_TYPE_0) { // 0：当前课堂，1：以往课堂
			// 1.查看课堂是否存在
			Map<String, Object> map = this.checkCourseExist(request, courseInfo);
			boolean isExist = (boolean) map.get("isExist");
			
			String ownerAccount = (String) map.get("ownerAccount");
			request.setAttribute("isExist", isExist);
			request.setAttribute("ownerAccount", ownerAccount);
			if (!isExist || !courseInfo.getUpdateId().equals(ownerAccount)) {
				if (!isExist) {
					request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000009", null));
				} else if (!courseInfo.getUpdateId().equals(ownerAccount)) {
					request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000010", null));
				}
			}
		}
		
		/**
		 * 以往课堂
		 */
		if (courseInfo.getCourseType() == ZzjConstants.COURSE_TYPE_1) { // 0：当前课堂，1：以往课堂
			// 异步请求获取数据
		}
		request.setAttribute("courseInfo", courseInfo);
	}
	
	/**
	 * 根据groupId查看当前课堂是否存在
	 * @param  request 请求实例
	 * @param  courseInfo 课堂信息
	 * @return boolean类型  查看当前课堂是否存在
	 * @throws Exception
	 */
	private Map<String, Object> checkCourseExist(HttpServletRequest request, CourseInfoVO courseInfo) throws Exception {
		// 请求参数
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isBlank(courseInfo.getRoomId())) {
			map.put("isExist", false);
			map.put("ownerAccount", "");
			return map;
		}
		
		JSONObject groupIdList = new JSONObject();
		groupIdList.put("GroupIdList", new String[]{courseInfo.getRoomId()});
		
		// 请求URL
		String url = ZzjConstants.HTTP_GET_GROUP_INFO;
				
		// APP管理员的userId 和 userSig
		String userId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
		// 取得签名
		String userSig = common.checkUserSig(userId);
		url = PropertyUtil.getHttpContent(url, new Object[] { userSig, userId, ZzjConstants.SDKAPPID_YUNTONGXUN });
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		String response = HttpUtil.post(url, groupIdList.toString(), headers);
		if (response.indexOf("OK") > 0) {// 请求发送成功
			// 获取历史聊天记录
			JSONObject json = new JSONObject(response);
			JSONArray groupInfos = json.getJSONArray("GroupInfo");
			JSONObject groupInfo = groupInfos.getJSONObject(0);
			Integer errorCode = groupInfo.getInt("ErrorCode");
			if (errorCode == 0) {// 课堂存在
				map.put("isExist", true);
				String ownerAccount = groupInfo.getString("Owner_Account");
				map.put("ownerAccount", ownerAccount);
				// 将当前用户加入到该群组
				this.addGroupMember(courseInfo.getRoomId(), (String)request.getAttribute("userId"), userId, userSig);
			} else {
				map.put("isExist", false);
				map.put("ownerAccount", "");
			}
		} else {
			map.put("isExist", false);
			map.put("ownerAccount", "");
		}
		return map;
	}
	
	/**
	 * 后台用户进入课堂时默认执行将该用户加入到群组
	 * @param groupId 房间id
	 * @param currentUserId 当前用户id
	 * @param userId app管理员的id
	 * @param userSig app管理员的签名
	 */
	private void addGroupMember(String groupId, String currentUserId, String userId, String userSig) {
		JSONObject json = new JSONObject();
		json.put("GroupId", groupId);
		
		JSONObject member = new JSONObject();
		member.put("Member_Account", currentUserId);
		
		JSONArray memberList = new JSONArray();
		memberList.put(member);
		
		json.put("MemberList", memberList);
		
		// 请求URL
		String url = ZzjConstants.HTTP_ADD_GROUP_MEMBER;
		url = PropertyUtil.getHttpContent(url, new Object[] { userSig, userId, ZzjConstants.SDKAPPID_YUNTONGXUN });
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		String response = HttpUtil.post(url, json.toString(), headers);
		if (response.indexOf("OK") > 0) {// 请求发送成功
			//System.out.println(response);
		}
		
	}
	/**
	 * 跳转到在线课堂页面。<br/>
	 * @param  request 请求实例
	 * @param  response 响应实例
	 * @param  courseCd 课堂编码
	 * @throws Exception
	 */
	@Override
	public void showMoreMsg(HttpServletRequest request, HttpServletResponse response, String courseCd) throws Exception {
		CourseInfoVO courseInfo = null;
		if (StringUtil.isNotBlank(courseCd)) {
			// 数据查询
			courseInfo = courseInfoBlo.selectByPrimaryKey(courseCd);
		}
		if (Objects.isNull(courseInfo.getCourseType())) {
			return;
		}
		JSONObject resultList = new JSONObject();
		
		// 查询本地服务器
		// 1.根据RoomId 在表msg_log 取记录
		Map<String, Object> queryMap = new HashMap<String, Object>();

		int totalCount = msgLogBlo.selectCountByGroupId(courseInfo.getRoomId());
		if (totalCount == 0) {
			return;
		}

		int mod = totalCount % ZzjConstants.COURSE_PAGESIZE;
		int count = totalCount / ZzjConstants.COURSE_PAGESIZE;
		int totalPage = (mod == 0) ? count : (count + 1);
		int lastPageDbIndex = (totalPage - 1) * ZzjConstants.COURSE_PAGESIZE;

		queryMap.put("groupId", courseInfo.getRoomId());
		queryMap.put("pageSize", ZzjConstants.COURSE_PAGESIZE);
		String livePageNo = request.getParameter("livePageNo");
		int dbIndex = lastPageDbIndex;
		if (StringUtil.isNotBlank(livePageNo)) {
			dbIndex = (Integer.parseInt(livePageNo) - 1) * ZzjConstants.COURSE_PAGESIZE;
			if (dbIndex > totalCount && dbIndex >= ZzjConstants.COURSE_PAGESIZE) {
				dbIndex = lastPageDbIndex;
			}
		}
		queryMap.put("dbIndex", dbIndex); // 默认从最后一页显示数据

		List<MsgLog> msgLogs = msgLogBlo.selectByGroupIdAndPage(queryMap);

		int flag = totalCount % ZzjConstants.COURSE_PAGESIZE;
		int num = totalCount / ZzjConstants.COURSE_PAGESIZE;
		int totalPageCount = flag == 0 ? num : num + 1;
		
		// 2.遍历结果集 根据表msg_log 的id ->mid从表msg_text msg_sound msg_image
		// msg_face四张表中取数据
		resultList = this.createMsgList(msgLogs, totalPageCount, dbIndex);
				
		// 将结果返回页面
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.println(resultList);
			out.flush();
		}catch(IOException e){
			e.printStackTrace();  
		}finally {
			 if (null != out) {  
				 out.close();  
			 } 
		}
		
	}
	

	/**
	 * 遍历结果集 根据表msg_log 的id ->mid从表msg_text msg_sound msg_image msg_face四张表中取数据
	 * @param  List<MsgLog> msgLogs log结果集
	 * @param  totalPageCount 总页数
	 * @param  dbIndex 查询记录其实数，用于计算当前页
	 * @return JSONArray 消息结果集
	 * @throws Exception 
	 */
	private JSONObject createMsgList(List<MsgLog> msgLogs,int totalPageCount,int dbIndex) throws Exception{
		/**
		 * 请求腾讯云根据id获取用户名
		 */
		Map<String, String> ids = new HashMap<String, String>();
		JSONObject sendMsg = new JSONObject();
		JSONArray TagList = new JSONArray().put("Tag_Profile_IM_Nick");
		sendMsg.put("TagList", TagList);
		//访问腾讯云获取用户名
		for (int i = 0; i < msgLogs.size(); i++) {
			ids.put(msgLogs.get(i).getFromAccount(), msgLogs.get(i).getFromAccount());
		}
		JSONArray ToAccount = new JSONArray();
		for (Map.Entry<String, String> entry:ids.entrySet()) {
			ToAccount.put(entry.getValue());
		}
		
		sendMsg.put("To_Account", ToAccount);
		
		Map<String, String> nicks = common.getNicksByAccounts(sendMsg);
		
		/**
		 * 遍历log构造消息体
		 */
		JSONArray RspMsgList = new JSONArray();
		JSONObject msg;
		// 构造信息
		for (MsgLog log : msgLogs) {
			msg = new JSONObject();
			JSONArray MsgBodys = new JSONArray();
			
//			List<CourseMsgVO> singleMsg = new ArrayList<CourseMsgVO>();
//			CourseMsgListVO listVO = new CourseMsgListVO();
			if (Objects.isNull(log.getId())) {
				continue;
			}
			Long mid = log.getId();
			
			List<CourseMsgVO> msgTexts = msgTextBlo.selectByMid(mid);
			List<CourseMsgVO> msgSounds = msgSoundBlo.selectByMid(mid);
			List<CourseMsgVO> msgImages = msgImageBlo.selectByMid(mid);
			List<CourseMsgVO> msgFaces = msgFaceBlo.selectByMid(mid);
			
			int msgSize = msgTexts.size() + msgSounds.size() + msgImages.size() + msgFaces.size();// 记录本条消息对应CourseMsgVO实体的个数
			// 对结果集根据msg_seq 排序显示
			Map<Integer, CourseMsgVO> map = new TreeMap<Integer, CourseMsgVO>(new Comparator<Integer>(){
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				}
			});
			for (CourseMsgVO vo : msgTexts) {
				map.put(vo.getMsgSeq(), vo);
			}
			for (CourseMsgVO vo : msgSounds) {
				map.put(vo.getMsgSeq(), vo);
			}
			for (CourseMsgVO vo : msgImages) {
				map.put(vo.getMsgSeq(), vo);
			}
			for (CourseMsgVO vo : msgFaces) {
				map.put(vo.getMsgSeq(), vo);
			}
			
			for (int i = 0; i < msgSize; i++) {
				//singleMsg.add(map.get(i+1)); // MsgSeq从1开始计数，所以i+1 
				JSONObject MsgBody = new JSONObject();
				JSONObject MsgContent = new JSONObject();
				switch (map.get(i+1).getMsgType()) { // 消息类型0:text,1:image,2:face,3:sound
				case 0:
					MsgBody.put("MsgType", "TIMTextElem");
					MsgContent.put("Text", map.get(i+1).getText());
					MsgBody.put("MsgContent", MsgContent);
					break;
				case 1:
					MsgBody.put("MsgType", "TIMImageElem");
					MsgContent.put("URL", StringUtil.getImageURL(map.get(i+1).getSmallUrl()));
					MsgContent.put("UUID", map.get(i+1).getUuid());
					MsgBody.put("MsgContent", MsgContent);
					break;
				case 2:
					MsgBody.put("MsgType", "TIMFaceElem");
					MsgContent.put("Index", map.get(i+1).getFaceIndex());
					MsgBody.put("MsgContent", MsgContent);
					break;
				case 3:
					MsgBody.put("MsgType", "TIMSoundElem");
					MsgContent.put("Second", map.get(i+1).getSecond());
					MsgContent.put("Address", StringUtil.getImageURL(map.get(i+1).getAddress()));
					MsgContent.put("UUID", map.get(i+1).getUuid());
					MsgBody.put("MsgContent", MsgContent);
					break;
				default:
					break;
				}
				MsgBodys.put(MsgBody);
			}
			
			msg.put("From_Account", log.getFromAccount());// 发送者id
			msg.put("From_Account_Nick", nicks.get(log.getFromAccount()));// 用于页面判断是否是自己发送的消息
			msg.put("MsgBody", MsgBodys);
			msg.put("MsgTimeStamp", log.getMsgTimestamp().getTime() / 1000);
			msg.put("MsgSeq", log.getId());
//			listVO.setMsgs(singleMsg);
//			listVO.setMsgOwner(log.getFromAccount());
//			listVO.setMsgDate(log.getMsgTimestamp());
			
			RspMsgList.put(msg);
//			resultList.add(listVO);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("RspMsgList", RspMsgList);
		obj.put("totalPageCount", totalPageCount);
		obj.put("livePageNo", dbIndex / ZzjConstants.COURSE_PAGESIZE + 1);
		return obj;
	}
	
	/**
	 * 图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@Override
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) {
		String id = req.getParameter("id");// 用于区分列表图片（littleIcon）、详情图片（bigIcon）
		if (StringUtil.isBlank(id)) {
			return null;
		}
		Map<String, String> resultMap = new HashMap<String, String>();	
		
		// 获得请求图片
		MultipartFile multipartFile = file.getFile(id + "ImgData");
		
		// 图片验证
		String message = UploadUtils.checkLittleIconImg(multipartFile);
		if (StringUtil.isNotBlank(message))
		{
			resultMap.put("message", message);
			return resultMap;
		}		
		
		String realPath = "";	
		try {						
			// 执行图片上传
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_04);
			// web服务器存储路径
			resultMap.put("path", realPath);
			// 取得图片URL链接
			resultMap.put("url", StringUtil.getImageURL(realPath));
			resultMap.put("message", ZzjConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回图片地址
		return resultMap;
	}
	
	/**
	 * 根据主键更新记录。
	 * @param  CourseInfo record 课堂信息
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	@Override
	public int save(CourseInfoVO record) throws Exception {
		
		int result = 0;
		// 1.保存课堂信息
		if (StringUtil.isBlank(record.getCourseCd()))
		{
			// 发番
			record.setCourseCd(mstSequenceInfoBlo.selectSequenceInfo(ZzjConstants.COURSE_NO));				
			// 分享URL
			record.setShareUrl(this.getShareUrl(null, record));
			
            // 取得列表信息
			this.setCourseInfo(record);
			// 创建聊天室，获取聊天室ID
			String roomId = this.createOrDestroyCourse(record);
			if (StringUtil.isNotBlank(roomId))
			{
				record.setRoomId(roomId);
				// 设置群组标签
				common.SetGroupInfo(roomId, record.getCreateId(), ZzjConstants.SIGN_ADMINISTRATOR);
				common.SetGroupInfo(roomId, record.getSpeaker(), ZzjConstants.SIGN_SPEAKER);
				result = courseInfoBlo.insertSelective(record);
			}
			else {				
				result = -1;
			}
		}
		else
		{
            // 取得列表信息
			this.setCourseInfo(record);
			if (StringUtil.isNotBlank(record.getRoomId()))
			{
				if (!record.getSpeakerOld().equals(record.getSpeaker()))
				{
					// 设置群组标签
					common.SetGroupInfo(record.getRoomId(), record.getSpeaker(), ZzjConstants.SIGN_SPEAKER);
				}
				record.setRoomId(null);				
			}
			result = courseInfoBlo.updateByPrimaryKeySelective(record);
		}

		if (result <= 0)
		{			
			return result;
		}
		// 2.保存领域
		TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
		topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_04);
		topicFieldInfo.setTopicCd(record.getCourseCd());
		topicFieldInfo.setDeleteFlag(1);
		topicFieldInfo.setUpdateId(record.getUpdateId());
		topicFieldInfo.setUpdateTime(record.getUpdateTime());
		topicFieldInfoBlo.delete(topicFieldInfo);
		List<TopicFieldInfo> fieldInfo = record.getFieldInfo();
		if (fieldInfo != null && fieldInfo.size() > 0) {
			for (TopicFieldInfo field : fieldInfo) {
				// 根据主键查询是否存在
				TopicFieldInfo temp = topicFieldInfoBlo.selectByPrimaryKey(field);
				if (temp != null) {
					// 如果存在执行更新操作
					result = topicFieldInfoBlo.saveTopicFieldInfo(field);
				} else {
					// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
					result = topicFieldInfoBlo.addTopicFieldInfo(field);
				}
			}
		}
		
		// 3.保存推荐置顶信息
		// 3.1 先将该专家数据库中保存的推荐置顶全部改为逻辑删除状态，防止多次修改导致领域集合累加
		RecommendInfo recommend = new RecommendInfo();
		recommend.setBusiType(ZzjConstants.BUSI_TYPE_04);
		recommend.setTopicCd(record.getCourseCd());
		recommend.setDeleteFlag(1);
		recommend.setUpdateId(record.getUpdateId());
		recommend.setUpdateTime(record.getUpdateTime());
		recommendInfoBlo.deleteRecommendInfoByExpertId(recommend);
		// 3.2.1置顶消息
		RecommendInfo toTopInfo = record.getToTopInfo();
		// 先查询后操作
		if (toTopInfo != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(toTopInfo);
			if (temp != null) {
				//如果存在执行更新操作
				result = recommendInfoBlo.saveRecommendInfo(toTopInfo);
			} else {
				//如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				result = recommendInfoBlo.addRecommendInfo(toTopInfo);
			}
		}
		// 3.2.2推荐消息
		RecommendInfo recommendInfo = record.getRecommendInfo();
		// 先查询后操作
		if (recommendInfo != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(recommendInfo);
			if (temp != null) {
				// 如果存在执行更新操作
				result = recommendInfoBlo.saveRecommendInfo(recommendInfo);
			} else {
				// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				result = recommendInfoBlo.addRecommendInfo(recommendInfo);
			}
		}
		
		// 生成静态页面
		htmlService.generateCourseHtml(null, record.getCourseCd());
		
		return result;
	}
	
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @return Map<String, Object> 用于数据库查询
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectKeyMap(HttpServletRequest request, String edit) throws Exception {
		String flag = request.getParameter("flag");
		// 课堂主题
		String courseName = null;
		// 费用
		String[] paymentKbn = null;
		// 领域（技术领域）
		String[] field = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		// 课堂分类
		String[] courseType = null;
		//当前页
		String pageNo = null;
		//保存或者从编辑页返回列表页
		if(StringUtil.isNotBlank(flag) && ("0".equals(flag) || "1".equals(flag))){
			String selectKey = request.getParameter("selectKey");
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				courseName = keyVO.getCourseName();
				paymentKbn = keyVO.getPaymentKbn();
				field = keyVO.getField();
				sDate = keyVO.getsDate();
				eDate = keyVO.geteDate();
				recommendKbn = keyVO.getRecommendKbn();
				courseType = keyVO.getCourseType();
				pageNo = keyVO.getPageNo();
			}
		}else{
			courseName = request.getParameter("selectKey.courseName");
			paymentKbn = request.getParameterValues("selectKey.paymentKbn");
			field = request.getParameterValues("selectKey.field");
			sDate = request.getParameter("selectKey.sDate");
			eDate = request.getParameter("selectKey.eDate");
			recommendKbn = request.getParameterValues("selectKey.recommendKbn");
			courseType = request.getParameterValues("selectKey.courseType");
			pageNo = request.getParameter("pageNo");
		}
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		KeyVO vo = new KeyVO();
		
		// 错误信息
		ValidateErrorException exception = null;
		
		if (StringUtil.isNotBlank(courseName)) {
			queryMap.put("courseName", courseName);
			vo.setCourseName(courseName);
		}
		if (StringUtil.isNotBlank(field) && StringUtil.isNotBlank(field[0])) {
			if (field.length == 1) {
				field = field[0].replace("[", "").replace("]", "").split(", ");
			}
			if (StringUtil.isNotBlank(field[0])) {
				queryMap.put("field", Arrays.asList(field));
				vo.setField(field);
			}
		}
		if (StringUtil.isNotBlank(paymentKbn) && StringUtil.isNotBlank(paymentKbn[0])) {
			if (paymentKbn.length == 1) {
				paymentKbn = paymentKbn[0].replace("[", "").replace("]", "").split(", ");
			}
			if (StringUtil.isNotBlank(paymentKbn[0])) {
				queryMap.put("paymentKbn", Arrays.asList(paymentKbn));
				vo.setPaymentKbn(paymentKbn);
			}
		}
		if ("search".equals(edit)) {
			SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
			if (StringUtil.isNotBlank(sDate)) {
				try {
					queryMap.put("sDate", sdf.parse(sDate));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"e课堂日期(开始时间)", ZzjConstants.datePattern}, "course/course_list", "sDate");				
				}
			}
			if (StringUtil.isNotBlank(eDate)) {
				try {
					queryMap.put("eDate", sdf.parse(eDate));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"e课堂日期(结束时间)", ZzjConstants.datePattern}, "course/course_list", "eDate");				
				}
			}
		}else {
			if (StringUtil.isNotBlank(sDate)) {
				queryMap.put("sDate", sDate);
				vo.setsDate(sDate);
			}
			if (StringUtil.isNotBlank(eDate)) {
				queryMap.put("eDate", eDate);
				vo.seteDate(eDate);
			}
		}

		if (StringUtil.isNotBlank(recommendKbn) && StringUtil.isNotBlank(recommendKbn[0])) {
			if (recommendKbn.length == 1) {
				recommendKbn = recommendKbn[0].replace("[", "").replace("]", "").split(", ");
			}
			if (StringUtil.isNotBlank(recommendKbn[0])) {
				queryMap.put("recommendKbn", Arrays.asList(recommendKbn));
				vo.setRecommendKbn(recommendKbn);
			}
		}

		if (StringUtil.isNotBlank(courseType) && StringUtil.isNotBlank(courseType[0])) {
			if (courseType.length == 1) {
				courseType = courseType[0].replace("[", "").replace("]", "").split(", ");
			}
			if (StringUtil.isNotBlank(courseType[0])) {
				queryMap.put("courseType", Arrays.asList(courseType));
				vo.setCourseType(courseType);
			}
		}
		
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE;
		if (StringUtil.isNotBlank(pageNo) && Integer.parseInt(pageNo) > 0) {
			Integer ipageNo = Integer.parseInt(pageNo);
			queryMap.put("dbIndex", (ipageNo - 1) * pageSize);
			queryMap.put("pageNo", ipageNo);
			vo.setPageNo(pageNo);
		}
		else {
			queryMap.put("dbIndex", 0);
			queryMap.put("pageNo", 1);
			vo.setPageNo("1");
		}
		queryMap.put("pageSize", pageSize);

//		if ("insert".equals(edit)) {
//			String courseCd = request.getParameter("courseInfo.courseCd");
//			courseName = request.getParameter("courseInfo.courseName");
//			// 添加时设置
//			if (StringUtil.isBlank(courseCd) && StringUtil.isNotBlank(courseName)) {
//				queryMap.put("courseName", courseName);
//				vo.setCourseName(courseName);
//			}
//		}
		if(StringUtil.isNotBlank(flag) && ("1".equals(flag) || "2".equals(flag))){
			//序列化
			byte[] data = SerializUtils.serializ(vo);  
			String selectKey2 = Base64.encode(data);
			// 页面回显数据
			request.setAttribute("selectKey", selectKey2);
		}else{
			// 页面回显数据
			request.setAttribute("selectKey", queryMap);
		}
		
		if (!"search".equals(edit) || exception != null) {
			request.setAttribute("doSearch", "0");
		}
		
		// 回答列表翻页,或者,有异常发生
		if (exception != null) 
		{
			throw exception;
		}
		
		return queryMap;
	}
	
	/**
	 * 知识编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @return String 返回图片保存的url
	 * @throws Exception
	 */
	@Override
	public String textEditorImg(MultipartRequest file) throws Exception {

		MultipartFile multipartFile = file.getFile("wangEditorH5File");
		String realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_04);
		// 取得图片URL链接
		return StringUtil.getImageURL(realPath);
	}

	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request 请求实例
	 * @param pagging 是否分页
	 * @return CourseInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	@Override
	public CourseInfoVO getSaveCourseInfo(HttpServletRequest request, String pagging) throws Exception {

		// 课堂编码
		String courseCd = request.getParameter("courseInfo.courseCd");		
		// 课堂主题
		String courseName = request.getParameter("courseInfo.courseName");
		// 主讲人
		String speaker = request.getParameter("courseInfo.speaker");
		// 开讲时间
		String startTime = request.getParameter("courseInfo.startTime");
		String endTime = request.getParameter("courseInfo.endTime");		
		// 内容介绍
		String courseBrief = request.getParameter("courseInfo.courseBrief");

		// 错误信息
		ValidateErrorException exception = null;
		SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.dateTimePattern);
		
		// 保存信息作成
		CourseInfoVO record = this.setUpdateInfo(request, "");
		
		if (StringUtil.isNotBlank(courseCd)) {
			record.setCourseCd(courseCd);
		}
		else{
			// 创建者
			record.setCreateId(record.getUpdateId());
			// 创建日
			record.setCreateTime(record.getUpdateTime());
			// 默认值
			record.setCourseType(ZzjConstants.COURSE_TYPE_0);
			// 默认值
			record.setDeleteFlag(0);
		}
		if (StringUtil.isNotBlank(courseName)) {
			if (courseName.length() > 30) {
				exception = this.rebuildException(exception, "E1000005", new Object[] {"课堂主题", "30个"}, "course/course_edit", "courseName");
			}
			record.setCourseName(courseName);
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"课堂主题"}, "course/course_edit", "courseName");
		}

		// 领域
		String[] fieldCd = request.getParameterValues("fieldCd");
		if (StringUtil.isNotBlank(fieldCd)) {
			record.setField(String.join(",", fieldCd));
		}
		else {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"领域"}, "course/course_edit", "choose_sel");
		}
		
		// 主讲人名字取得
		if (StringUtil.isNotBlank(speaker)) {
			speaker = expertInfoBlo.selectIdByExpertName(speaker);
		}		
		if (StringUtil.isNotBlank(speaker)) {
			if (speaker.length() > 100) {
				exception = this.rebuildException(exception, "E1000005", new Object[] {"主讲人", "100个"}, "course/course_edit", "speaker");
			}
			record.setSpeaker(speaker);
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"主讲人"}, "course/course_edit", "speaker");
		}
		if (StringUtil.isNotBlank(startTime)) {
			try {
				record.setStartTime(sdf.parse(startTime));
			} catch (ParseException e) {				
				exception = this.rebuildException(exception, "E1000004", new Object[] {"开讲时间(开始时间)", ZzjConstants.dateTimePattern}, "course/course_edit", "startTime");				
			}
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"开讲时间(开始时间)"}, "course/course_edit", "startTime");
		}
		if (StringUtil.isNotBlank(endTime)) {
			if (endTime.compareTo(startTime) > 0) {
				try {
					record.setEndTime(sdf.parse(endTime));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"开讲时间(结束时间)", ZzjConstants.dateTimePattern}, "course/course_edit", "endTime");				
				}
			} else {
				exception = this.rebuildException(exception, "E1000007",new Object[] { "开讲时间(结束时间)" }, "course/course_edit", "endTime");
			}
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"开讲时间(结束时间)"}, "course/course_edit", "endTime");
		}
		if (courseBrief != null)
		{
			courseBrief = courseBrief.replace("<p><br></p>", "");
		}
		if (StringUtil.isNotBlank(courseBrief)) {
			record.setCourseBrief(courseBrief);
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"e课堂介绍"}, "course/course_edit", "courseBrief");
		}
		
		// 列表图片
		String littleIcon = request.getParameter("littleIcon");
		if (StringUtil.isNotBlank(littleIcon)) {
			// 将临时保存的图片文件重命名并修改图片地址字段
			record.setLittleIcon(littleIcon);
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"列表图片"}, "course/course_edit", "");
		}
		// 详情图片
		String bigIcon = request.getParameter("bigIcon");
		if (StringUtil.isNotBlank(bigIcon)) {
			// 将临时保存的图片文件重命名并修改图片地址字段
			record.setBigIcon(bigIcon);
		}
		
		// 费用
		String paymentKbn = request.getParameter("paymentKbn");
		String price = request.getParameter("price");
		if (StringUtil.isNotBlank(paymentKbn)) {
			record.setPaymentKbn(Integer.valueOf(paymentKbn));
			if (ZzjConstants.PAYMENT_KBN_1.equals(paymentKbn)) {
				if (StringUtil.isNotBlank(price)) {
					record.setPrice(new BigDecimal(price));
				}
				else
				{
					exception = this.rebuildException(exception, "E1000001", new Object[] {"费用"}, "course/course_edit", "chargeInput");
				}
			} else {
				record.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_0));
				record.setPrice(null);
			}
		}
		// 置顶
		String toTopCode = request.getParameter("toTopCode");
		if (StringUtil.isNotBlank(toTopCode)) {
			// 置顶信息
			RecommendInfo toTopInfo = new RecommendInfo();
			toTopInfo.setBusiType(ZzjConstants.BUSI_TYPE_04);
			toTopInfo.setTopicCd(record.getCourseCd());
			toTopInfo.setRecommendKbn(Integer.parseInt(toTopCode));
			if (recommendInfoBlo.isToTopRecommendCount(toTopInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				exception = this.rebuildException(exception, "E1000027", new Object[] {ZzjConstants.TO_TOP_NUM}, "course/course_edit", "toTop");
			}
		}
		// 推荐
		String recommend = request.getParameter("recommend");
		if (StringUtil.isNotBlank(recommend)) {
			// 推荐信息
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_04);
			recommendInfo.setTopicCd(record.getCourseCd());
			recommendInfo.setRecommendKbn(Integer.parseInt(recommend));
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				exception = this.rebuildException(exception, "E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM}, "course/course_edit", "recommend");
			}
		}
		// 推荐语
		String recommendMsg = request.getParameter("recommendMsg");
		if (StringUtil.isNotBlank(recommend) || StringUtil.isNotBlank(toTopCode)) {
			record.setRecommendKbn(StringUtil.nullToBlank(recommend) + ", " + StringUtil.nullToBlank(toTopCode));
		}
		if (StringUtil.isNotBlank(recommend)) {
			if (StringUtil.isNotBlank(recommendMsg)) {
				record.setRecommendMsg(recommendMsg);
			}
		}
		String roomId = request.getParameter("courseInfo.roomId");
		if (StringUtil.isNotBlank(roomId)) {
			record.setRoomId(roomId);
			record.setSpeakerOld(request.getParameter("courseInfo.speakerOld"));
		}		
		
		// 回答列表翻页,或者,有异常发生
		if (exception != null) 
		{
			// 取得图片URL链接
			if (StringUtil.isNotBlank(record.getLittleIcon())) {
				record.setLittleIconUrl(request.getParameter("littleIconUrl"));
			}
			if (StringUtil.isNotBlank(record.getBigIcon())) {
				record.setBigIconUrl(request.getParameter("bigIconUrl"));
			}
			// 取得列表信息
			this.setCourseInfo(record);
			
			request.setAttribute("courseInfo", record);
			throw exception;
		}
		
		// 前台传来的领域信息格式为："智慧城市->基础设施及网络技术,智慧城市->安全领域,智慧城市->宏观领域,智慧城市->应用领域,电子政务->宏观领域"
		String fieldNameForKeyWord = request.getParameter("fieldNameForKeyWord");
		if (StringUtil.isNotBlank(fieldNameForKeyWord)) {
			fieldNameForKeyWord = fieldNameForKeyWord.replace(",", "").replace("->", "");
		}
		StringBuffer sBuffer = new StringBuffer();
		// 关键词字段由:领域名称#课堂主题
		sBuffer.append(fieldNameForKeyWord).append("#").append(record.getCourseName());
		record.setKeyword(sBuffer.toString());
		
		return record;
	}
	/**
	 * 取得列表信息<br/>
	 * @param CourseInfoVO 用于数据库查询     
	 * @return CourseInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	private CourseInfoVO setCourseInfo(CourseInfoVO record) throws Exception {
		
		// 领域
		List<TopicFieldInfo> fieldInfo = null;
		List<TopicFieldInfoKey> fieldInfoKey = null;
		if (StringUtil.isNotBlank(record.getField()))
		{
			String[] fieldCd = record.getField().split(",");
			if (fieldCd != null && fieldCd.length > 0) {
				fieldInfo = new ArrayList<TopicFieldInfo>();
				fieldInfoKey = new ArrayList<TopicFieldInfoKey>();
				for (String code : fieldCd) {
					// 此时codes的length应该为2，如果不是2，则是垃圾数据，不予保存
					String[] codes = code.split("-");
					if (codes.length != 2) {
						continue;
					}
					TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
					topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_04);
					topicFieldInfo.setUpdateId(record.getUpdateId());
					topicFieldInfo.setUpdateTime(record.getUpdateTime());
					topicFieldInfo.setDeleteFlag(0);
					topicFieldInfo.setTopicCd(record.getCourseCd());
					topicFieldInfo.setTechFieldCd(codes[0]);
					topicFieldInfo.setRschFieldCd(codes[1]);
					fieldInfo.add(topicFieldInfo);
					fieldInfoKey.add(topicFieldInfo);
				}
			}
			record.setFieldInfo(fieldInfo);
			record.setFieldCd(fieldInfoKey);
		}
		// 获得不属于该课堂的领域
		record.setOtherFieldCd(this.getOtherTopicFieldInfo(fieldInfoKey));
		
		if (StringUtil.isNotBlank(record.getRecommendKbn())) {
			String[] str = record.getRecommendKbn().split(",");
			// 推荐
			String recommend = str[0].trim();
			// 置顶
			String toTopCode = str[1].trim();
			if (StringUtil.isNotBlank(toTopCode)) {
				// 置顶信息
				RecommendInfo toTopInfo = new RecommendInfo();
				toTopInfo.setBusiType(ZzjConstants.BUSI_TYPE_04);
				toTopInfo.setTopicCd(record.getCourseCd());
				toTopInfo.setUpdateId(record.getUpdateId());
				toTopInfo.setUpdateTime(record.getUpdateTime());
				toTopInfo.setDeleteFlag(0);
				toTopInfo.setRecommendKbn(Integer.parseInt(toTopCode));
				record.setToTopInfo(toTopInfo);
				record.setTopFlag(ZzjConstants.TOP_FLG_1);
			}
			else
			{
				record.setTopFlag(ZzjConstants.TOP_FLG_0);
			}

			if (StringUtil.isNotBlank(recommend)) {
				// 推荐信息
				RecommendInfo recommendInfo = new RecommendInfo();
				recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_04);
				recommendInfo.setTopicCd(record.getCourseCd());
				recommendInfo.setUpdateId(record.getUpdateId());
				recommendInfo.setUpdateTime(record.getUpdateTime());
				recommendInfo.setDeleteFlag(0);
				recommendInfo.setRecommendKbn(Integer.parseInt(recommend));
				recommendInfo.setRecommendMemo(record.getRecommendMsg());
				record.setRecommendInfo(recommendInfo);
			}
		}
		
		return record;
	}
	/**
	 * 设置更新信息<br/> 
	 * @param request 请求实例
	 * @param courseCd 课程编码
	 * @return CourseInfoVO
	 */
	@Override
	public CourseInfoVO setUpdateInfo(HttpServletRequest request, String courseCd) {
		
		CourseInfoVO record = new CourseInfoVO() ;
		if (StringUtil.isNotBlank(courseCd)) {
			record.setCourseCd(courseCd);
		}
		// 获得更新人的id，并创建更新时间
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		// 更新者
		record.setUpdateId(user.getUserId());
		// 更新日
		record.setUpdateTime(new Date());
		return record;
	}
	
	/**
	 * 根据专家id获得专家姓名，获取不到则直接返回
	 * @param  expertId 专家id
	 * @return String  专家姓名
	 */
	private String getExpertName(String expertId) {
		if (StringUtil.isBlank(expertId)) {
			return expertId;
		}
		String expertName = expertInfoBlo.selectNameByExpertId(expertId);
		if (StringUtil.isBlank(expertName)) {
			return expertId;
		}
		return expertName;
	}
	
	/**
	 * 根据课堂信息获得分享链接
	 * @param  request HTTP请求
	 * @param  record 课堂信息
	 * @return String  课堂分享链接
	 * @throws Exception 
	 */
	private String getShareUrl(HttpServletRequest request, CourseInfoVO record) throws Exception {
		if (record == null) {
			return null;
		}
		String shareUrl = htmlService.generateCourseHtmlUrl(record.getCourseCd());
		return shareUrl;
	}
	
	/**
	 * 创建聊天室获取聊天室ID。<br/>
	 * 解散聊天室返回成功信息。
	 * @param  record 课堂信息
	 * @return String 开启聊天室返回聊天室ID，解散聊天室返回成功信息。
	 * @throws Exception 
	 */
	private String createOrDestroyCourse(CourseInfoVO record) throws Exception {
		// 请求URL
		String url = "";
		// userId
		String userId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
		// 参数设置
		JSONObject json = new JSONObject();
		if (StringUtil.isBlank(record.getRoomId())) 
		{	// 创建群组
			url = ZzjConstants.HTTP_CREATE_COURSE;
			json.put("Owner_Account", userId);
			json.put("Type", "ChatRoom");
			json.put("Name", record.getCourseCd());	
		}
		else
		{   // 解散群组
			url = ZzjConstants.HTTP_DESTROY_COURSE;
			json.put("GroupId", record.getRoomId());
		}
		
		// 取得签名
		String userSig = common.checkUserSig(userId);
			
		url = PropertyUtil.getHttpContent(url, new Object[] {userSig, userId, ZzjConstants.SDKAPPID_YUNTONGXUN});		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		String response = HttpUtil.post(url, json.toString(), headers);
		String roomId = "";
		if (response.indexOf("OK") > 0)
		{
			if (StringUtil.isBlank(record.getRoomId())) {
				// 获取聊天室ID
				roomId = response.substring(response.lastIndexOf("@"), response.lastIndexOf("@") + 14).trim();
			}
			else {				
				roomId = ZzjConstants.SUCCESS;
			}
		}		
		return roomId;
	}
	
	/**
	 * 重构ValidateErrorException实例。<br/>
	 * @param  exception ValidateErrorException
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorItemKey 错误项目ID
	 */
	private ValidateErrorException rebuildException (ValidateErrorException exception, String errorCode, Object[] msgArgs, String errorPage, String errorItemKey) {
		if (exception == null) {
			exception = new ValidateErrorException(errorCode, msgArgs, errorPage, errorItemKey);
		} else {
			exception.addError(errorCode, msgArgs, errorItemKey);
		}
		return exception;
	}
}

