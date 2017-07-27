/**
 * Project Name:zzj-base
 * File Name:Constants.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p><strong>类名: </strong></p>UploadUtils <br/>
 * <p><strong>功能说明: </strong></p>上传共通<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月02日下午6:52:11 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class UploadUtils {

//	/**
//	 * 存储图片。<br/>
//	 * @param  MultipartFile mFile
//	 * @param  String module
//	 * @return String 路径
//	 * @throws Exception
//	 */
//	public static String uploadImg (MultipartFile mFile, String module) throws Exception {
//		
//		// 获取原本文件名称
//    	String fileName = generateFileName(mFile.getOriginalFilename());
//    	// 设定文件的存储路径----------------------------------------改地址-------------------------------------------
//    	String path = fileName;
//    	
//        String realPath = module + "/" + fileName;
//		if (!mFile.isEmpty()) {
//			try {
//				FileUtils.copyInputStreamToFile(mFile.getInputStream(), new File(realPath));
//				return path;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return "";
//    }
	
	/** 
	 * 文件大小验证<br/>
	 * @param file 保存的文件
	 * @param  size 大小
	 * @return boolean 返回是否正确
	 */
	public static boolean checkImgSize(MultipartFile file, Integer size) {
		size = 1024 * size;
		if (file.getSize() > size)
		{
			return false;
		}		
		return true;
	}
	
	/** 
	 * 文件尺寸验证<br/>
	 * @param file 保存的文件
	 * @param  width 宽
	 * @param  height 高
	 * @return boolean 返回是否正确
	 */
	public static boolean checkImgWH(MultipartFile file, Integer width, Integer height) {
				
		try {
			BufferedImage sourceImg = ImageIO.read(file.getInputStream());
			if (sourceImg.getWidth() != width || sourceImg.getHeight() != height)
			{
				return false;
			}
		} catch (IOException e) {			
			e.printStackTrace();			
		}
		
		return true;
	}
	
	/** 
	 * 图片验证（列表图片）<br/>
	 * 192*148，不超过200K。
	 * @param file 保存的文件
	 * @return String 信息
	 */
	public static String checkLittleIconImg(MultipartFile file) {
				
		// 图片尺寸验证
		boolean ret = UploadUtils.checkImgWH(file,	ZzjConstants.IMG_WIDTH_192, ZzjConstants.IMG_HEIGHT_148);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000020", 
					new Object[] {ZzjConstants.IMG_WIDTH_192 + "*" + ZzjConstants.IMG_HEIGHT_148});
		}
		// 图片大小验证
		ret = UploadUtils.checkImgSize(file, ZzjConstants.IMG_SIZE);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000019", new Object[] {ZzjConstants.IMG_SIZE});
		}
		return "";
	}
	
	/** 
	 * 图片验证（详细图片，背景）。<br/>
	 * 720*412，不超过200K。
	 * @param file 保存的文件
	 * @return String 信息
	 */
	public static String checkBigIconImg(MultipartFile file) {
				
		// 图片尺寸验证
		boolean ret = UploadUtils.checkImgWH(file,	ZzjConstants.IMG_WIDTH_720, ZzjConstants.IMG_HEIGHT_412);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000020", 
					new Object[] {ZzjConstants.IMG_WIDTH_720 + "*" + ZzjConstants.IMG_HEIGHT_412});
		}
		// 图片大小验证
		ret = UploadUtils.checkImgSize(file, ZzjConstants.IMG_SIZE);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000019", new Object[] {ZzjConstants.IMG_SIZE});
		}
		return "";
	}
	
	/** 
	 * 图片验证（客服二维码）<br/>
	 * 宽高一致，不大于200，不超过200K。
	 * @param file 保存的文件
	 * @return String 信息
	 */
	public static String checkQRCodeImg(MultipartFile file) {
		
		try {
			BufferedImage sourceImg = ImageIO.read(file.getInputStream());
			if (sourceImg.getWidth() > ZzjConstants.IMG_WIDTH_200 || sourceImg.getWidth() != sourceImg.getHeight())
			{
				return PropertyUtil.getMessageContent("E1000001", new Object[] {"宽高一致，不大于200的图片"});
			}
		} catch (IOException e) {			
			e.printStackTrace();			
		}
		
		// 图片大小验证
		boolean ret = UploadUtils.checkImgSize(file, ZzjConstants.IMG_SIZE);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000019", new Object[] {ZzjConstants.IMG_SIZE});
		}
		
		return "";
	}
	
	/** 
	 * 图片验证（头像）<br/>
	 * 140*140，不超过200K。
	 * @param file 保存的文件
	 * @return String 信息
	 */
	public static String checkAvatorImg(MultipartFile file) {
				
		// 图片尺寸验证
		boolean ret = UploadUtils.checkImgWH(file,	ZzjConstants.IMG_WIDTH_140, ZzjConstants.IMG_HEIGHT_140);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000020", 
					new Object[] {ZzjConstants.IMG_WIDTH_140 + "*" + ZzjConstants.IMG_WIDTH_140});
		}
		// 图片大小验证
		ret = UploadUtils.checkImgSize(file, ZzjConstants.IMG_SIZE);
		if (!ret)
		{
			return PropertyUtil.getMessageContent("E1000019", new Object[] {ZzjConstants.IMG_SIZE});
		}		
		return "";
	}
	
	/**
	 * 执行图片上传并返回临时文件名，用于保存数据
	 * 最终保存地址："z:/html/02/BD/90999999/或z:/html/03/img/2016/12/18/7e215a9b2e4046e397f4d36c603d7a5d.jpg)"
	 * 返回数据示例："html/02/BD/90999999/或z:/html/03/img/2016/12/18/7e215a9b2e4046e397f4d36c603d7a5d.jpg)"
	 * @param file 保存的文件
	 * @param uploadType 上传类型  
	 * content: 内容详情（包括简介，列表图片，详情图片等）；
	 * avatar: 头像；
	 * file: 文件；
	 * other: 其他功能图片（如客服图片等）<br/>
	 * @param moduleCode 功能模块编码(业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告)
	 * @author 任晓茂
	 * @return String 临时保存的文件名
	 * @throws Exception 
	 */
	public static String uploadImg(MultipartFile file, String uploadType, String moduleCode) throws Exception {
		// 获取原本文件名称
    	String tempFileName = getTempFileName(file.getOriginalFilename());
    	// 设定文件的存储路径
    	String relativePath = StringUtil.getUploadPath(uploadType, moduleCode, "") + tempFileName;
    	String path = PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH) + relativePath;
        
		if (!file.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return relativePath;
	}
	
	/**
	 * 执行apk上传并返回文件路径，用于保存数据
	 * @param file 保存的文件
	 * @param versionNo apk版本号
	 * @author 任晓茂
	 * @return String 保存的文件路径
	 * @throws Exception 
	 */
	public static String uploadApk(MultipartFile file, String versionNo) throws Exception {
		
		if (!file.isEmpty() && StringUtil.isNotBlank(versionNo)) {
			
			// 获取原本文件名称
			String tempFileName = versionNo + File.separator + PropertyUtil.getConfigValue(ZzjConstants.APK_NAME);
			// 设定文件的存储路径
			String path = PropertyUtil.getConfigValue(ZzjConstants.APK_PATH) + tempFileName;
			
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempFileName;
		}
		return PropertyUtil.getConfigValue(ZzjConstants.APK_NAME);
	}
	
	/**
	 * Ueditor文件上传处理
	 * @param file 保存的文件
	 * @param uploadType 上传类型  
	 * content: 内容详情（包括简介，列表图片，详情图片等）；
	 * avatar: 头像；
	 * file: 文件；
	 * other: 其他功能图片（如客服图片等）<br/>
	 * @param moduleCode 功能模块编码(业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告)
	 * @return String 临时保存的文件名
	 * @throws Exception 
	 */
	public static Map<String,String> uploadEditImg(MultipartFile file, String uploadType, String moduleCode) throws Exception {
		
        //返回结果信息(UEditor需要)
        Map<String,String> map = new HashMap<String,String >();
        
		// 获取原本文件名称
        String fileName = file.getOriginalFilename();
        
        // 取得临时文件名称
    	String tempFileName = getTempFileName(fileName);
    	
    	// 设定文件的存储路径
    	String relativePath = StringUtil.getUploadPath(uploadType, moduleCode, "") + tempFileName;
    	String path = PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH) + relativePath;
        
		if (!file.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//是否上传成功
        map.put("state", "SUCCESS");

        //现在文件名称
        map.put("title", tempFileName);

        //文件原名称 
        map.put("original", fileName);

        //文件类型 .+后缀名
        map.put("type", fileName.substring(fileName.lastIndexOf(".")));

        //文件路径
        map.put("url", relativePath);

        //文件大小（字节数）
        map.put("size", file.getSize()+"");
        
		return map;
	}
	
	/**
	 * 生成文件名称。<br/>
	 * @param  String path 路径
	 * @return String 文件名称
	 */
	public static String generateFileName (String path) {
    	String suffix = "";
    	if (path.lastIndexOf(".")!=-1) {
    		suffix=path.substring(path.lastIndexOf("."));
    	}
		return UUID.randomUUID().toString() + suffix;
    }
	
	/**
	 * 生成临时文件名，用于保存数据
	 * 返回数据示例："442374d844f8414d8f78f27c5ffe46d2.png"
	 * @param  originalFilename 原始文件名
	 * @author 任晓茂
	 * @return String 临时保存的文件名
	 */
	public static String getTempFileName(String originalFilename) {
		String suffix = "";
    	if(originalFilename.lastIndexOf(".")!=-1){
    		suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
    	}
		return UUID.randomUUID().toString().replace("-", "") + suffix;
	}
	
	/**
	 * 对图片重命名，并返回符合要求的图片地址
	 * @param  tempAddress 临时地址
	 * @param  code 主键code
	 * @author 任晓茂
	 * @return String 真实的文件地址
	 * @throws Exception 
	 */
//	public static String getRealPath(String tempAddress, String code) throws Exception {
//		if (StringUtil.isBlank(tempAddress) || StringUtil.isBlank(code)) {
//			return null;
//		}
//		File file = new File(PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH) + "/" + tempAddress);
//		if (!file.exists()) {
//			return null;
//		}
//		if (tempAddress.contains(code)) {
//			return tempAddress;
//		}
//		//String oldName = tempAddress.replace(PropertyUtil.getConfigValue(ZzjConstants.ICON_PATH) + "/", "");
//		String oldName = tempAddress.substring(tempAddress.lastIndexOf("/") + 1, tempAddress.lastIndexOf("-"));
//		String realPath = tempAddress.replace(oldName, code);
//		File temp = new File(PropertyUtil.getConfigValue(ZzjConstants.ICON_PATH) + "/" + realPath);
//		if (temp.exists()) {
//			temp.delete();
//		}
//		file.renameTo(new File(PropertyUtil.getConfigValue(ZzjConstants.ICON_PATH) + "/" + realPath));
//		return realPath;
//	}
	
	/** 
	 * 文件删除<br/>
	 * 如果要新增删除图片方法，只需修改ZzjConstants.CHAPTER_PATH
	 * @param  path 文件路径
	 * @param  name 区分删除文件的类型
	 * @return boolean 返回删除是否成功
	 * @author 任晓茂
	 * @throws Exception 
	 */
	public static boolean delFile(String name, String path) throws Exception {
		if (StringUtil.isBlank(path) || StringUtil.isBlank(name)) {
			return false;
		}
		String root = null;
		switch (name) {
		case "img":
			root = PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH) + "/"; break;
		case "pdf":
			root = PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH) + "/"; break;
		case "video":
			root = PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH) + "/"; break;
		default: break;
		}
		
		File file = new File(root + path);
		if (!file.exists()) {
			return false;
		}
		file.delete();
		return true;
	}

	/**
	 * 对pdf重命名，并返回符合要求的pdf地址
	 * @param  tempAddress 临时地址
	 * @param  code 主键code
	 * @author 任晓茂
	 * @return String 真实的文件地址
	 * @throws Exception 
	 */
//	public static String getPdfRealPath(String tempAddress, String code) throws Exception {
//		if (StringUtil.isBlank(tempAddress) || StringUtil.isBlank(code)) {
//			return null;
//		}
//		File file = new File(PropertyUtil.getConfigValue(ZzjConstants.CHAPTER_PATH) + "/" + tempAddress);
//		if (!file.exists()) {
//			return null;
//		}
//		if (tempAddress.contains(code)) {
//			return tempAddress;
//		}
//		//String oldName = tempAddress.replace(PropertyUtil.getConfigValue(ZzjConstants.ICON_PATH) + "/", "");
//		String oldName = tempAddress.substring(tempAddress.lastIndexOf("/") + 1, tempAddress.lastIndexOf("."));
//		String realPath = tempAddress.replace(oldName, code);
//		File temp = new File(PropertyUtil.getConfigValue(ZzjConstants.CHAPTER_PATH) + "/" + realPath);
//		if (temp.exists()) {
//			temp.delete();
//		}
//		file.renameTo(new File(PropertyUtil.getConfigValue(ZzjConstants.CHAPTER_PATH) + "/" + realPath));
//		return realPath;
//	}
}