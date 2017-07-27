/**
 * Project Name:zzj-base
 * File Name:StringUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * <p><strong>类名: </strong></p>StringUtil <br/>
 * <p><strong>功能说明: </strong></p>字符串工具类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午7:13:24 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class StringUtil {
	/**
	 * 系统环境属性实例
	 */
	public static Properties props = System.getProperties();
	/**
	 * 文件分隔符
	 */
	public static final String fileSeparator = (String) StringUtil.props.get("file.separator");
	/**
	 * 本机IP地址显示符
	 */
	public static final String localhost = "0:0:0:0:0:0:0:1";
	/**
	 * 本机IP地址显示符
	 */
	public static final String localhostAddress = "127.0.0.1";
	/**
	 * 图片格式数组
	 */
	public static final String[] imageTypes = {"JPG","JPEG","GIF","PNG","BMP"};
	/**
	 * 视频格式数组
	 */
	public static final String[] vedioTypes = {"MP4","AVI","MOV","ASF","WMV","NAVI","3GP","RA","RAM","MKV","FLV","F4V","RMVB"};
	/**
	 * 音频格式数组
	 */
	public static final String[] voiceTypes = {"WAV","MP3","WMA","MID","RMI"};
	/**
	 * 浮点行数字格式转换类
	 */
	public static DecimalFormat decimalFormat  = new DecimalFormat("######0.00"); 
	/**
	 * 判断字符串是否非空
	 * @param str
	 * @return boolean 是否为空
	 */
	public static boolean isNullOrEmpty(String str) {
		return (null == str || "".equals(str));
	}
	/**
	 * 将null字符串转换成空字符串
	 * @param  target 字符串
	 * @return String 转换后的字符串
	 */
	public static String nullToBlank(String target){
		return target == null ? "" : target;
	}
	/**
	 * 将null字符串转换成空字符串
	 * @param  target 字符串
	 * @return String 转换后的字符串
	 */
	public static String nullToBlank(Object target){
		return target == null ? "" : String.valueOf(target).trim();
	}
	/**
	 * 判断是否不为空字符串
	 * @param  target 目标字符串
	 * @return boolean 是否不为空字符串
	 */
	public static boolean isNotBlank(String target){
		return (null != target && !"".equals(target.trim()));
	}
	/**
	 * 判断数组是否不为空且长度大于0
	 * @param  target 目标数组
	 * @return boolean 是否不为空字符串
	 */
	public static boolean isNotBlank(Object[] target){
		return (null != target && target.length>0);
	}
	/**
	 * 判断数组是否为空
	 * @param  target 目标数组
	 * @return boolean 是否不为空字符串
	 */
	public static boolean isBlank(Object[] target){
		return (null == target || target.length==0);
	}
	/**
	 * 判断集合是否不为空且长度大于0
	 * @param  targetList 目标集合对象
	 * @return boolean 是否不为空
	 */
	public static boolean isNotBlank(List<?> targetList){
		return (null != targetList && targetList.size()>0);
	}
	/**
	 * 判断是否为空字符串
	 * @param  target 目标字符串
	 * @return boolean 是否不为空字符串
	 */
	public static boolean isBlank(String target){
		return (null == target || "".equals(target.trim()));
	}
	
	/**
	 * 将数组转换成字符串，中间用“,”隔开
	 * @param  target 目标数组
	 * @return String 用“,”隔开的字符串
	 */
	public static String arrayToString(Object[] target){
		if(target==null) return null;
		String str = "";
		for (int i = 0 ,l=target.length; i < l; i++) {
			str +=target[i];
			if(i<l-1)
				str+=",";
		}
		return str;
	}
	
	/**
	 * 将数组成员判重
	 * @param  target 目标数组
	 * @return boolean true 不重； false 重
	 */
	public static boolean arrRepeatCheck(String[] target){
		if (target==null) return true;
		for (int i = 0; i < target.length; i++) {
	        String temp = nullToBlank(target[i]);
	        if (isBlank(temp))
	        {
	        	continue;
	        }
	        for (int j = i + 1; j < target.length; j++) {
	        	String temp2 = nullToBlank(target[j]);
	            if(temp.equals(temp2)){
	            	return false;
	            }
	        }
		}
		return true;
	}
	
	/**
	 * 根据文件名获得文件类型
	 * @param fileName 文件名称
	 * @return String 文件类型
	 */
	public static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	/**
	 * 根据内容类型判断文件扩展名
	 * @param contentType 内容类型
	 * @return String 文件扩展名
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		else {
			fileEndWitsh = "."+contentType.substring(contentType.indexOf("/")+1);
		}
		return fileEndWitsh;
	}
	/**
	 * 根据实际文件名获得去掉后缀后的文件名称
	 * @param fileName 文件名称
	 * @return String 去掉后缀后的文件名称
	 */
	public static String getFileName(String s) {
		return s.substring(0, s.lastIndexOf("."));
	}
	
	/**
	 * 重命名文件
	 * @param path
	 * @param fileName
	 * @return String 文件名称
	 */
	public static String renameFileName(String path, String fileName) {
		File file = new File(path +"/"+ fileName);// 获取要报存的文件路径
		if (file.exists()
				&& (StringUtil.getFileType(fileName).equals("jpg")
						|| StringUtil.getFileType(fileName).equals("jpeg")
						|| StringUtil.getFileType(fileName).equals("png") || StringUtil
						.getFileType(fileName).equals("bmp")))// 如果文件已存在就重命名，不存在就直接返回
		{
			return System.currentTimeMillis()+"."+StringUtil.getFileType(fileName) ;
		}
		return fileName;
	}
	/**
	 * 判断是否为图片文件. <br/>
	 * @param fileTypeStr 文件后缀名   
	 * @return boolean 是否为图片文件
	 * @throws Exception
	 */
	public static boolean isImageFile(String fileTypeStr) throws Exception{
		fileTypeStr = fileTypeStr.toUpperCase();
		for (int i = 0; i < imageTypes.length; i++) {
			if(fileTypeStr.equals(imageTypes[i])){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取文件类型0,图片，1，视频，2音频，其他为-1. <br/>
	 * @param fileTypeStr 文件后缀名
	 * @return int 类型值 0,图片，1，视频，2音频
	 * @throws Exception
	 */
	public static Short getFileType2Int(String fileTypeStr) throws Exception{
		fileTypeStr = fileTypeStr.toUpperCase();
		for (int i = 0; i < imageTypes.length; i++) {
			if(fileTypeStr.equals(imageTypes[i])){
				return 0;
			}
		}
		for (int i = 0; i < vedioTypes.length; i++) {
			if(fileTypeStr.equals(vedioTypes[i])){
				return 1;
			}
		}
		for (int i = 0; i < voiceTypes.length; i++) {
			if(fileTypeStr.equals(voiceTypes[i])){
				return 1;
			}
		}
		return -1;
	}
	/**
	 * 校验字符串是否为数字
	 * @param str 字符串
	 * @return boolean 是否为数字
	 */
	public static boolean isNumber(String str){
		boolean flag = false;
		//判断正负数都可以
		//Pattern pattern = Pattern.compile("^[-]{0,1}[0-9]+$");
		Pattern pattern = Pattern.compile("^[0-9][0-9]*$");
		Matcher isNum = pattern.matcher(str);
		if(isNum.matches()){
			flag = true;
		}
		return flag;
	}
	/**
	 * 加密算法Md5
	 * @param password 待加密的密码原文
	 * @return String 加密后的字符串
	 * @throws Exception
	 */
	public static String stringToMD5(String password) throws Exception{
		String result="";
		if(null!=password){
			MessageDigest md5 = null;
			StringBuffer hexValue = new StringBuffer();
			try {
				md5 = MessageDigest.getInstance("MD5");
				char[] charArray = password.toCharArray();
				byte[] byteArray = new byte[charArray.length];
	
				for (int i = 0; i < charArray.length; i++)
					byteArray[i] = (byte) charArray[i];
				byte[] md5Bytes = md5.digest(byteArray);
				for (int i = 0; i < md5Bytes.length; i++) {
					int val = ((int) md5Bytes[i]) & 0xff;
					if (val < 16)
						hexValue.append("0");
					hexValue.append(Integer.toHexString(val));
					result=hexValue.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
    /**
     * 将字符串转换成List(字符串格式如"12,123,43,1,4")
     * @param  source 被转换的字符串
     * @return List 转换后的结果
     * @throws exception
     */
	public static List<Integer> StringToList(String source) throws Exception{
		List<Integer> result =  null;
		if(null != source && !"".equals(source.trim())){
			String [] target = source.split(",| ");
			result = new ArrayList<Integer>(target.length);
			
			for(int i = 0, len = target.length ; i < len ; i++){
				try{
					Integer tmp = Integer.valueOf(target[i]);
					result.add(tmp);
				}catch(NumberFormatException e){
				   throw new NumberFormatException("字符串转换成整数失败 :\t"+target[i]);	
				}
			}
		}
		
	    return result;
	}
	
	 /**
     * 将字符串转换成List(字符串格式如"12,123,43,1,4")
     * @param  source 被转换的字符串
     * @return List 转换后的结果
     * @throws exception
     */
	public static List<BigDecimal> StringToBigDecimalList(String source) throws Exception{
		List<BigDecimal> result = null;
		if(null != source && !"".equals(source.trim())){
			String [] target = source.split(",| ");
			result = new ArrayList<BigDecimal>(target.length);
			
			for(int i = 0, len = target.length ; i < len ; i++){
				try{
					BigDecimal tmp = new BigDecimal(target[i]);
					result.add(tmp);
				}catch(NumberFormatException e){
				   throw new NumberFormatException("字符串转换成整数失败 :\t"+target[i]);	
				}
			   }
		}
	    return result;
	}
	/**
	 * 将List<BigDecimal>转换成字符串
	 * @param  source 转换对象
	 * @param  separator 分隔符(为null则默认为",")
	 * @return String 转换结果(用分隔符隔开,如：1,2,3)
	 * @throws Exception
	 */
	public static String BigDecimalListToString(List<BigDecimal> source,String separator){
		StringBuilder buffer = null;
		if(null != source && source.size() > 0){
			buffer = new StringBuilder(source.size());
			if(null == separator){
				separator = ",";
			}
			
			for(int i = 0, len = source.size(); i < len; i++){
				if(i > 0){
					buffer.append(separator + source.get(i).intValue());
				}else{
					buffer.append(source.get(i).intValue());
				}
			}
		}
		
		return (buffer == null ? "" : buffer.toString());
	}
	/**
	 * 将List<String>转换成字符串
	 * @param  source 转换对象
	 * @param  separator 分隔符(为null则默认为",")
	 * @return String 转换结果(用分隔符隔开,如：1,2,3)
	 * @throws Exception
	 */
	public static String StringListToString(List<String> source,String separator){
		StringBuilder buffer = null;
		if(null != source && source.size() > 0){
			buffer = new StringBuilder(source.size());
			if(null == separator){
				separator = ",";
			}
			
			for(int i = 0, len = source.size(); i < len; i++){
				if(i > 0){
					buffer.append(separator + source.get(i));
				}else{
					buffer.append(source.get(i));
				}
			}
		}
		return (buffer == null ? "" : buffer.toString());
	}
	
	/**
     * 将字符串转换成字符List(字符串格式如"12,123,43,1,4")
     * @param  source 被转换的字符串
     * @return List 转换后的结果
     * @throws exception
     */
	public static List<String> StringToStringList(String source) throws Exception{
		
		if(null == source || "".equals(source.trim())){
			throw new IllegalArgumentException("字符串不能为空！");
		}
		
		String [] target = source.split(",| ");
		List<String> result = Arrays.asList(target);
		
	    return result;
	}
	
	
	/**
	 * 创建uuid
	 * @return String uuid值
	 * @throws Exception 
	 */
	public static String getUuid() throws Exception {
		String uuid = java.util.UUID.randomUUID().toString();
		//uuid = uuid.replaceAll("-", "");
		return uuid;
	}
	
	/**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String digitUppercase(double n){
    	try {
    		String fraction[] = {"角", "分"};
            String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
            String unit[][] = {{"元", "万", "亿"},{"", "拾", "佰", "仟"}};
     
            String head = n < 0? "负": "";
            n = Math.abs(n);
             
            String s = "";
            for (int i = 0; i < fraction.length; i++) {
                s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
            }
            if(s.length()<1){
                s = "整";    
            }
            int integerPart = (int)Math.floor(n);
     
            for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
                String p ="";
                for (int j = 0; j < unit[1].length && n > 0; j++) {
                    p = digit[integerPart%10]+unit[1][j] + p;
                    integerPart = integerPart/10;
                }
                s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
            }
            return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
        
    }
	
	/**
	 * 获取异常信息
	 * @param e 系统异常实例
	 * @return String 异常文本信息
	 */
	public static String getExceptionInfo(Exception e){
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
	/**
	 * 获取JVM运行错误信息
	 * @param e JVM错误实例
	 * @return String 异常文本信息
	 */
	public static String getExceptionInfo(Error e){
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
	/**
	 * 解码UTF16编码字符串
	 * @param uTF16Str UTF16编码字符串
	 * @return String 解码后的字符串
	 * **/
	public static String decompressFromUTF16(String uTF16Str) {
	    if (uTF16Str == null)
	      return "";
	    StringBuilder output = new StringBuilder(200);
	    int current = 0, c, status = 0, i = 0;

	    while (i < uTF16Str.length()) {
	      c = (((int)uTF16Str.charAt(i)) - 32);

	      switch (status++) {
	        case 0:
	          current = c << 1;
	          break;
	        case 1:
	          output.append((char) (current | (c >> 14)));
	          current = (c & 16383) << 2;
	          break;
	        case 2:
	          output.append((char) (current | (c >> 13)));
	          current = (c & 8191) << 3;
	          break;
	        case 3:
	          output.append((char) (current | (c >> 12)));
	          current = (c & 4095) << 4;
	          break;
	        case 4:
	          output.append((char) (current | (c >> 11)));
	          current = (c & 2047) << 5;
	          break;
	        case 5:
	          output.append((char) (current | (c >> 10)));
	          current = (c & 1023) << 6;
	          break;
	        case 6:
	          output.append((char) (current | (c >> 9)));
	          current = (c & 511) << 7;
	          break;
	        case 7:
	          output.append((char) (current | (c >> 8)));
	          current = (c & 255) << 8;
	          break;
	        case 8:
	          output.append((char) (current | (c >> 7)));
	          current = (c & 127) << 9;
	          break;
	        case 9:
	          output.append((char) (current | (c >> 6)));
	          current = (c & 63) << 10;
	          break;
	        case 10:
	          output.append((char) (current | (c >> 5)));
	          current = (c & 31) << 11;
	          break;
	        case 11:
	          output.append((char) (current | (c >> 4)));
	          current = (c & 15) << 12;
	          break;
	        case 12:
	          output.append((char) (current | (c >> 3)));
	          current = (c & 7) << 13;
	          break;
	        case 13:
	          output.append((char) (current | (c >> 2)));
	          current = (c & 3) << 14;
	          break;
	        case 14:
	          output.append((char) (current | (c >> 1)));
	          current = (c & 1) << 15;
	          break;
	        case 15:
	          output.append((char) (current | c));

	          status = 0;
	          break;
	      }
	      i++;
	    }
	    return StringUtil.decompress(output.toString());
	}
	/**
	 * 配合UTF16解码的字符压缩函数
	 * @return compressed 待压缩字符串
	 * @return String 压缩处理后的字符串
	 * **/
	public static String decompress(String compressed) {
	    if (compressed == null)
	      return "";
	    if (compressed == "")
	      return null;
	    List<String> dictionary = new ArrayList<String>(200);
	    double enlargeIn = 4;
	    int dictSize = 4;
	    int numBits = 3;
	    String entry = "";
	    StringBuilder result;
	    String w;
	    int bits;
	    int resb;
	    double maxpower;
	    int power;
	    String c = "";
	    int d;
	    LZData data = LZData.getInstance();
	    data.string = compressed;
	    data.val = (int) compressed.charAt(0);
	    data.position = 32768;
	    data.index = 1;

	    for (int i = 0; i < 3; i += 1) {
	      dictionary.add(i, "");
	    }

	    bits = 0;
	    maxpower = Math.pow(2, 2);
	    power = 1;
	    while (power != Double.valueOf(maxpower).intValue()) {
	      resb = data.val & data.position;
	      data.position >>= 1;
	      if (data.position == 0) {
	        data.position = 32768;
	        data.val = (int) data.string.charAt(data.index++);
	      }
	      bits |= (resb > 0 ? 1 : 0) * power;
	      power <<= 1;
	    }

	    switch (bits) {
	      case 0:
	        bits = 0;
	        maxpower = Math.pow(2, 8);
	        power = 1;
	        while (power != Double.valueOf(maxpower).intValue()) {
	          resb = data.val & data.position;
	          data.position >>= 1;
	          if (data.position == 0) {
	            data.position = 32768;
	            data.val = (int) data.string.charAt(data.index++);
	          }
	          bits |= (resb > 0 ? 1 : 0) * power;
	          power <<= 1;
	        }
	        c += (char) bits;
	        break;
	      case 1:
	        bits = 0;
	        maxpower = Math.pow(2, 16);
	        power = 1;
	        while (power != Double.valueOf(maxpower).intValue()) {
	          resb = data.val & data.position;
	          data.position >>= 1;
	          if (data.position == 0) {
	            data.position = 32768;
	            data.val = (int) data.string.charAt(data.index++);
	          }
	          bits |= (resb > 0 ? 1 : 0) * power;
	          power <<= 1;
	        }
	        c += (char) bits;
	        break;
	      case 2:
	        return "";

	    }

	    dictionary.add(3, c);
	    w = c;
	    result = new StringBuilder(200);
	    result.append(c);

	   // w = result = c;

	    while (true) {
	      if (data.index > data.string.length()) {
	        return "";
	      }

	      bits = 0;
	      maxpower = Math.pow(2, numBits);
	      power = 1;
	      while (power != Double.valueOf(maxpower).intValue()) {
	        resb = data.val & data.position;
	        data.position >>= 1;
	        if (data.position == 0) {
	          data.position = 32768;
	          data.val = (int) data.string.charAt(data.index++);
	        }
	        bits |= (resb > 0 ? 1 : 0) * power;
	        power <<= 1;
	      }

	      switch (d = bits) {
	        case 0:
	          bits = 0;
	          maxpower = Math.pow(2, 8);
	          power = 1;
	          while (power != Double.valueOf(maxpower).intValue()) {
	            resb = data.val & data.position;
	            data.position >>= 1;
	            if (data.position == 0) {
	              data.position = 32768;
	              data.val = (int) data.string.charAt(data.index++);
	            }
	            bits |= (resb > 0 ? 1 : 0) * power;
	            power <<= 1;
	          }

	          String temp = "";
	          temp += (char) bits;
	          dictionary.add(dictSize++, temp);

	          d = dictSize - 1;

	          enlargeIn--;

	          break;
	        case 1:
	          bits = 0;
	          maxpower = Math.pow(2, 16);
	          power = 1;
	          while (power != Double.valueOf(maxpower).intValue()) {
	            resb = data.val & data.position;
	            data.position >>= 1;
	            if (data.position == 0) {
	              data.position = 32768;
	              data.val = (int) data.string.charAt(data.index++);
	            }
	            bits |= (resb > 0 ? 1 : 0) * power;
	            power <<= 1;
	          }

	          temp = "";
	          temp += (char) bits;

	          dictionary.add(dictSize++, temp);

	          d = dictSize - 1;

	          enlargeIn--;

	          break;
	        case 2:
	          return result.toString();
	      }

	      if (Double.valueOf(enlargeIn).intValue() == 0) {
	        enlargeIn = Math.pow(2, numBits);
	        numBits++;
	      }

	      if (d < dictionary.size() && dictionary.get(d) != null) {
	        entry = dictionary.get(d);
	      } else {
	        if (d == dictSize) {
	          entry = w + w.charAt(0);
	        } else {
	          return null;
	        }
	      }

	      result.append(entry);

	      // Add w+entry[0] to the dictionary.
	      dictionary.add(dictSize++, w + entry.charAt(0));
	      enlargeIn--;

	      w = entry;

	      if (Double.valueOf(enlargeIn).intValue() == 0) {
	        enlargeIn = Math.pow(2, numBits);
	        numBits++;
	      }

	    }
	}
	
	/**
	 * Hash加密处理. <br/>
	 * @param  phoneNum 登录账户
	 * @param  password 密码
	 * @return cipher 密文
	 * @throws exception
	 */
	public static String getHashCipher(String phoneNum,String password){
		String cipher = new SimpleHash(ZzjConstants.C_S_HASH_TYPE,password).toString(); 
		return cipher;
	}	
	/**
	 * 获取IP地址
	 * @param request http请求对象
	 * @return String IP地址
	 * @throws Exception
	 */
	public static String getRemoteIPAddress(HttpServletRequest request) {
       String ip = request.getHeader("x-forwarded-for");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    	   ip = request.getRemoteAddr();
       }
       if(localhost.equals(ip)) return localhostAddress;
       return ip;
	}
	
	/**
	 * 取得H5页面完整URL<br/>
	 * @param  h5Path H5页面相对路径
	 * @return String  H5页面完整URL
	 * @throws Exception
	 */
	public static String getH5URL(String h5Path) throws Exception {
		// 从配置文件中取得H5页面的前缀URL，拼上H5页面的相对路径
		if(StringUtil.isBlank(h5Path)){
			return h5Path;
		} else{
			return PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_H5_URL) + h5Path;
		}
	}
	
	/**
	 * 取得pdf页面完整URL<br/>
	 * @param  pdfPath pdf相对路径
	 * @return String  pdf完整URL
	 * @throws Exception
	 */
	public static String getPdfURL(String pdfPath) throws Exception {
		// 从配置文件中取得pdf的前缀URL，拼上pdf的相对路径
		if(StringUtil.isBlank(pdfPath)){
			return pdfPath;
		} else{
			return PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_PDF_URL) + pdfPath;
		}
	}
	
	/**
	 * 取得APK页面完整URL<br/>
	 * @param  APKPath APK相对路径
	 * @return String  APK完整URL
	 * @throws Exception
	 */
	public static String getApkURL(String apkPath) throws Exception {
		// 从配置文件中取得apk的前缀URL，拼上apk的相对路径
		if(StringUtil.isBlank(apkPath)){
			return apkPath;
		} else{
			return PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_APK_URL) + apkPath;
		}
	}
	
	/**
	 * 取得图片完整URL<br/>
	 * @param  imgPath 图片相对路径
	 * @return String  图片完整URL
	 * @throws Exception
	 */
	public static String getImageURL(String imgPath) throws Exception {
		// 从配置文件中取得图片的前缀URL，拼上图片的相对路径
		if(StringUtil.isBlank(imgPath)) {
			return "";
		}
		return PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_IMG_URL) + imgPath;
	}

	/**
	 * 
	 * 将JSON串List转换成数据类型为JSONMap的List。<br/>
	 * 将数据库中的JSON串转换成前台可以使用JSON对象
	 * @param  jsonStringList,JSON串List
	 * @return 数据类型  JSONMap的List，返回前台用JSON对象List
	 * @throws Exception
	 */
	public static List<Map<String, String>> convertJsonList(List<String> jsonStringList) {
		List<Map<String, String>> jsonObjList = new ArrayList<Map<String, String>>();
		
		// 参数为空的场合，直接返回JSON对象List
		if(jsonStringList == null) {
			return jsonObjList;
		}
		// 遍历JSON串List,生成JSON对象List
		for(String json : jsonStringList) {
			//jsonObjList.add(GsonUtil.getKeyMap(json));
			jsonObjList.add(GsonUtil.getKeyMapString(json));
		}
		return jsonObjList;
	}
	
	/**
	 * 
	 * 字符串每隔四位插入分隔符'-' 1606-1310-0637-0506. <br/>
	 * @param  str 要格式化的字符串
	 * @return String 格式化的字符串
	 * @throws exception
	 */
	public static String getStrFormat(String str){
		
		if(null == str || str == ""){
			return str;
		}
		
		StringBuffer sb = new StringBuffer(str);
		for(int index = 4; index < str.length(); index+=5){
			sb.insert(index, '-');
		}
		return sb.toString();
	}
	
	/**
	 * 手机号校验
	 * @param  字符串手机号
	 * @return 校验结果，成功返回true，否则返回false
	 * @throws Exception
	 */
	public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
	
	public static void main(String[] args) {
		String path = getUploadPath("content", "02", "");
		System.out.println(path);
		path = getUploadPath("avatar", "", "USR90999999");
		System.out.println(path);
		path = getUploadPath("file", "01", "USR90999999");
		System.out.println(path);
		path = getUploadPath("other", "", "USR90999999");
		System.out.println(path);
		path = getUploadPath("content", "02", "USR90999999");
		System.out.println(path);
	}
	
	/**
	 * 根据指定的项目ID生成保存用相对路径
	 * @param prefix 路径前缀
	 * @param itemId 项目ID
	 * @return String 保存用相对路径(/avatar/FF/000000/)
	 * @throws Exception
	 */
	public static String getSavePath(String prefix, String itemId) {
		
		prefix = prefix == null ? "" : prefix;
		String folderName = "";
		if(StringUtil.isNullOrEmpty(itemId)) {
			// 按当前日期生成目录结构
			String curDate = DateUtil.getDate(new Date());
			folderName = MessageFormat.format("{0}/{1}/{2}/", Arrays.asList(curDate.split("-")).toArray());
		} else{
			String tmpId = itemId.length() <= 3 ? itemId : itemId.substring(3);
			if(NumberUtils.isDecimal(tmpId)) {
				long longId = Long.parseLong(tmpId);
				folderName = "00" + Long.toHexString(longId % 255).toUpperCase();
				folderName = folderName.substring(folderName.length() - 2, folderName.length());
				folderName = folderName + "/" + Long.toString(longId) + "/";
			} else{
				// 按当前日期生成目录结构
				String curDate = DateUtil.getDate(new Date());
				folderName = MessageFormat.format("{0}/{1}/{2}/", Arrays.asList(curDate.split("-")).toArray());
			}
		}
		folderName = prefix + (prefix.endsWith("/") ? "" : "/") + folderName;
		
		return folderName;
	}
	
	/**
	 * 生成上传文件相对路径<br/>
	 * 根据上传类型，功能模块编码，项目ID<br/>
	 * @param type 上传类型  
	 * content: 内容详情（包括简介，列表图片，详情图片等）；
	 * avatar: 头像；
	 * file: 文件；
	 * other: 其他功能图片（如客服图片等）<br/>
	 * @param moduleCode 功能模块编码(业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告)
	 * @param itemId 项目ID
	 * @return String 保存用相对路径(html/02/BD/90999999/或/html/03/img/2016/12/18/7e215a9b2e4046e397f4d36c603d7a5d.jpg)
	 * @throws Exception
	 */
	public static String getUploadPath(String type, String moduleCode, String itemId) {
		
		if(StringUtil.isBlank(type)) {
			return null;
		}
		
		// 获取路径前缀（功能前缀）
		String prefix = "";
		switch(type) {
			case ZzjConstants.UPLOAD_TYPE_CONTENT:
				// 内容详情（包括简介，列表图片，详情图片等）
				prefix = "html/{0}/img/";
				break;
			case ZzjConstants.UPLOAD_TYPE_HTML:
				// HTML文件
				prefix = "html/{0}/";
				break;
			case ZzjConstants.UPLOAD_TYPE_AVATAR:
				// 头像
				prefix = "avatar/";
				break;
			case ZzjConstants.UPLOAD_TYPE_FILE:
				// 文件
				prefix = "file/{0}/";
				break;
			case ZzjConstants.UPLOAD_TYPE_SLIDE:
				// 轮播
				prefix = "slide/img/";
				break;
			case ZzjConstants.UPLOAD_TYPE_CHAT_IMG:
				// 聊天图片
				prefix = "chat/img/";
				break;
			case ZzjConstants.UPLOAD_TYPE_CHAT_AUDIO:
				// 聊天图片
				prefix = "chat/audio/";
				break;
			case ZzjConstants.UPLOAD_TYPE_OTHER:
				// 其他功能图片（如客服图片等）
				prefix = "img/";
				break;
			default:
				break;
		}
		
		// 功能模块路径设置
		prefix = MessageFormat.format(prefix, new Object[]{moduleCode});
		
		return getSavePath(prefix, itemId);
	}

	/**
	 * 去掉html标签
	 * @param  变量名，做什么用的
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        String space_ptn = "&nbsp;";
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        
        Pattern p_space=Pattern.compile(space_ptn,Pattern.CASE_INSENSITIVE); 
        Matcher m_space=p_space.matcher(htmlStr); 
        htmlStr=m_space.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    }
	
	/**
	 * 过滤空格回车换行
	 * @param  变量名，做什么用的
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
    public static String StringFilter(String   str) throws Exception {     
    	 Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
         Matcher m = p.matcher(str);  
         return m.replaceAll("");  
    }
    
	/**
	 * 生成上传文件本地保存路径<br/>
	 * 根据上传类型，功能模块编码，项目ID<br/>
	 * @param type 上传类型  
	 * content: 内容详情（包括简介，列表图片，详情图片等）；
	 * avatar: 头像；
	 * file: 文件；
	 * other: 其他功能图片（如客服图片等）<br/>
	 * @param moduleCode 功能模块编码(业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告)
	 * @param itemId 项目ID
	 * @return String 保存用路径(Z:/html/02/BD/90999999/)
	 * @throws Exception
	 */
	public static String getLocalUploadPath(String type, String moduleCode, String itemId) throws Exception {
		String relativePath = getUploadPath(type, moduleCode, itemId);
		String localPath = PropertyUtil.getConfigValue(ZzjConstants.RESOURCE_PATH);
		localPath = localPath.endsWith("/") ? localPath : localPath + "/";
		
		return localPath + relativePath;
	}
	
	/**
	 * 取得直播码
	 * @return 随机生成的直播码
	 * @throws Exception
	 */
	public static String getLiveCode() throws Exception {
		return stringToMD5(String.valueOf(Math.random())).substring(0, 10);
	}
}

/**
 * UTF16解码辅助类
 * 
 */
class LZData {
	  public int val;
	  public String string;
	  public int position;
	  public int index;

	  public static LZData getInstance() {
	    return new LZData();
	  }
}

