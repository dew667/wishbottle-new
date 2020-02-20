package com.hust13.wishbottle.interceptor;

import com.hust13.wishbottle.model.OpenIdJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求拦截器 用于分析heder中用户标识
 * created by wzy on 2020/2/20
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getHeader("auth");
        //若请求头中无auth字段则不放行
        if(key == null)
            return false;
        Object keyInRedis = redisTemplate.opsForValue().get(key);
        //用户标识失效或不正确
        if(keyInRedis == null)
            return false;
        //从redis获取openid
        OpenIdJson data =  (OpenIdJson) keyInRedis;
        String openid = data.getOpenid();
        request.setAttribute("openid", openid);
        return true;
    }

}
