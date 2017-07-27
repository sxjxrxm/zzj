/**
 * Project Name:zzj-base
 * File Name:DateUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/
package com.zzj.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p><strong>类名: </strong></p>NumberUtils <br/>
 * <p><strong>功能说明: </strong></p>数值类型操作工具类<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月14日下午2:17:09 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class NumberUtils {

	// ------------------------------------------------------------------
	/**
	 * <PRE>
	 * 字符串转换为整数
	 * </PRE>
	 * 
	 * @param str
	 *            字符串
	 * @return int（不能转换时，返回0）
	 */
	public static int convertToInt(String str) {
		return Integer.parseInt(str);
	}

	/**
	 * <PRE>
	 * 字符串转换为整数
	 * </PRE>
	 * 
	 * @param str
	 *            字符串
	 * @return int（不能转换时，返回0）
	 */
	public static BigDecimal convertToDec(String str) {

		BigDecimal decimal = new BigDecimal(str);

		return decimal;
	}
	/**
	 * <PRE>
	 * 数字转换为字符串
	 * </PRE>
	 * 
	 * @param bigDecimal
	 *            数字
	 * @return BigDecimal（不能转换时，返回“”）
	 */
	public static String convertToStr(BigDecimal bigDecimal) {
		String rtn = "";
		if (bigDecimal != null) {
			rtn = bigDecimal.toPlainString();
		}
		return rtn;
	}
	/**
	 * <PRE>
	 * 数字转换为字符串
	 * </PRE>
	 * 
	 * @param bigDecimal 数字
	 * @return BigDecimal（不能转换时，返回“”）
	 */
	public static String formatDecimal(BigDecimal bigDecimal) {
		String rtn = "";
		if (bigDecimal != null) {
			rtn = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		}
		return rtn;
	}
	/**
	 * <PRE>
	 * 数字转换为字符串
	 * </PRE>
	 * 
	 * @param integer
	 *            数字
	 * @return BigDecimal（不能转换时，返回“”）
	 */
	public static String convertToStr(Integer integer) {
		String rtn = "";
		if (integer != null) {
			rtn = integer.toString();
		}
		return rtn;
	}
	/**
	 * <PRE>
	 * 判断是否为数字
	 * </PRE>
	 * 
	 * @param str
	 *            字符串
	 * @return 验证结果（true：成功，false：失败）
	 */
	public static boolean validInteger(String value) {
		Pattern pattern = Pattern.compile("^[+-]?[0-9]+");
		Matcher matcher = pattern.matcher((CharSequence) value);
		boolean result = matcher.matches();
		return result;
	}

	/**
	 * <PRE>
	 * 判断是否为decimal
	 * </PRE>
	 * 
	 * @param str
	 *            字符串
	 * @return 验证结果（true：成功，false：失败）
	 */
	public static boolean validDecimal(String value) {
		Pattern pattern = Pattern.compile("^[+-]?[0-9]+(\\.[0-9]+)([Ee]?[1-9]+?)");
		Matcher matcher = pattern.matcher((CharSequence) value);
		boolean result = matcher.matches();
		return result;
	}
	
	/**
	 * 判断是否为数字
	 * @param  str 数字
	 * @return boolean 是数字true，不是false
	 * @author 张赫东
	 */
	public static boolean isDecimal(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public static void main(String[] args) {
		boolean result = validDecimal("+1.2345532");
		System.out.println(result);
	}
}
