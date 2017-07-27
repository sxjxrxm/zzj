/**
 * Project Name:zzj-base
 * File Name:DateUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/
package com.zzj.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p><strong>类名: </strong></p>SerializUtils <br/>
 * <p><strong>功能说明: </strong></p>序列化工具类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月16日下午2:17:09 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class SerializUtils {

	/**
	 * 
	 * 序列化实体
	 * @param  变量名，做什么用的
	 * @return 数组
	 * @throws IOException 
	 * @throws Exception
	 */
	public static byte[] serializ(Object object) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		ObjectOutputStream os = new ObjectOutputStream(bos);  
		os.writeObject(object);
		os.flush();  
		os.close();  
		byte[] b = bos.toByteArray();  
		bos.close();
		return b;
	}
	
	/**
	 * 反序列化方法
	 * @param  变量名，做什么用的
	 * @return 返回对象
	 * @throws Exception
	 */
	public static <T> T deserialize(byte[] b, Class<T> targetClass) throws ClassNotFoundException, IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(in);
		@SuppressWarnings("unchecked")
		T t = (T)ois.readObject();
		in.close();
		ois.close();
		return t;
	}
}
