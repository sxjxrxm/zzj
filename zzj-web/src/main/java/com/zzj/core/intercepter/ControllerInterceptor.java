/**
 * Project Name:zzj-web
 * File Name:ControllerInterceptor.java
 * Package Name:com.zzj.core.intercepter
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zzj.core.web.service.AuthService;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>ControllerInterceptor <br/>
 * <p><strong>功能说明: </strong></p>MVC层请求拦截器. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午6:47:58 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 登录页面控制器
	 */
	private static String login = "login.htm";
	
	/**
	 * 注销页面控制器
	 */
	private static String logout = "logout.htm";
	
	/**
	 * 权限验证Service
	 */
	@Autowired
	private AuthService authService;
	
	/**
	 * 预处理<br/>
	 * 进行编码、安全控制等处理<br/>
	 * @param  request HTTP请求
	 * @param  response HTTP应答
	 * @param  handler 响应的处理器对象
	 * @return boolean 是否继续流程（true:继续流程;false:流程中断)
	 * @throws Exception
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// 取得当前访问路径
		String uri = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		// 判断是否是登录或者注销请求
		if(uri.endsWith(login) || uri.endsWith(logout) ||
			uri.endsWith(ZzjConstants.SYS_ERROR_URL) ||
			uri.endsWith(ZzjConstants.SYS_GOLOGIN_URL) ||
			uri.endsWith(ZzjConstants.SYS_SESSION_EXPIRED_URL)){
			// 登录或者注销的场合，处理继续
			return super.preHandle(request, response, handler);
			
		}else{
			// 以外的场合，判断是否已经登录过
			if(null == request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY)){
				// 未登录的场合
				
				//如果是ajax的会话过期,则返回sessionTimeOut标识
				if(null != request.getHeader("x-requested-with") 
						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
					PrintWriter writer = response.getWriter();
					response.setContentType("application/json; charset=UTF-8");
					writer.print("{\"sessionTimeout\":1}");
					writer.flush();
					writer.close();
				}else if(uri.endsWith(ZzjConstants.INDEX_URL)){
					// 首页访问的场合，跳转到登录页面
					response.sendRedirect(basePath + login);
				} else {
					// 跳转到错误页面
					response.sendRedirect(basePath + ZzjConstants.SYS_SESSION_EXPIRED_URL);
				}
				// 处理终端
				return false;
				
			}else{
				// 登录的场合
				// 判断用户权限
				if(this.doAuth(request, uri)){
					// 用户权限OK的场合，直接跳转到相应控制器处理
					return super.preHandle(request, response, handler);
				} else{
					
					// 用户权限不足的场合，直接跳转到权限错误画面
					response.sendRedirect(basePath + ZzjConstants.SYS_ERROR_URL);
					return false;
				}
			}
		}
	}
	
	/**
	 * 后处理回调方法<br/>
	 * 实现处理器的后处理（但在渲染视图之前）<br/>
	 * @param  request HTTP请求
	 * @param  response HTTP应答
	 * @param  handler 响应的处理器对象
	 * @param  modelAndView 视图对象
	 * @return 无
	 * @throws Exception
	 */
	public void postHandle(HttpServletRequest request,
			  HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 整个请求处理完毕回调方法<br/>
	 * 在视图渲染完毕时回调<br/>
	 * 如性能监控中我们可以在此记录结束时间并输出消耗时间，<br/>
	 * 还可以进行一些资源清理，类似于try-catch-finally中的finally，<br/>
	 * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion<br/>
	 * @param  request HTTP请求
	 * @param  response HTTP应答
	 * @param  handler 响应的处理器对象
	 * @param  ex 例外对象
	 * @return 无
	 * @throws Exception
	 */	  
	public void afterCompletion(HttpServletRequest request,
			  HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 验证该访问是否有权限
	 * @param  url 访问路径
	 * @return boolean 是否有访问权限(true:有;false:无)
	 * @throws Exception
	 */
	private boolean doAuth(HttpServletRequest request, String uri) throws Exception {
		return authService.isAuth(request, uri);
	}
}

