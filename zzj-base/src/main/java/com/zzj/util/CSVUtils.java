/**
 * Project Name:zzj-base
 * File Name:Constants.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * <p><strong>类名: </strong></p>CSVUtils <br/>
 * <p><strong>功能说明: </strong></p>文件导出<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午6:52:11 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CSVUtils {

    /**
     * CSV文件生成方法
     * @param list 输出数据
     * @param outPutPath 路径
     * @param filename 文件名
     * @return File 文件
     */
    public static File createCSVFile(List<ArrayList<String>> list, String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件内容
            for (List<String> row : list) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     * @param row 一航数据
     * @param csvWriter
     * @throws IOException
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    private static void writeRow(List<String> row, BufferedWriter csvWriter) throws IOException, IllegalArgumentException, IllegalAccessException {
        // 写入文件头部
        StringBuffer sb = new StringBuffer();
        for (String data : row) {
            sb.append("\"").append(data).append("\",").toString();
        }
        csvWriter.write(sb.toString());      
        csvWriter.newLine();
    }
    
    /**
     * 导出文件
     * @param filePath 路径
     * @param response http应答
     * @param isOnLine 打开方式
     * @throws Exception
     */
    public static void exportToExcel(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {      	
    	File f = new File(filePath);  
    	BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
    	byte[] buf = new byte[1024];
    	int len = 0;
    	response.reset();
    	// 在线打开方式  
    	if (isOnLine) { 
    		URL u = new URL(filePath); 
    		response.setContentType(u.openConnection().getContentType());
    		// 文件名应该编码成UTF-8  
    		response.setHeader("Content-Disposition", "inline; filename=" + toUTF8(f.getName()));  
    	}
        // 纯下载方式  
        else {
        	response.setContentType("application/x-msdownload");
        	// 文件名应该编码成UTF-8
        	response.setHeader("Content-Disposition", "attachment; filename=" + toUTF8(f.getName()));
        }
    	OutputStream out = response.getOutputStream();
    	while ((len = br.read(buf)) > 0)
    	{
    		out.write(buf, 0, len);  
    	}    		
    	out.flush();  
        br.close();  
        out.close();  
     }
    
    /**
     * UTF-8编码
     * @param  s 文件
     * @return UTF8字符串
     */
    public static String toUTF8(String s) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            if (c >= 0 && c <= 255) {  
                sb.append(c);  
            } else {  
                byte[] b;  
                try {  
                    b = Character.toString(c).getBytes("utf-8");  
                } catch (Exception ex) {  
                    System.out.println(ex);  
                    b = new byte[0];  
                }  
                for (int j = 0; j < b.length; j++) {  
                    int k = b[j];  
                    if (k < 0)
                    {
                    	k += 256; 
                    }                         
                    sb.append("%" + Integer.toHexString(k).toUpperCase());  
                }  
            }  
        }  
        return sb.toString();  
    } 

}