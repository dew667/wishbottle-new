package com.hust13.wishbottle.interceptor;

import com.alibaba.fastjson.JSON;
import com.hust13.wishbottle.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 心愿瓶拦截器 用于限制用户每日捞取次数
 * created by wzy on 2020/2/28
 */
@Component
public class BottleInterceptor implements HandlerInterceptor{

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 限制每日捞取次数
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户的openid
        String openid = (String) request.getAttribute("openid");

        //判断缓存中是否已有该键
        if(!redisTemplate.hasKey(openid)){
            //如果没有 则新增缓存键值对
            //获取当前时分秒
            Calendar now = Calendar.getInstance();
            Integer hour = now.get(Calendar.HOUR_OF_DAY);
            Integer minute = now.get(Calendar.MINUTE);
            Integer second = now.get(Calendar.SECOND);
            //计算今天还剩多少时间-秒
            Integer limitTime = 24*60*60 - (hour*60*60 + minute*60 + second);
            //设置缓存 openid为key 捞取次数限制为20次 限制时间至明日0时
            redisTemplate.opsForValue().set(openid, 19, limitTime, TimeUnit.SECONDS);
            //放行
            return true;
        }

        //如果已有该键 则判断捞取次数剩余是否为0
        Integer rest = (Integer) redisTemplate.opsForValue().get(openid);
        //如果剩余次数为0 则不放行
        if(rest <= 0){
            //设置错误返回数据
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = null;
            Model model = new Model();
            model.setCode(1);
            model.setData("");
            model.setMsg("剩余捞取次数已不足 请明日再来吧");
            String jsonString = JSON.toJSONString(model);
            out = response.getWriter();
            out.append(jsonString);
            return false;
        }
        else {
            //如果剩余次数大于0 则剩余次数减1且放行
            redisTemplate.opsForValue().decrement(openid, 1);
            return true;
        }
    }
}
