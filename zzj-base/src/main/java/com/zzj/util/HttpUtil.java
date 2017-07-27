/**
 * Project Name:yuntong-noti
 * File Name:HttpUtil.java
 * Package Name:com.yuntong.util
 * Copyright © 北京云彤科技有限公司  All Rights Reserved.
*/
package com.zzj.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * <p><strong>类名: </strong></p>HttpUtil <br/>
 * <p><strong>功能说明: </strong></p>Http通信类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月5日下午1:09:01 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@SuppressWarnings("deprecation")
public class HttpUtil {
	public static String post(String url, String body, Map<String,String> headers) {
		
		PostMethod post = new PostMethod(url);
		post.setRequestBody(body);// 这里添加xml字符串

		return method(post, headers);
	}
	
	public static String get(String url, Map<String,String> headers) {
		GetMethod get = new GetMethod(url);
		return method(get, headers);
	}
	
	public static String delete(String url, Map<String,String> headers) {
		DeleteMethod delete = new DeleteMethod(url);
		return method(delete, headers);
	}
	
	public static String put(String url, Map<String,String> headers) {
		return put(url, null, headers);
	}
	public static String put(String url, String body, Map<String,String> headers) {
		PutMethod put = new PutMethod(url);
		put.setRequestBody(body);
		return method(put, headers);
	}
	
	private static String method(HttpMethod method, Map<String,String> headers){
		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		
		HttpClient httpclient = new HttpClient();// 创建 HttpClient 的实例
		String res = null;
		try {
			for(Entry<String, String > entry : headers.entrySet()){
				method.setRequestHeader(entry.getKey(), entry.getValue());
			}
			
			httpclient.executeMethod(method);

			InputStream inputStream = method.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
			StringBuffer stringBuffer = new StringBuffer();  
			String str= "";  
			while((str = br.readLine()) != null){  
				stringBuffer.append(str);  
			}
			res = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		method.releaseConnection();// 释放连接
		return res;
	}
	
	public static void main(String[] args){
		
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ",  
                Locale.ENGLISH);
		
		String url = "https://api-testing.qlear.build/v1/readings";
		JSONObject json = new JSONObject();
		json.put("token", "538f2765-617d-48bf-a55c-707c7d151f18");
		json.put("monitor_id", "f58mkRCEWckMj8Drp2mfT3");
		json.put("timestamp", dateFormat.format(new Date()));
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("pm2p5", 19);
		data.put("temperature", 32);
		data.put("humidity", 10);
		data.put("hcho", 8);
		data.put("co2", 438);
		data.put("tvoc", 655);
		data.put("reading_time", dateFormat.format(new Date(new Double(1480317669.372*1000).longValue())));
		json.put("data", data);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String response = HttpUtil.post(url, json.toJSONString(), headers);
		System.out.println("response = " + response);*/
		
		// 请求URL
		String url = PropertyUtil.getHttpContent(ZzjConstants.HTTP_CREATE_COURSE,
				new Object[] {"eJxFkF1PgzAYhf8LtxrTFnHUu20uFspiui2SetPUtbCifKTUDTX*97GGxdvnyZtzzvsb7LLtnew6o4R0IrQqeAxAcOuxHjpjtZCF03bEMIoiBMDVHrXtTduMAgEYQRQC8C*N0o0zhfGHUtWmmURvypGsV3yZsCdKqhqXLHteVEAth3LBQ6MTxvh7isrNiXBGVy*0vflZz5PDnL7i-vg20zkfbFo8MEYOLAebHKfpbNfD0zYmNOYdqT6za5j6EH7bpf392A7BEONJOlNrz2OE4MVOXO737VfjhPvutH-G3xlZ0VcE",
						"admin", ZzjConstants.SDKAPPID_YUNTONGXUN});
		
		JSONObject json = new JSONObject();		
		json.put("Owner_Account", "admin");
		json.put("Type", "ChatRoom");
		json.put("Name", "啊啊啊啊");	
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		String response = HttpUtil.post(url, json.toJSONString(), headers);
		String roomId = "";
		if (response.indexOf("OK") > 0)
		{
			roomId = response.substring(response.lastIndexOf("@"), response.lastIndexOf("@") + 14).trim();
		}
		System.out.println("response = " + response);
		System.out.println("roomId = " + roomId);
		
	}
}
