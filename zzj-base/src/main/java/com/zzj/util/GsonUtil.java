/**
 * Project Name:zzj-base
 * File Name:GsonUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * <p><strong>类名: </strong></p>GsonUtil <br/>
 * <p><strong>功能说明: </strong></p>Gson工具类，便于操作JSON。<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月11日下午5:42:36 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class GsonUtil {
	/**
	 * 持久层类的报名
	 * **/
	public static String BEAN_PACKAGE = "com.zzj";
	/**
	 * 静态实例化Gson
	 * **/
	public static Gson gson = new Gson();
	/**
	 * 静态实例化JsonParser
	 * **/
	static JsonParser parser = new JsonParser();
	/**
	 * 将JSON串转换成数据类型为Map的List集合。<br/>
	 * @param jsonString 需要转换的JSON串
	 * @return List<Map<String, Object>> 转换后的Map的List集合
	 * **/
	public static Map<String, List<?>>  createBeanFromJson(String jsonString) throws ClassNotFoundException{
		Map<String,List<?>> resultMap = new HashMap<String,List<?>>();
		JsonObject jsonObject = GsonUtil.getBeanFromJson(jsonString,JsonObject.class);
		Set<Map.Entry<String, JsonElement>> set = jsonObject.entrySet();
		for (Map.Entry<String, JsonElement> entry : set) {
			String className = entry.getKey();
			Class<?> targetClass = Class.forName(BEAN_PACKAGE + className);
			JsonElement jsonElement = entry.getValue();
            JsonArray jsonArray = (JsonArray) jsonElement;
            if(null!=jsonArray&&jsonArray.size()>0){
            	List<Object> beanList = new ArrayList<Object>(jsonArray.size());
            	for (JsonElement je : jsonArray) {
                    //根据Json类型解析json
                    Object object = gson.fromJson(je, targetClass);
                    beanList.add(object);
    			}
            	resultMap.put(className,beanList);
            }
		}
		return resultMap;
	}

	/**
	 * 将java对象实例转换成JSON字符串。<br/>
	 * @param value java对象实例
	 * @return String JSON字符串
	 * **/
	public static String getJsonStringFormBean(Object value) {
		String Str = gson.toJson(value);
		return Str;
	}
	
	/**
	 * 将实例数组转换成JSON字符串。<br/>
	 * @param value java对象数组
	 * @return String JSON字符串
	 * **/
	public static String getJsonStringFormArray(Object[] value) {
		String Str = gson.toJson(value);
		return Str;
	}
	/**
	 * 将json转换成对应的javabean列表。<br/>
	 * @param json 数组JSON对象
	 * @param targetClass 需要转成的JAVABEAN
	 * @return List<Object> 转换后的JAVA类实例列表
	 * **/
	public static <T> List<T> getBeanList(String json,Class<T> targetClass) {
		List<T> beanList = null;
		JsonElement el = parser.parse(json);
		JsonArray jsonArray = null;
		if(el.isJsonArray()){
			jsonArray = el.getAsJsonArray();
			Iterator<JsonElement> it = jsonArray.iterator();
			beanList = new ArrayList<T>(jsonArray.size());
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				//JsonElement转换为JavaBean对象
				T obj = gson.fromJson(e, targetClass);
				beanList.add(obj);
			}
		}
		return beanList;
	}
	
	/**
	 * 将JsonElement转换成对应的javabean。<br/>
	 * @param je JSON对象
	 * @param targetClass 需要转成的JAVABEAN
	 * @return Object 转换后的JAVA类实例
	 * **/
	public static <T> T getBeanFormJsonElement(JsonElement je,Class<T> targetClass) {
		T object = gson.fromJson(je, targetClass);
		return object;
	}
	
	
	/**
	 * 将JSON串转换成对应的javabean。<br/>
	 * @param jsonString 需要转换的JSON串
	 * @tClass 需要转换成的java类
	 * @return T 转换后的JAVA类实例
	 * **/
	public static <T> T getBeanFromJson(String jsonString, Class<T> tClass) {
		T t = null;
		try {
			t = gson.fromJson(jsonString, tClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * 将JSON串转换成对应的javabean集合。<br/>
	 * @param jsonString 需要转换的JSON串
	 * @tClass 需要转换成的List集合中的数据类型
	 * @return List<T> 转换后的JAVA类实例集合
	 * **/
	public static <T> List<T> getListFormJson(String jsonString, Class<T> tClass) {
		List<T> list = null;
		try {
			list = gson.fromJson(jsonString, new TypeToken<List<T>>(){}.getType());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 将JSON串转换成数据类型为Map的List集合。<br/>
	 * @param jsonString 需要转换的JSON串
	 * @return List<Map<String, Object>> 转换后的Map的List集合
	 * **/
	public static List<Map<String, Object>> getKeyMapList(String jsonString) {
		List<Map<String, Object>> list = null;
		try {
			list = gson.fromJson(jsonString, (new TypeToken<List<Map<String, Object>>>(){}).getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 将JSON串转换成数据类型为Map集合。<br/>
	 * @param jsonString 需要转换的JSON串
	 * @return Map<String, Object> 转换后的Map集合
	 * **/
	public static Map<String, Object> getKeyMap(String jsonString) {
		Map<String, Object> list = null;
		try {
			list = gson.fromJson(jsonString, (new TypeToken<Map<String, Object>>(){}).getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 将JSON串转换成数据类型为Map集合。<br/>
	 * @param jsonString 需要转换的JSON串
	 * @return Map<String, String> 转换后的Map集合
	 * **/
	public static Map<String, String> getKeyMapString(String jsonString) {
		Map<String, String> list = null;
		try {
			list = gson.fromJson(jsonString, (new TypeToken<Map<String, String>>(){}).getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

