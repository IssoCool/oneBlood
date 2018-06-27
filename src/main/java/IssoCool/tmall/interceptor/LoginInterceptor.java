package IssoCool.tmall.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import IssoCool.tmall.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	/** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */   
    public boolean preHandle(HttpServletRequest request,   
            HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //获取ServletContext的相对路径:/my_ssm
        String contextPath = session.getServletContext().getContextPath();  
        String[] noNeedAuthPage = new String[] {
        		"home",
                "checkLogin",
                "register",
                "loginAjax",
                "login",
                "product",
                "category",
                "search"    };
        //获取路径: /my_ssm/路径
        String uri = request.getRequestURI();
        //去掉前缀/my_ssm
        uri = StringUtils.remove(uri, contextPath);
        //如果以"/fore"开头
        if(uri.startsWith("/fore")) {
        	//截取“/fore”之后的字符串
        	String method = StringUtils.substringAfterLast(uri, "/fore");
        	//判断method是否没在noNeedAuthPage中
        	if(!Arrays.asList(noNeedAuthPage).contains(method)) {
        		//判断是否已经登录，否则客户端跳转
        		User user =(User) session.getAttribute("user");
                if(null==user){
                    response.sendRedirect("loginPage");
                    return false;
                }
        	}
        }
        return true;
         
    } 
 
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
     */ 
     
    public void postHandle(HttpServletRequest request,   
            HttpServletResponse response, Object handler,   
            ModelAndView modelAndView) throws Exception { 
    } 
 
    /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *  
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */
     
    public void afterCompletion(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex) 
    throws Exception { 
    } 
}
