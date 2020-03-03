package com.hust13.wishbottle.interceptor;

import com.hust13.wishbottle.model.OpenIdJson;
import com.hust13.wishbottle.service.TreeholeService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于保存浏览历史记录的拦截器
 */
@Component
public class HistoryInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TreeholeService treeholeService;

    //请求方法完成后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String key = request.getHeader("auth");
        //从redis中获取数据
        Object keyInRedis = null;
        if(key != null)
            keyInRedis = redisTemplate.opsForValue().get(key);
        //auth认证通过才执行下面代码保存浏览记录
        if(keyInRedis != null){
            //获取openid
            OpenIdJson data =  (OpenIdJson) keyInRedis;
            String openid = data.getOpenid();
            //获取用户id
            Integer userId = userService.getUserIdByOpenId(openid);
            //获取url
            String url =  request.getRequestURI();
            //获取id变量
            String idStr = url.substring(url.lastIndexOf("/") + 1);
            Integer treeholeId = Integer.parseInt(idStr);
            //保存历史记录
            Integer ret = treeholeService.saveHistory(userId, treeholeId);
        }
    }
}
