/**
 * Project Name:zzj-base
 * File Name:DateUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/
package com.zzj.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p><strong>类名: </strong></p>ValidateUtils <br/>
 * <p><strong>功能说明: </strong></p>验证工具类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月14日下午2:17:09 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ValidateUtils {

	/**
	 * 邮箱验证
	 * @param Mail 邮箱
	 * @return 验证结果：true 成功 失败 false
	 */
	public static boolean validMail(String value) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher matcher = pattern.matcher(value);
		boolean result = matcher.matches();
		return result;
	}

	/**
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * 
	 * @param mobile
	 *            移动、联通、电信运营商的号码段
	 *            <p>
	 *            移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 *            、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
	 *            </p>
	 *            <p>
	 *            联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
	 *            </p>
	 *            <p>
	 *            电信的号段：133、153、180（未启用）、189
	 *            </p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean validCellphone(String mobile) {
		String regex = "(\\+\\d+)?1[3458]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}

	/**
	 * 区号+座机号码+分机号码
	 * 
	 * @param fixedPhone
	 * @return
	 */

	public static boolean validTelphone(String fixedPhone) {
		String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|"
				+ "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
		return Pattern.matches(reg, fixedPhone);
	}

	/**
	 * 匹配中国邮政编码
	 * 
	 * @param postcode
	 *            邮政编码
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean validZipcode(String postCode) {
		String reg = "[1-9]\\d{5}";
		return Pattern.matches(reg, postCode);
	}

	/**
	 * 检查身份证是否有效
	 * 
	 * @param IDCard
	 *            身份证号码
	 * @return 验证结果：true 成功 失败 false
	 */
	public static boolean validIDCard(String IDCard){
		IdcardValidator iv = new IdcardValidator();
		return iv.isValidatedAllIdcard(IDCard);
	}
	
	/**
	 * 根据身份证号码取得生日
	 * 
	 * @param IDCard
	 *            身份证号码
	 * @return 结果：生日日期
	 */
	public static Date getBirthdayFromIDCard(String IDCard)
	{
		IdcardInfoExtractor ie = new IdcardInfoExtractor(IDCard);
		return ie.getBirthday();
	}
	
	/**
	 * 根据身份证号码计算年龄
	 * 
	 * @param IDCard
	 *            身份证号码
	 * @return 结果：年龄
	 */
	public static int getAgeFromIDCard(String IDCard)
	{
		IdcardInfoExtractor ie = new IdcardInfoExtractor(IDCard);
		return ie.getAge();
	}
	
	//Test用
	public static void main(String[] args)
	{
		Date date = getBirthdayFromIDCard("220524198209042013");
		System.out.println(date);
	}
}
