/**
 * Project Name:zzj-web
 * File Name:CommonServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.db.blo.MstUsersInfoBlo;
import com.zzj.db.blo.TokenCacheInfoBlo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.TokenCacheInfo;
import com.zzj.manage.service.CommonService;
import com.zzj.util.GsonUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;
import com.zzj.util.tls.sigature.tls_sigature;
import com.zzj.util.tls.sigature.tls_sigature.GenTLSSignatureResult;

/**
 * <p><strong>类名: </strong></p>CommonServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>共通业务服务实现类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午1:31:10 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class CommonServiceImpl implements CommonService {
	
	/**
	 * 缓存操作类
	 */
	@Autowired
	private TokenCacheInfoBlo tokenCacheInfoBlo;
	
	/**
	 * 后台用户操作类
	 */
	@Autowired
	private MstUsersInfoBlo mstUsersInfoBlo;

	/**
	 * 编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @param uploadType 上传类型  
	 * content: 内容详情（包括简介，列表图片，详情图片等）；
	 * avatar: 头像；
	 * file: 文件；
	 * other: 其他功能图片（如客服图片等）<br/>
	 * @param moduleCode 功能模块编码(业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告)
	 * @return String 文件保存相对路径 "html/02/BD/90999999/或z:/html/03/img/2016/12/18/7e215a9b2e4046e397f4d36c603d7a5d.jpg)"
	 * @throws Exception
	 */
	@Override
	public Map<String, String> textEditorImg(MultipartRequest file, String uploadType, String moduleCode) throws Exception {
		MultipartFile multipartFile = file.getFile("upfile");
		// 取得图片URL链接
		return UploadUtils.uploadEditImg(multipartFile, uploadType, moduleCode);
	}

	/**
	 * 获取用户签名
	 * @param  userId 用户名
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	public String getUserSig(String userId) throws Exception {
		//腾讯云 云通信 appid
		Long appId = Long.parseLong(PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID));
		//腾讯云 云通信 私匙
		String privStr = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_PRIVSTR);
		// generate signature
        GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(appId, userId, privStr);
//		//生成签名失败
//		if (ZzjConstants.NUM_I_0 == result.urlSig.length()) {
//            throw new ValidateErrorException(MessageConstant.MSGID_E00066, new Object[]{result.errMessage});
//        }
		return result.urlSig;
	}

	/**
	 * 判断签名是否过期,过期重新生成并插入数据库;没过期从数据库中取
	 * @param  userId 用户名
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String checkUserSig(String userId) throws Exception {
		//设置参数
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("userId", userId);
		//类型为userSig
		maps.put("cacheType", ZzjConstants.NUM_I_2);
		//超时180天
		maps.put("timeOut", ZzjConstants.NUM_I_180);
		//获取标签
		String userSig = tokenCacheInfoBlo.selectCacheByUserIdAndType(maps);
		//超时或者数据库没有对应标签
		if(StringUtil.isBlank(userSig)){
			//生成标签
			userSig = this.getUserSig(userId);
			maps.put("flag", ZzjConstants.DELETE_FLAG_0);
			//查找用户
			MstUsersInfo mstUsersInfo = mstUsersInfoBlo.getUser(userId);
//			//用户为空
//			if(null == appUsersInfo){
//				throw new ValidateErrorException(MessageConstant.MSGID_E00039, null);
//			}
			//获取参数
			String phoneNum = mstUsersInfo.getPhoneNumber();
//			//电话为空
//			if(StringUtil.isBlank(phoneNum)){
//				throw new ValidateErrorException(MessageConstant.MSGID_E00067, null);
//			}
			//设置参数
			TokenCacheInfo sigCacheInfo = new TokenCacheInfo();
			sigCacheInfo.setTokenId(userSig);
			sigCacheInfo.setPhoneNo(phoneNum);
			sigCacheInfo.setUserId(userId);
			sigCacheInfo.setCacheType(ZzjConstants.NUM_I_2);
			//插入签名
			tokenCacheInfoBlo.insertSelective(sigCacheInfo);
//			//插入失败
//			if(null == addSigNum || addSigNum != ZzjConstants.NUM_1){
//				throw new ValidateErrorException(MessageConstant.MSGID_E00064, null);
//			}
		}
		return userSig;
	}
	
	
	/**
	 * 独立模式腾讯云同步
	 * @param identifier 用户id(必填)
	 * @param nick 昵称(选填)
	 * @param faceUrl 头像(选填)
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	public void accountImport(String identifier, String nick, String faceUrl) throws Exception {
		//设置json
    	Map<String, Object> jsons = new HashMap<String, Object>();
    	jsons.put("Identifier", identifier);
    	if(!StringUtil.isBlank(nick)){
    		jsons.put("Nick", nick);
    	}
    	if(!StringUtil.isBlank(faceUrl)){
    		jsons.put("FaceUrl", StringUtil.getImageURL(faceUrl));
    	}
    	String param = GsonUtil.getJsonStringFormBean(jsons);
		//获取配置文件中的腾讯云管理员用户
    	String imUserId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
    	//获取appid
    	String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
    	//获取sig
    	String userSig = checkUserSig(imUserId);
    	//生成32位随机数
    	//Random random = new Random();
		//int randomInt = random.nextInt(89999999) + 10000000;
    	String url = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?usersig=" + userSig + 
    					"&identifier=" + imUserId + "&sdkappid=" + appId + 
    					//"&random=" + randomInt + 
    					"&contenttype=json";
    	//发送腾讯云
//    	String result = 
    	sendPost(url, param);
//    	//解析
//    	Map<String, Object> resultMap = GsonUtil.getKeyMap(result);
//    	//失败
//    	if(resultMap.get("ActionStatus").equals("FAIL")){// 
//    		throw new ValidateErrorException(PropertyUtil.getMessageContent("E1000029", new Object[]{resultMap.get("ErrorCode"), resultMap.get("ErrorInfo")}));
//    	}
    	
	}
	
	/**
	 * 设置用户资料
	 * @param identifier 用户id(必填)
	 * @param nick 昵称(选填)
	 * @param faceUrl 头像(选填)
	 * @param selfSignature 定义字段
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	public void SetAccountInfo(String identifier, String nick, String faceUrl, String selfSignature) throws Exception{
		//昵称和头像都为空
		if(StringUtil.isBlank(nick) && StringUtil.isBlank(faceUrl) && StringUtil.isBlank(selfSignature)){
			return;
		}
		//获取配置文件中的腾讯云管理员用户
    	String imUserId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
    	//获取appid
    	String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
    	//获取sig
    	String userSig = checkUserSig(imUserId);
    	//生成32位随机数
    	Random random = new Random();
		int randomInt = random.nextInt(89999999) + 10000000;
		//发送url
		String url = "https://console.tim.qq.com/v4/profile/portrait_set?usersig=" 
					+ userSig + "&identifier=" + imUserId + "&sdkappid=" + appId 
					+ "&random=" + randomInt + "&contenttype=json"; 
		//设置参数
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("From_Account", identifier);
		//设置消息体
		List<Map<String, Object>> profileItems = new ArrayList<Map<String, Object>>();
		//昵称不为空
		if(!StringUtil.isBlank(nick)){
			Map<String, Object> nickMap = new HashMap<String, Object>();
			nickMap.put("Tag", "Tag_Profile_IM_Nick");
			nickMap.put("Value", nick);
			profileItems.add(nickMap);
		}
		//头像不为空
		if(!StringUtil.isBlank(faceUrl)){
			Map<String, Object> faceMap = new HashMap<String, Object>();
			faceMap.put("Tag", "Tag_Profile_IM_Image");
			faceMap.put("Value", StringUtil.getImageURL(faceUrl));
			profileItems.add(faceMap);
		}
		//定义字段不为空
		if(!StringUtil.isBlank(selfSignature)){
			Map<String, Object> selfMap = new HashMap<String, Object>();
			selfMap.put("Tag", "Tag_Profile_IM_SelfSignature");
			selfMap.put("Value", selfSignature);
			profileItems.add(selfMap);
		}
		//加入消息体
		maps.put("ProfileItem", profileItems);
		//转成json
		String param = GsonUtil.getJsonStringFormBean(maps);
		//发送腾讯云
//    	String result = 
		sendPost(url, param);
//    	//解析
//    	Map<String, Object> resultMap = GsonUtil.getKeyMap(result);
//    	//失败
//    	if(resultMap.get(ZzjConstants.ACTION_STATUS).equals(ZzjConstants.FAIL)){
//    		throw new ValidateErrorException(MessageConstant.MSGID_E00073,
//    											new Object[]{resultMap.get(ZzjConstants.ERROR_CODE),
//    															resultMap.get(ZzjConstants.ERROR_INFO)});
//    	}
	}
	
	/**
	 * 设置群组中成员名片
	 * @param groupId 组id
	 * @param memberId 用户id
	 * @param nameCard 群名片
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	public void SetGroupInfo(String groupId, String memberId, String nameCard) throws Exception {
		//有一个为空
		if(StringUtil.isBlank(groupId) || StringUtil.isBlank(memberId) 
				|| StringUtil.isBlank(nameCard)){
			return;
		}
		//获取配置文件中的腾讯云管理员用户
    	String imUserId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
    	//获取appid
    	String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
    	//获取sig
    	String userSig = checkUserSig(imUserId);
    	//生成32位随机数
    	Random random = new Random();
		int randomInt = random.nextInt(89999999) + 10000000;
		//发送url
		String url = "https://console.tim.qq.com/v4/group_open_http_svc/modify_group_member_info?usersig="
						+ userSig + "&identifier=" + imUserId + "&sdkappid=" + appId 
						+ "&random=" + randomInt + "&contenttype=json"; 
		//设置参数
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("GroupId", groupId);
		maps.put("Member_Account", memberId);
		maps.put("NameCard", nameCard);
		//转成json
		String param = GsonUtil.getJsonStringFormBean(maps);
		//发送腾讯云
//    	String result = 
    	sendPost(url, param);
    	//解析
//    	Map<String, Object> resultMap = GsonUtil.getKeyMap(result);
    	//失败
//    	if(resultMap.get(ZzjConstants.ACTION_STATUS).equals(ZzjConstants.FAIL)){
//    		throw new ValidateErrorException(MessageConstant.MSGID_E00073,
//    											new Object[]{resultMap.get(ZzjConstants.ERROR_CODE),
//    															resultMap.get(ZzjConstants.ERROR_INFO)});
//    	}
	}
	
//	/**
//	 * 查看群组中成员的信息
//	 * @param groupId 组id
//	 * @param memberId 成员id
//	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
//	 * @throws Exception
//	 */
//	public void GetGroupInfo(String groupId, String memberId) throws Exception{
//		//获取配置文件中的腾讯云管理员用户
//    	String imUserId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
//    	//获取appid
//    	String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
//    	//获取sig
//    	String userSig = checkUserSig(imUserId);
//    	//生成32位随机数
//    	Random random = new Random();
//		int randomInt = random.nextInt(89999999) + 10000000;
//		//发送url
//		String url = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info?usersig="
//						+ userSig + "&identifier=" + imUserId + "&sdkappid=" + appId 
//						+ "&random=" + randomInt + "&contenttype=json"; 
//		//设置参数
//		Map<String, Object> maps = new HashMap<String, Object>();
//		maps.put("GroupId", groupId);
//		maps.put("MemberRoleFilter", new String[]{memberId});
//		//转成json
//		String param = GsonUtil.getJsonStringFormBean(maps);
//		//发送腾讯云
//    	String result = sendPost(url, param);
//    	//解析
//    	Map<String, Object> resultMap = GsonUtil.getKeyMap(result);
//    	System.out.println("*************" + resultMap);
//	}
	
	/**
	 * 修改群组在腾讯云的名称
	 * @param  groupId groupId
	 * @param  courseName 新的群组名称
	 * @return 无
	 * @throws Exception 
	 */
	@Override
	public void changeGroupName(String groupId, String courseName) throws Exception {
		//有一个为空
		if (StringUtil.isBlank(groupId) || StringUtil.isBlank(courseName)) {
			return;
		}
		// 获取配置文件中的腾讯云管理员用户
		String imUserId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
		// 获取appid
		String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
		// 获取sig
		String userSig = checkUserSig(imUserId);
		// 生成32位随机数
		Random random = new Random();
		int randomInt = random.nextInt(89999999) + 10000000;
		// 发送url
		String url = "https://console.tim.qq.com/v4/group_open_http_svc/modify_group_base_info?usersig=" + userSig
				+ "&identifier=" + imUserId + "&sdkappid=" + appId + "&random=" + randomInt + "&contenttype=json";
		// 设置参数
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("GroupId", groupId);
		maps.put("Name", courseName);
		// 转成json
		String param = GsonUtil.getJsonStringFormBean(maps);
		// 发送腾讯云
		// String result =
		sendPost(url, param);
		
	}
	
	
	private String sendPost(String strUrl, String param) {
		DataOutputStream out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL url = new URL(strUrl);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置通用的请求属性
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.connect();
			out = new DataOutputStream(conn.getOutputStream());
			out.write(param.getBytes("UTF-8"));// 这样可以处理中文乱码问题
			out.flush();
			// 读取响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = in.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			result = sb.toString();
			// 断开连接
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.close();
				}
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id获得用户的用户名
	 * @param  JSONObject sendMsg
	 * @return Map<String, String>
	 * @throws Exception 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> getNicksByAccounts(JSONObject sendMsg) throws Exception {
		// 有一个为空
		if (sendMsg == null) {
			return new HashMap<String, String>();
		}
		// 获取配置文件中的腾讯云管理员用户
		String imUserId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
		// 获取appid
		String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
		// 获取sig
		String userSig = checkUserSig(imUserId);
		// 生成32位随机数
		Random random = new Random();
		int randomInt = random.nextInt(89999999) + 10000000;
		// 发送url
		String url = "https://console.tim.qq.com/v4/profile/portrait_get?usersig=" + userSig
				+ "&identifier=" + imUserId + "&sdkappid=" + appId + "&random=" + randomInt + "&contenttype=json";

		// 转成json
		String param = sendMsg.toString();
		// 发送腾讯云
		String result = sendPost(url, param);
		// 解析
		Map<String, Object> resultMap = GsonUtil.getKeyMap(result);
		// 失败
		if (resultMap.get("ActionStatus").equals("FAIL")) {
			return new HashMap<String, String>();
		} else {// 成功
			Map<String, String> nicks = new HashMap<String, String>();
			
			List<Map<String, Object>> items = (List<Map<String, Object>>) resultMap.get("UserProfileItem");
			for (Map<String, Object> item : items) {
				String id = (String) item.get("To_Account");
				List<Map<String, Object>> profileItem = (List<Map<String, Object>>) item.get("ProfileItem");
				String nick = (String) profileItem.get(0).get("Value");
				
				nicks.put(id, nick);
			}
			
			return nicks;
		}
		
	}

	
}

