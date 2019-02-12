package com.taotao.order.interceptor;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户是否登录的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;
    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private UserService userService;
    
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //执行handler之前先指定此方法
        //1.从cookie中取token信息
        String token = CookieUtils.getCookieValue(httpServletRequest, TOKEN_KEY);
        //2.如果取不到token，跳转到sso的登录页面，需要把当前请求的url作为参数传递给sso，sso登录成功之后跳转回当前请求页面
        if(StringUtils.isBlank(token)){
            //取当前请求的url
            String requestURL = httpServletRequest.getRequestURL().toString();
            //跳转到登录页面
            httpServletResponse.sendRedirect(SSO_URL + "/page/login?url=" + requestURL);
            //拦截
            return false;
        }
        //3.取到token，调用sso系统的服务，判断用户是否登录
        TaotaoResult taotaoResult = userService.getUserByToken(token);
        //4.如果用户是未登录状态，即没取到用户信息，跳转到sso的登录页面
        if(taotaoResult.getStatus() != 200){
            String requestURL = httpServletRequest.getRequestURL().toString();
            // 跳转到登录页面
            httpServletResponse.sendRedirect(SSO_URL + "/page/login?url=" + requestURL);
            //拦截
            return false;
        }
        //5.如果取到登录信息。放行
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //handler执行之后，ModeAndView返回之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //ModeAndView返回之后，异常处理
    }
}
