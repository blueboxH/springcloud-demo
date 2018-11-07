package com.thgy.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thgy.common.result.ResultEnum;
import com.thgy.common.result.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;


/**
 * 检测token的过滤器,
 * 在启动入口加入相应的Bean或者 在这加上 @Component 注解
 */
@Component
public class ExceptionFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ExceptionFilter.class);

    // 过滤器的类型, 决定过滤器在请求的哪个生命周期中运行
    @Override
    public String filterType() {
        return ERROR_TYPE;
//        return POST_TYPE;
    }

    // 过滤器的执行顺序, 当请求在一个阶段中存在多个过滤器时, 决定执行的顺序
    @Override
    public int filterOrder() {
        // 数字越小优先级越高
        return 100;
    }

    // 判断该过滤器是否需要被执行
    @Override
    public boolean shouldFilter() {

        return false;
    }

    // 过滤器的具体实现逻辑
    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable cause = ctx.getThrowable().getCause();

        if (cause instanceof ResultException){
            ctx.setResponseBody(ResultBody.error(((ResultException) cause).getCode(), cause.getMessage()).toJsonString());
            return null;
        } else {
            cause.printStackTrace();
            ctx.setResponseBody(ResultBody.error(ResultEnum.UNKNOW_ERROR).toJsonString());
            return null;
        }
    }
}
