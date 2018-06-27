package IssoCool.tmall.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import IssoCool.tmall.pojo.Category;
import IssoCool.tmall.pojo.OrderItem;
import IssoCool.tmall.pojo.User;
import IssoCool.tmall.service.CategoryService;
import IssoCool.tmall.service.OrderItemService;

public class OtherInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	CategoryService categoryService;
	@Autowired
    OrderItemService orderItemService;
	
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
				return true;
    } 
 
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
     */ 
     
    public void postHandle(HttpServletRequest request,   
            HttpServletResponse response, Object handler,   
            ModelAndView modelAndView) throws Exception { 
    	/*获取分类集合信息放在搜索栏下*/
    	List<Category> cs = categoryService.list();
    	request.getSession().setAttribute("cs",cs);
    	
    	/*给变形金刚图片设置跳转到首页*/
    	HttpSession session = request.getSession();
    	String contextPath = session.getServletContext().getContextPath();
    	session.setAttribute("contextPath", contextPath);
    	
    	/*获取购物车物品数量*/
    	User user = (User) session.getAttribute("user");
    	int cartTotalItemNumber =0;
    	if(null != user) {
    		List<OrderItem> ois = orderItemService.listByUser(user.getId());
    		for(OrderItem oi:ois) {
    			cartTotalItemNumber+=oi.getNumber();
    		}
    	}
    	session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
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
