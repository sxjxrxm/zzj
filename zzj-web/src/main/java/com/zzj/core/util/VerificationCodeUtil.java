/**
 * Project Name:zzj-web
 * File Name:VerificationCodeUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>VerificationCodeUtil <br/>
 * <p><strong>功能说明: </strong></p>这个类是用于生成验证码. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月15日下午6:10:41 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class VerificationCodeUtil {

	/**
	 * 
	 * 生成验证码。<br/>
	 * @param  request 请求数据， response 相应数据，返回验证码图片， session存放验证码信息strCode
	 * @throws Exception
	 */
	public static void getAuthCode(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
        int width = 55;
        int height = 28;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,24));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<ZzjConstants.AUTHCODE_LENGTH;i++){
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+1, 22);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("strCode", strCode);
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();

    }
	
	
	/**
	 * 创建颜色。<br/>
	 */
    private static Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
	
}

