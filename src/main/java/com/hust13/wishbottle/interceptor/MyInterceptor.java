package com.hust13.wishbottle.interceptor;

import com.alibaba.fastjson.JSON;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.model.OpenIdJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 请求拦截器 用于分析header中用户标识
 * created by wzy on 2020/2/20
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getHeader("auth");

        //设置错误返回数据
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;

        Model model = new Model();
        model.setCode(1);
        model.setData("");
        model.setMsg("请求头中未包含身份认证或已失效");
        String jsonString = JSON.toJSONString(model);

        //若请求头中无auth字段则不放行
        if(key == null){
            out = response.getWriter();
            out.append(jsonString);
            return false;
        }
        //从redis中获取数据
        Object keyInRedis = redisTemplate.opsForValue().get(key);
        //用户标识失效或不正确
        if(keyInRedis == null){
            out = response.getWriter();
            out.append(jsonString);
            return false;
        }

        //获取openid
        OpenIdJson data =  (OpenIdJson) keyInRedis;
        String openid = data.getOpenid();
        request.setAttribute("openid", openid);
        return true;
    }

}
