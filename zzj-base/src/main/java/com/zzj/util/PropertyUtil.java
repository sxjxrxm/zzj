/**
 * Project Name:zzj-base
 * File Name:PropertyUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.util;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <p><strong>类名: </strong></p>PropertyUtil <br/>
 * <p><strong>功能说明: </strong></p>Dubbo层用属性文件工具类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年5月10日下午2:05:09 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class PropertyUtil {
	/**
	 * ResourceBundle Map
	 */
    private static Map<String, ResourceBundle> bundleMap = new HashMap<>();
    
	/**
	 * 错误信息的配置文件名
	 */
	public static final String CONFIG_FILE_NAME = "config";
    
	/**
	 * 消息的配置文件名
	 */
	public static final String MSG_FILE_NAME = "message";
	
	/**
	 * 读取编码：ISO-8859-1
	 */
	public static final String READ_ENCODE = "ISO-8859-1";
	
	/**
	 * 文件编码：UTF-8
	 */
	public static final String FILE_ENCODE = "UTF-8";

    /**
     * 指定属性文件取得。<br/>
     * 
     * @param  propertyName 属性文件名称
     * @return ResourceBundle
     * @throws Exception
     */
    private static ResourceBundle getBundle(String propertyName){
        if(!bundleMap.containsKey(propertyName)){
                bundleMap.put(propertyName, ResourceBundle.getBundle(propertyName));
        }
        return bundleMap.get(propertyName);
    }

    /**
     * 指定属性文件中指定key对应的value取得。<br/>
     * 
     * @param  propertyName 属性文件名称
     * @param  key 属性文件中的key值
     * @return String 属性文件中的key对应的值 <br/>属性文件不存在或者Key对应的值没有的场合，返回空白
     * @throws Exception
     */
    public static String getString(String propertyName, String key) throws Exception{
    	ResourceBundle bundle = getBundle(propertyName);
    	if(bundle == null || !bundle.containsKey(key))
    	{
    		// 配置文件或者key不存在的场合，返回空白
    		return "";
    	}
    	
    	return new String(bundle.getString(key).getBytes(READ_ENCODE), FILE_ENCODE);
    }

    /**
     * 指定消息文件中指定key对应的消息内容取得。(message.properties)<br/>
     * 
     * @param  key 属性文件中的key值
     * @param  msgArgs 消息参数，没有的场合设置为NULL
     * @return String 属性文件中的key对应的值 <br/>属性文件不存在或者Key对应的值没有的场合，返回空白
     * @throws Exception
     */
    public static String getMessageContent(String key, Object[] msgArgs){
    	ResourceBundle bundle = getBundle(MSG_FILE_NAME);
    	if(bundle == null || !bundle.containsKey(key))
    	{
    		// 配置文件或者key不存在的场合，返回空白
    		return "";
    	}
    	
    	// 取得消息内容
    	String msgContent = "";
		try {
			msgContent = new String(bundle.getString(key).getBytes(READ_ENCODE), FILE_ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	// 有消息参数的场合，根据消息参数补完消息内容
    	if(!StringUtil.isNullOrEmpty(msgContent) && msgArgs != null && msgArgs.length > 0) {
    		msgContent = MessageFormat.format(msgContent, msgArgs);
    	}
    	
    	return msgContent;
    }

    /**
     * 取得配置文件中指定Key的值(config.properties)。<br/>
     * 
     * @param  key 属性文件中的key值
     * @return String 属性文件中的key对应的值 <br/>属性文件不存在或者Key对应的值没有的场合，返回空白
     * @throws Exception
     */
    public static String getConfigValue(String key) throws Exception{
    	ResourceBundle bundle = getBundle(CONFIG_FILE_NAME);
    	if(bundle == null || !bundle.containsKey(key))
    	{
    		// 配置文件或者key不存在的场合，返回空白
    		return "";
    	}
    	
    	return new String(bundle.getString(key).getBytes(READ_ENCODE), FILE_ENCODE);
    }

    /**
     * 取得配置文件中指定Key的值(config.properties)。<br/>
     * 
     * @param  key 属性文件中的key值
     * @param  defaultValue 找不到时的默认值
     * @return double 属性文件中的key对应的值 <br/>属性文件不存在或者Key对应的值没有的场合，返回空白
     * @throws Exception
     */
    public static double getConfigDoubleValue(String key, double defaultValue) throws Exception{

    	double doubleValue = defaultValue;
    	
    	// 从配置文件中取得配置值
    	String configValue = getConfigValue(key);
    	try{
    		defaultValue = StringUtil.isBlank(configValue) ? defaultValue : Double.parseDouble(configValue);
    	} catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return doubleValue;
    }
    
    /**
     * http消息内容取得。<br/>
     * 
     * @param  key 属性文件中的key值
     * @param  msgArgs 消息参数，没有的场合设置为NULL
     * @return String http消息内容 <br/>没有的场合，返回空白
     */
    public static String getHttpContent(String key, Object[] msgArgs) {

    	if(StringUtil.isNullOrEmpty(key))
    	{
    		// 配置文件或者key不存在的场合，返回空白
    		return "";
    	}
    	
    	// 取得消息内容
    	String msgContent = key;
    	// 有消息参数的场合，根据消息参数补完消息内容
    	if(!StringUtil.isNullOrEmpty(msgContent) && msgArgs != null && msgArgs.length > 0) {
    		msgContent = MessageFormat.format(msgContent, msgArgs);
    	}
    	
    	return msgContent;
    }
    
    public static void main(String[] args){
    }
}