package com.hust13.wishbottle.config;

import com.hust13.wishbottle.interceptor.BottleInterceptor;
import com.hust13.wishbottle.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置 配置拦截器拦截规则
 */
@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private MyInterceptor myInterceptor;

    @Autowired
    private BottleInterceptor bottleInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/treehole/getArticleList/**", "/treehole/getRecommend/**",
                        "/treehole/getOneArticle/**", "/treeReply/getAllComments/**", "/treeReply/getCommentsOfAuthor/**",
                        "/treeReply/getAllReplies/**", "/admin/**");
        registry.addInterceptor(bottleInterceptor).addPathPatterns("/wishbottle/pick");
        super.addInterceptors(registry);
    }
}
