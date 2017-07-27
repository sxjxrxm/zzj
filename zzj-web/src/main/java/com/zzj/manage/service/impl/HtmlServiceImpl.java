/**
 * Project Name:zzj-html
 * File Name:HtmlInfoServiceImpl.java
 * Package Name:com.zzj.html.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.blo.CourseInfoBlo;
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.LiveInfoBlo;
import com.zzj.db.blo.NewsInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.model.CourseInfoVO;
import com.zzj.db.model.ExpertInfoEditVO;
import com.zzj.db.model.ExpertInfoListVO;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.db.model.NewsInfoEditVO;
import com.zzj.db.model.NewsInfoListVO;
import com.zzj.manage.service.HtmlService;
import com.zzj.util.DateUtil;
import com.zzj.util.FileUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>HtmlInfoServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>H5页面操作Service实现类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月16日上午11:15:45 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class HtmlServiceImpl implements HtmlService{
	
	/**
	 * 日志文件
	 */
	private static final Logger fileLog = LoggerFactory.getLogger(HtmlServiceImpl.class);
	
	/**
	 * 图片路径
	 */
	private static final String IMG_PATH = "../../../../";

	/**
	 * 知识内容操作BLO
	 */
	@Autowired
	private NewsInfoBlo newsInfoBlo;
	
	/**
	 * 专家内容操作BLO
	 */
	@Autowired
	private ExpertInfoBlo expertInfoBlo;
	
	/**
	 * 主题领域操作BLO
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	
	/**
	 * 直播内容操作BLO
	 */
	@Autowired
	private LiveInfoBlo liveInfoBlo;
	
	/**
	 * 课堂内容操作BLO
	 */
	@Autowired
	private CourseInfoBlo courseInfoBlo;
	
	/**
	 * 生成知识内容HTML处理<br/>
	 * @param request HTTP请求
	 * @param infoId 知识ID
	 * @return String 生成后的HTML相对路径
	 * @throws Exception
	 */
	@Override
	public String generateInfoHtml(HttpServletRequest request, String infoId) throws Exception {
		String h5Url = "";
		if(StringUtil.isNullOrEmpty(infoId)) {
			return h5Url;
		}

        try {
        	FileUtil fsh = new FileUtil();//实例化文件操作辅助类
        	
    		String templatePath = PropertyUtil.getConfigValue(ZzjConstants.H5_TEMPLATE_INFO); //模板文件地址
            String htmlPath = StringUtil.getLocalUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_02, infoId);//静态页文件地址
            String htmlFileName = this.generateHtmlName(infoId);
            h5Url = this.generateInfoHtmlUrl(infoId);

            StringBuffer fileContent = fsh.readToBuffer(templatePath, ZzjConstants.ENCODE_TYPE_UTF8);//读取模板文件
            String htmlcode = fileContent == null ? "" : fileContent.toString();
            
    		// 取得知识内容
    		NewsInfoEditVO info = newsInfoBlo.selectNewInfo(infoId);
    		if(info == null) {
    			// 没有取到知识内容的场合
    			info = new NewsInfoEditVO();
    			info.setUpdateTime(new Date());
    		}
    		// 设置知识来源名称
    		String source = "";
    		if(info.getSourceType() == ZzjConstants.SOURCE_TYPE_2){
    			// 来源为专家的时候
    			ArrayList<String> sourceList = new ArrayList<String>();
    			if(!StringUtil.isNullOrEmpty(info.getSourceOwnerName()) && sourceList.size() < 3){
    				sourceList.add(info.getSourceOwnerName());
    			} else if(StringUtil.isNullOrEmpty(info.getSourceOwnerName()) && StringUtil.isNotBlank(info.getSourceOwner()) && sourceList.size() < 3) {
    				// 专家库中找不到名称，但是专家code存在的场合，显示专家名称
    				sourceList.add(info.getSourceOwner());
    			}
    			if(!StringUtil.isNullOrEmpty(info.getExpertCd2Name()) && sourceList.size() < 3){
    				sourceList.add(info.getExpertCd2Name());
    			} else if(StringUtil.isNullOrEmpty(info.getExpertCd2Name()) && StringUtil.isNotBlank(info.getExpertCd2()) && sourceList.size() < 3) {
    				// 专家库中找不到名称，但是专家code存在的场合，显示专家名称
    				sourceList.add(info.getExpertCd2());
    			}
    			if(!StringUtil.isNullOrEmpty(info.getExpertCd3Name()) && sourceList.size() < 3){
    				sourceList.add(info.getExpertCd3Name());
    			} else if(StringUtil.isNullOrEmpty(info.getExpertCd3Name()) && StringUtil.isNotBlank(info.getExpertCd3()) && sourceList.size() < 3) {
    				// 专家库中找不到名称，但是专家code存在的场合，显示专家名称
    				sourceList.add(info.getExpertCd3());
    			}
    			if(!StringUtil.isNullOrEmpty(info.getExpertCd4Name()) && sourceList.size() < 3){
    				sourceList.add(info.getExpertCd4Name());
    			} else if(StringUtil.isNullOrEmpty(info.getExpertCd4Name()) && StringUtil.isNotBlank(info.getExpertCd4()) && sourceList.size() < 3) {
    				// 专家库中找不到名称，但是专家code存在的场合，显示专家名称
    				sourceList.add(info.getExpertCd4());
    			}
    			if(!StringUtil.isNullOrEmpty(info.getExpertCd5Name()) && sourceList.size() < 3){
    				sourceList.add(info.getExpertCd5Name());
    			} else if(StringUtil.isNullOrEmpty(info.getExpertCd5Name()) && StringUtil.isNotBlank(info.getExpertCd5()) && sourceList.size() < 3) {
    				// 专家库中找不到名称，但是专家code存在的场合，显示专家名称
    				sourceList.add(info.getExpertCd5());
    			}
    			source = StringUtils.join(sourceList, ",");
    		} else{
    			source = "找专家平台";
    		}
    		
    		// 设置知识标题
            htmlcode=htmlcode.replaceAll("###article_title###", info.getNewsName());
    		// 设置发布时间
            htmlcode=htmlcode.replaceAll("###article_time###", DateUtil.getYmdHmFormatString(info.getUpdateTime()));
    		// 设置知识来源
            htmlcode=htmlcode.replaceAll("###article_author###", source);
    		// 设置详情图片
            htmlcode=htmlcode.replaceAll("###article_img###", StringUtil.getImageURL(info.getBigIcon()));
    		// 设置知识内容
            htmlcode=htmlcode.replaceAll("###article_content###", info.getNewsBrief());
    		// 设置二维码
            htmlcode=htmlcode.replaceAll("###QR_code###","");
    		
    		// 替换HTML里的绝对路径为相对路径
    		htmlcode=htmlcode.replaceAll(PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_IMG_URL), IMG_PATH);
            
            // 保存成HTML文件
            fsh.WriteFile(htmlcode, htmlPath, htmlFileName, ZzjConstants.ENCODE_TYPE_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error(e.getMessage(), e);
        }
		
		return h5Url;
	}

	/**
	 * 生成专家简介HTML处理<br/>
	 * @param request HTTP请求
	 * @param expertId 专家ID
	 * @return String 生成后的HTML相对路径
	 * @throws Exception
	 */
	@Override
	public String generateExpertHtml(HttpServletRequest request, String expertId) throws Exception {
		String h5Url = "";
		if(StringUtil.isNullOrEmpty(expertId)) {
			return h5Url;
		}

        try {
        	FileUtil fsh = new FileUtil();//实例化文件操作辅助类

    		String templatePath = PropertyUtil.getConfigValue(ZzjConstants.H5_TEMPLATE_EXPERT); //模板文件地址
            String htmlPath = StringUtil.getLocalUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_01, expertId);//静态页文件地址
            String htmlFileName = this.generateHtmlName(expertId);
            h5Url = this.generateExpertHtmlUrl(expertId);
            
            StringBuffer fileContent = fsh.readToBuffer(templatePath, ZzjConstants.ENCODE_TYPE_UTF8);//读取模板文件
            String htmlcode = fileContent == null ? "" : fileContent.toString();
            
    		// 取得知识内容
    		ExpertInfoEditVO info = expertInfoBlo.selectExpertInfo(expertId);
    		if(info == null) {
    			// 没有取到专家内容的场合
    			info = new ExpertInfoEditVO();
    		}
    		
    		// 生成领域标签
    		Map<String, String> topicMap = new HashMap<String, String>();
    		topicMap.put("busiType", ZzjConstants.BUSI_TYPE_01);
    		topicMap.put("topicCd", expertId);
    		topicMap.put("codeType", ZzjConstants.TECH_FIELD_TYPE);
    		List<TopicFieldInfo> topicList = topicFieldInfoBlo.selectTopicInfo(topicMap);
    		StringBuffer sbTopic = new StringBuffer();
    		if(topicList != null) {
    			int count = 0;
    			for(TopicFieldInfo topic: topicList){
    				if(count < 3) {
        				sbTopic.append(MessageFormat.format("<p>{0}</p>", topic.getTechFieldName()));	
        				count++;
    				} else {
    					break;
    				}
    			}
    		}
    		
    		// 生成职称
    		StringBuffer sbPosition = new StringBuffer();
    		if(StringUtil.isNotBlank(info.getRank())) {
        		sbPosition.append(MessageFormat.format("<p>{0}</p>", info.getRank()));
    		}
    		if(StringUtil.isNotBlank(info.getRank2())) {
        		sbPosition.append(MessageFormat.format("<p>{0}</p>", info.getRank2()));
    		}
    		if(StringUtil.isNotBlank(info.getRank3())) {
        		sbPosition.append(MessageFormat.format("<p>{0}</p>", info.getRank3()));
    		}
    		if(StringUtil.isNotBlank(info.getRank4())) {
        		sbPosition.append(MessageFormat.format("<p>{0}</p>", info.getRank4()));
    		}
    		// 设置专家顶部背景图
            htmlcode=htmlcode.replaceAll("###expert_head_backimg###", StringUtil.getImageURL(info.getBackgroundAdress()));
    		// 设置专家头像
            htmlcode=htmlcode.replaceAll("###expert_head_url###", StringUtil.getImageURL(info.getAvatorAddress()));
    		// 设置专家姓名
            htmlcode=htmlcode.replaceAll("###expert_name###", info.getExpertName());
    		// 设置专家领域
            htmlcode=htmlcode.replaceAll("###expert_label###", sbTopic.toString());
    		// 设置专家职称
            htmlcode=htmlcode.replaceAll("###expert_position###", sbPosition.toString());
    		// 设置专家简介
            htmlcode=htmlcode.replaceAll("###expert_content###", info.getExpertBrief());
    		// 设置二维码
            htmlcode=htmlcode.replaceAll("###QR_code###","");
    		
    		// 替换HTML里的绝对路径为相对路径
    		htmlcode=htmlcode.replaceAll(PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_IMG_URL), IMG_PATH);
            
            // 保存成HTML文件
            fsh.WriteFile(htmlcode, htmlPath, htmlFileName, ZzjConstants.ENCODE_TYPE_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error(e.getMessage(), e);
        }
		
		return h5Url;
	}


	/**
	 * 生成视频简介HTML处理<br/>
	 * @param request HTTP请求
	 * @param expertId 视频ID
	 * @return String 生成后的HTML相对路径
	 * @throws Exception
	 */
	@Override
	public String generateVideoHtml(HttpServletRequest request, String videoId) throws Exception {
		return null;
	}

	/**
	 * 生成直播简介HTML处理<br/>
	 * @param request HTTP请求
	 * @param liveId 直播ID
	 * @return String 生成后的HTML相对路径
	 * @throws Exception
	 */
	@Override
	public String generateLiveHtml(HttpServletRequest request, String liveId) throws Exception {
		String h5Url = "";
		if(StringUtil.isNullOrEmpty(liveId)) {
			return h5Url;
		}

        try {
        	FileUtil fsh = new FileUtil();//实例化文件操作辅助类
        	
    		String templatePath = PropertyUtil.getConfigValue(ZzjConstants.H5_TEMPLATE_LIVE); //模板文件地址
            String htmlPath = StringUtil.getLocalUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_03, liveId);//静态页文件地址
            String htmlFileName = this.generateHtmlName(liveId);
            h5Url = this.generateLiveHtmlUrl(liveId);

            StringBuffer fileContent = fsh.readToBuffer(templatePath, ZzjConstants.ENCODE_TYPE_UTF8);//读取模板文件
            String htmlcode = fileContent == null ? "" : fileContent.toString();
            
    		// 取得直播内容
    		LiveInfoVO info = liveInfoBlo.selectByPrimaryKey(liveId);
    		if(info == null) {
    			// 没有取到直播内容的场合
    			info = new LiveInfoVO();
    		}
    		
    		// 设置直播图片
            htmlcode=htmlcode.replaceAll("###live_image###", StringUtil.getImageURL(info.getBigIcon()));
    		
    		// 编辑直播时间
    		String classTime = MessageFormat.format("{0}~{1}", 
    				new Object[]{DateUtil.convertDateToString("yyyy年MM月dd日 HH:mm", info.getStartTime()),
    						DateUtil.convertDateToString("yyyy年MM月dd日 HH:mm", info.getEndTime())});
    		// 设置直播时间
            htmlcode=htmlcode.replaceAll("###live_time###", classTime);
    		// 设置直播主题
            htmlcode=htmlcode.replaceAll("###live_title###", info.getLiveName());
    		// 设置开讲内容
            htmlcode=htmlcode.replaceAll("###live_content###", info.getLiveBrief());
    		// 设置二维码
            htmlcode=htmlcode.replaceAll("###QR_code###","");
    		
    		// 替换HTML里的绝对路径为相对路径
    		htmlcode=htmlcode.replaceAll(PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_IMG_URL), IMG_PATH);
            
            // 保存成HTML文件
            fsh.WriteFile(htmlcode, htmlPath, htmlFileName, ZzjConstants.ENCODE_TYPE_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error(e.getMessage(), e);
        }
		
		return h5Url;
	}

	/**
	 * 生成课堂简介HTML处理<br/>
	 * @param request HTTP请求
	 * @param expertId 课堂ID
	 * @return String 生成后的HTML相对路径
	 * @throws Exception
	 */
	@Override
	public String generateCourseHtml(HttpServletRequest request, String courseId) throws Exception {
		String h5Url = "";
		if(StringUtil.isNullOrEmpty(courseId)) {
			return h5Url;
		}
		
		String templatePath = PropertyUtil.getConfigValue(ZzjConstants.H5_TEMPLATE_COURSE); //模板文件地址
        String htmlPath = StringUtil.getLocalUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_04, courseId);//静态页文件地址
        String htmlFileName = this.generateHtmlName(courseId);
        h5Url = this.generateCourseHtmlUrl(courseId);

        try {
        	FileUtil fsh = new FileUtil();//实例化文件操作辅助类

            StringBuffer fileContent = fsh.readToBuffer(templatePath, ZzjConstants.ENCODE_TYPE_UTF8);//读取模板文件
            String htmlcode = fileContent == null ? "" : fileContent.toString();
            
    		// 取得课堂内容
            CourseInfoVO info = courseInfoBlo.selectByPrimaryKey(courseId);
    		if(info == null) {
    			// 没有取到课堂内容的场合
    			info = new CourseInfoVO();
    		}
    		
    		// 设置课堂图片
            htmlcode=htmlcode.replaceAll("###class_detail_img###", StringUtil.getImageURL(info.getBigIcon()));
    		
    		// 编辑开讲时间
    		String classTime = MessageFormat.format("{0}~{1}", 
    				new Object[]{DateUtil.convertDateToString("yyyy年MM月dd日 HH:mm", info.getStartTime()),
    						DateUtil.convertDateToString("yyyy年MM月dd日 HH:mm", info.getEndTime())});
    		// 设置开讲时间
            htmlcode=htmlcode.replaceAll("###class_time###", classTime);
    		// 设置开讲主题
            htmlcode=htmlcode.replaceAll("###class_title###", info.getCourseName());
    		// 设置开讲内容
            htmlcode=htmlcode.replaceAll("###class_content###", info.getCourseBrief());
    		// 设置二维码
            htmlcode=htmlcode.replaceAll("###QR_code###","");
    		
    		// 替换HTML里的绝对路径为相对路径
    		htmlcode=htmlcode.replaceAll(PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_IMG_URL), IMG_PATH);
            
            // 保存成HTML文件
            fsh.WriteFile(htmlcode, htmlPath, htmlFileName, ZzjConstants.ENCODE_TYPE_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error(e.getMessage(), e);
        }
		
		return h5Url;
	}

	/**
	 * 知识H5页面URL生成处理
	 * @param infoId 知识ID
	 * @return String 知识H5页面URL链接 
	 * @throws Exception
	 */
	@Override
	public String generateInfoHtmlUrl(String infoId) throws Exception {

		String baseUrl = "";
		
        String htmlFileName = this.generateHtmlName(infoId);//静态页面文件名称
                
        // 生成HTML地址
        htmlFileName = baseUrl + StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_02, infoId) + htmlFileName;
        
        return htmlFileName;
	}
	
	/**
	 * 生成静态页面文件名
	 * @param itemId 项目ID
	 * @return String 静态页面文件名
	 * @throws Exception
	 */
	private String generateHtmlName(String itemId) throws Exception {
		return MessageFormat.format("{0}.html", new Object[]{StringUtil.stringToMD5(itemId)});//静态页面文件名称
	}

	/**
	 * 专家简介H5页面URL生成处理
	 * @param expertId 专家ID
	 * @return String 专家简介H5页面URL链接 
	 * @throws Exception
	 */
	@Override
	public String generateExpertHtmlUrl(String expertId) throws Exception {

		String baseUrl = "";

        String htmlFileName = this.generateHtmlName(expertId);//静态页面文件名称
        
        // 生成HTML地址
        htmlFileName = baseUrl + StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_01, expertId) + htmlFileName;
        
        return htmlFileName;
	}

	/**
	 * 视频简介H5页面URL生成处理
	 * @param videoId 视频ID
	 * @return String 视频简介H5页面URL链接 
	 * @throws Exception
	 */
	@Override
	public String generateVideoHtmlUrl(String videoId) throws Exception {
		
		return null;
	}

	/**
	 * 直播简介H5页面URL生成处理
	 * @param liveId 直播ID
	 * @return String 直播简介H5页面URL链接 
	 * @throws Exception
	 */
	@Override
	public String generateLiveHtmlUrl(String liveId) throws Exception {

		String baseUrl = "";

        String htmlFileName = this.generateHtmlName(liveId);//静态页面文件名称
        
        // 生成HTML地址
        htmlFileName = baseUrl + StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_03, liveId) + htmlFileName;
        
        return htmlFileName;
	}

	/**
	 * 课堂简介H5页面URL生成处理
	 * @param courseId 课堂ID
	 * @return String 课堂简介H5页面URL链接 
	 * @throws Exception
	 */
	@Override
	public String generateCourseHtmlUrl(String courseId) throws Exception {

		String baseUrl = "";

        String htmlFileName = this.generateHtmlName(courseId);//静态页面文件名称
        
        // 生成HTML地址
        htmlFileName = baseUrl + StringUtil.getUploadPath(ZzjConstants.UPLOAD_TYPE_HTML, ZzjConstants.BUSI_TYPE_04, courseId) + htmlFileName;
        
        return htmlFileName;
	}

	/**
	 * 生成所有HTML页面
	 * @return String 生成结果
	 * @throws Exception
	 */
	@Override
	public String generateAllHtml() throws Exception {
		
		// 生成知识HTML
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<NewsInfoListVO> newsList = newsInfoBlo.selectAll(map);
		if(newsList != null) {
			for(NewsInfoListVO newsInfoListVO : newsList) {
				this.generateInfoHtml(null, newsInfoListVO.getNewsCd());
			}
		}

		// 生成专家HTML
		List<ExpertInfoListVO> expertList = expertInfoBlo.selectAll(map);
		if(expertList != null) {
			for(ExpertInfoListVO expertInfoListVO : expertList) {
				this.generateExpertHtml(null, expertInfoListVO.getExpertId());
			}
		}

		// 生成课堂HTML
		List<CourseInfoVO> courseList = courseInfoBlo.selectAll(map);
		if(courseList != null) {
			for(CourseInfoVO courseInfoVO : courseList) {
				this.generateCourseHtml(null, courseInfoVO.getCourseCd());
			}
		}

		// 生成直播HTML
		LiveInfoVO record = new LiveInfoVO();
		List<LiveInfoVO> liveList = liveInfoBlo.selectAll(record);
		if(liveList != null) {
			for(LiveInfoVO liveInfoVO : liveList) {
				this.generateLiveHtml(null, liveInfoVO.getLiveCd());
			}
		}
		
		return "success";
	}
}

