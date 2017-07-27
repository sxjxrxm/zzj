/**
 * Project Name:zzj-web
 * File Name:AppUserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zzj.db.blo.CourseInfoBlo;
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.MsgCustomBlo;
import com.zzj.db.blo.MsgFaceBlo;
import com.zzj.db.blo.MsgImageBlo;
import com.zzj.db.blo.MsgLogBlo;
import com.zzj.db.blo.MsgSoundBlo;
import com.zzj.db.blo.MsgTextBlo;
import com.zzj.db.dto.MsgCustom;
import com.zzj.db.dto.MsgFace;
import com.zzj.db.dto.MsgImage;
import com.zzj.db.dto.MsgLog;
import com.zzj.db.dto.MsgSound;
import com.zzj.db.dto.MsgText;
import com.zzj.manage.service.QcloudIMService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;
/**
 * <p><strong>类名: </strong></p>QcloudIMServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>腾讯云 云通信 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午8:00:57  <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class QcloudIMServiceImpl implements QcloudIMService {

	
	/**
	 * 消息记录主表操作类
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
	 * 课堂操作类
	 */
	@Autowired
	private CourseInfoBlo courseInfoBlo;
	/**
	 * 专家操作类
	 */
	@Autowired
	private ExpertInfoBlo expertInfoBlo;
	/**
	 * 自定义消息操作类
	 */
	@Autowired
	private MsgCustomBlo msgCustomBlo;
	
	
	
	/**
	 * 腾讯云 云通信 群消息发送前回调
	 * @param SdkAppid APP在云通讯申请的Appid
	 * @param CallbackCommand 固定为：Group.CallbackBeforeSendMsg。
	 * @param contenttype 固定为：json。
	 * @param ClientIP 客户端IP地址。
	 * @param OptPlatform 客户端平台。对应不同的平台类型，可能的取值有：
						  RESTAPI（使用REST API发送请求）、Web（使用Web SDK发送请求）、
						  Android、iOS、Windows、Mac、Unkown（使用未知类型的设备发送请求）。
	 * @param msgLogVO 聊天记录
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Object callbackBefore(String SdkAppid, String CallbackCommand, String contenttype, String ClientIP,
			String OptPlatform, Map<String, Object> postMap){
		try{
			//获取appid
			String appId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_SDKAPPID);
			//appId对不上
			if(!appId.equals(SdkAppid)){
				//设置错误信息
				JSONObject data = new JSONObject();
				data.put("ActionStatus", ZzjConstants.ACTION_STATUS_OK);
				data.put("ErrorInfo", "");
				data.put("ErrorCode", ZzjConstants.ERROR_COED_1);
				return data;
			}
			//设置主表参数
			MsgLog msgLog = new MsgLog();
			String userId = (String)postMap.get("From_Account");
			String groupId = (String)postMap.get("GroupId");
			msgLog.setFromAccount(userId);
			msgLog.setGroupId(groupId);
			msgLog.setClientIp(ClientIP);
			msgLog.setOptPlatform(OptPlatform);
			//插入主键
			Long mid = msgLogBlo.insertSelective(msgLog);
			//获取消息体
			List<Map<String, Object>> MsgBodys = (List<Map<String, Object>>)postMap.get("MsgBody");
			//计数(记录顺序)
			int msgSeq = 0;
			//文本
			MsgText msgText;
			//表情
			MsgFace msgFace;
			//图片
			MsgImage msgImage;
			//语音
			MsgSound msgSound;
			//自定义消息
			MsgCustom msgCustom;
			//循环消息体
			for(Map<String, Object> MsgBody : MsgBodys){
				msgSeq++;
				//获取消息类型
				String MsgType = (String)MsgBody.get("MsgType");
				//获取消息内容
				Map<String, Object> MsgContent = (Map<String, Object>)MsgBody.get("MsgContent");
				//文本消息
				if("TIMTextElem".equals(MsgType)){
					//设置文本消息
					msgText = new MsgText();
					msgText.setMid(mid);
					msgText.setMsgSeq(msgSeq);
					msgText.setText((String)MsgContent.get("Text"));
					//插入文本消息
					msgTextBlo.insertSelective(msgText);
				}
				//表情消息
				if("TIMFaceElem".equals(MsgType)){
					//设置表情消息
					msgFace = new MsgFace();
					msgFace.setMid(mid);
					msgFace.setFaceIndex((Integer)MsgContent.get("Index"));
					msgFace.setFaceData((String)MsgContent.get("Data"));
					msgFace.setMsgSeq(msgSeq);
					//插入表情
					msgFaceBlo.insertSelective(msgFace);
				}
				//图片
				if("TIMImageElem".equals(MsgType)){
					//获取参数
					String uuid = (String)MsgContent.get("UUID");
					Integer imageFormat = (Integer)MsgContent.get("ImageFormat");
					//后缀
					String suffix = "";
					//生成相对路径
					String path = StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_CHAT_IMG, 
															ZzjConstants.BUSI_TYPE_04, groupId);
					//本地地址
					String localPath = StringUtil.getLocalUploadPath(ZzjConstants.UPLOAD_TYPE_CHAT_IMG, 
							ZzjConstants.BUSI_TYPE_04, groupId);
					//目录不存在则新建
					File localFile = new File(localPath);
					//不存在,新建
					if(!localFile.exists()){
						localFile.mkdirs();
					}
					switch (imageFormat) {
					//BMP=1
					case ZzjConstants.NUM_I_1:
						suffix = ".bmp";
						break;
					//JPG
					case ZzjConstants.NUM_I_2:
						suffix = ".jpg";
						break;
					//GIF
					case ZzjConstants.NUM_I_3:
						suffix = ".gif";
						break;
					//其他
					default:
						suffix = ".png";
						break;
					}
					//设置图片消息
					msgImage = new MsgImage();
					msgImage.setMid(mid);
					msgImage.setUuid(uuid);
					msgImage.setImageformat(imageFormat);
					msgImage.setMsgSeq(msgSeq);
					//获取图片集合
					List<Map<String, Object>> ImageInfoArray = (List<Map<String, Object>>)MsgContent.get("ImageInfoArray");
					//循环图片集合
					for(Map<String, Object> ImageInfo : ImageInfoArray){
						//获取类型
						Integer type = (Integer)ImageInfo.get("Type");
						//原图
						if(ZzjConstants.NUM_I_1 == type){
							msgImage.setOriginalSize((Integer)ImageInfo.get("Size"));
							msgImage.setOriginalWidth((Integer)ImageInfo.get("Width"));
							msgImage.setOriginalHeight((Integer)ImageInfo.get("Height"));
							String filePath = path + uuid + "_1" + suffix;
							msgImage.setOriginalUrl(filePath);
							String localFilePath = localPath + uuid + "_1" + suffix;
							//下载到本地
							this.download(new File(localFilePath), (String)ImageInfo.get("URL"));
						}
						//大图
						if(ZzjConstants.NUM_I_2 == type){
							msgImage.setBigSize((Integer)ImageInfo.get("Size"));
							msgImage.setBigWidth((Integer)ImageInfo.get("Width"));
							msgImage.setBigHeight((Integer)ImageInfo.get("Height"));
							String filePath = path + uuid + "_2" + suffix;
							msgImage.setBigUrl(filePath);
							String localFilePath = localPath + uuid + "_2" + suffix;
							//下载到本地
							this.download(new File(localFilePath), (String)ImageInfo.get("URL"));
						}
						//小图
						if(ZzjConstants.NUM_I_3 == type){
							msgImage.setSmallSize((Integer)ImageInfo.get("Size"));
							msgImage.setSmallWidth((Integer)ImageInfo.get("Width"));
							msgImage.setSmallHeight((Integer)ImageInfo.get("Height"));
							String filePath = path + uuid + "_3" + suffix;
							msgImage.setSmallUrl(filePath);
							String localFilePath = localPath + uuid + "_3" + suffix;
							//下载到本地
							this.download(new File(localFilePath), (String)ImageInfo.get("URL"));
						}
					}
					//插入图片
					msgImageBlo.insertSelective(msgImage);
				}
				//语音
				if("TIMSoundElem".equals(MsgType)){
//					//生成相对地址
//					String path = StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_CHAT_AUDIO,
//												ZzjConstants.BUSI_TYPE_04, groupId);
					//设置语音消息
					msgSound = new MsgSound();
					msgSound.setMid(mid);
					msgSound.setUuid((String)MsgContent.get("UUID"));
					msgSound.setSize((Integer)MsgContent.get("Size"));
					msgSound.setSecond((Integer)MsgContent.get("Second"));
					msgSound.setMsgSeq(msgSeq);
					
					//插入语音
					msgSoundBlo.insertSelective(msgSound);
				}
				
			}
			msgSeq++;
			//设置返回信息
			JSONObject data = new JSONObject();
			data.put("ActionStatus", ZzjConstants.ACTION_STATUS_OK);
			data.put("ErrorInfo", "");
			data.put("ErrorCode", ZzjConstants.ERROR_COED_0);
			//设置查询参数
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("speaker", userId);
			maps.put("roomId", groupId);
			//查询是否是主持人
			Integer isSpeaker = courseInfoBlo.selectSpeakerByUserIdAndRoomId(maps);
			//是主持人
			if(null != isSpeaker && ZzjConstants.NUM_I_1 == isSpeaker){
				//设置自定义消息
				Map<String, Object> pMap2 = new HashMap<String, Object>();
				pMap2.put("Desc", "CustomElement.Speaker");
				pMap2.put("Data", "主持人");
				Map<String, Object> pMap1 = new HashMap<String, Object>();
				pMap1.put("MsgType", "TIMCustomElem");
				pMap1.put("MsgContent", pMap2);
				MsgBodys.add(pMap1);
				//设置自定义消息
				msgCustom = new MsgCustom();
				msgCustom.setMid(mid);
				msgCustom.setCustomData("主持人");
				msgCustom.setCustomDesc("CustomElement.Speaker");
				msgCustom.setMsgSeq(msgSeq);
				//插入数据库
				msgCustomBlo.insertSelective(msgCustom);
				//追加map
				Map<String, List<Map<String, Object>>> returnMaps = new HashMap<String, List<Map<String, Object>>>();
				returnMaps.put("MsgBody", MsgBodys);
				data.putAll(returnMaps);
			}else{
				//不是主持人
				//查询是否是专家
				maps.put("deleteFlag", ZzjConstants.DELETE_FLAG_0);
				maps.put("expertId", userId);
				Integer isExpert = expertInfoBlo.selectIsExpertById(maps);
				//是专家
				if(null != isExpert && ZzjConstants.NUM_I_1 == isExpert){
					//设置自定义消息
					Map<String, Object> pMap2 = new HashMap<String, Object>();
					pMap2.put("Desc", "CustomElement.Expert");
					pMap2.put("Data", "专家");
					Map<String, Object> pMap1 = new HashMap<String, Object>();
					pMap1.put("MsgType", "TIMCustomElem");
					pMap1.put("MsgContent", pMap2);
					MsgBodys.add(pMap1);
					//设置自定义消息
					msgCustom = new MsgCustom();
					msgCustom.setMid(mid);
					msgCustom.setCustomData("专家");
					msgCustom.setCustomDesc("CustomElement.Expert");
					msgCustom.setMsgSeq(msgSeq);
					//插入数据库
					msgCustomBlo.insertSelective(msgCustom);
					//追加map
					Map<String, List<Map<String, Object>>> returnMaps = new HashMap<String, List<Map<String, Object>>>();
					returnMaps.put("MsgBody", MsgBodys);
					data.putAll(returnMaps);
				}
			}
			return data;
		}catch(Exception e){
			//设置错误信息
			JSONObject data = new JSONObject();
			data.put("ActionStatus", ZzjConstants.ACTION_STATUS_OK);
			data.put("ErrorInfo", "");
			data.put("ErrorCode", ZzjConstants.ERROR_COED_1);
			return data;
		}
	}
	
	/**
	 * 下载语音并保存到本地
	 * @param URL 语音地址
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void downloadSound(String url, String groupId, String uuid) throws Exception{
		//生成相对路径
		String path = StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_CHAT_AUDIO, 
												ZzjConstants.BUSI_TYPE_04, groupId);
		//本地地址
		String localPath = StringUtil.getLocalUploadPath(ZzjConstants.UPLOAD_TYPE_CHAT_AUDIO, 
				ZzjConstants.BUSI_TYPE_04, groupId);
		//目录不存在则新建
		File localFile = new File(localPath);
		//不存在,新建
		if(!localFile.exists()){
			localFile.mkdirs();
		}
		String localFilePath = localPath + uuid + ".mp3";
		String filePath = path + uuid + ".mp3";
		this.download(new File(localFilePath), url);
		//设置参数 TODO 空指针异常
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("uuid", uuid);
		maps.put("groupId", groupId);
		//插入语音
		MsgSound msgSound = msgSoundBlo.selectByUuidAndGroupId(maps);
		//设置地址
		msgSound.setAddress(filePath);
		//修改地址
		msgSoundBlo.updateByPrimaryKeySelective(msgSound);
	}
	
	/**
	 * 下载图片/语音
	 * @param  变量名，做什么用的
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	private void download(File file, String urlString) throws Exception{
		 // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    OutputStream os = new FileOutputStream(file);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    os.flush();
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	}
	
	public static void main(String[] args) throws Exception {
//		String path = "Z:/chat/img/2016/12/20/aaasss.jpg";
//		File f = new File("Z:/chat/img/2016/12/20/");
//		if(!f.exists()){
//			f.mkdirs();
//		}
//		File file = new File(path);
//		String urlString = "http://t11.baidu.com/it/u=329730859,750555821&fm=76";
//		QcloudIMServiceImpl impl = new QcloudIMServiceImpl();
//		impl.download(file, urlString);
		
		String urlString = "http://123.151.76.28/asn.com/stddownload_common_file?authkey=3043020101043c303a02010102010102040c9b83ca02037a13f502041c4c977b02041c4c977b02037a1afd02041216a3b402041716a3b4020458638734020451f80df60400&bid=10001&subbid=1400001533&fileid=305c02010004553053020100041231343431313531393838303933393530353302037a1afd02042116a3b40204585ba05104233135373637303934333836333035343431385fe416f8e7ed3e976ab9221816e0dc99470201000201000400&filetype=2106&openid=86-18612413647&ver=0";
		QcloudIMServiceImpl impl = new QcloudIMServiceImpl();
		File sound = new File("D:/zzj/sound.mp3");
		impl.download(sound, urlString);
	}
	
}

