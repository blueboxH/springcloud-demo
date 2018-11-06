package com.thgy.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        // zuul 中默认定义了以下4中不同生命周期的锅炉其类型:
        // - pre: 可以在请求被路由前调用
        // - routing: 在路由请求时调用
        // - error: 在处理请求发生错误时调用
        // - post: 在routing 和 error 过滤器之后被调用

        return "error";
    }

    // 过滤器的执行顺序, 当请求在一个阶段中存在多个过滤器时, 决定执行的顺序
    @Override
    public int filterOrder() {
        // 数字越小优先级越高
        return 10;
    }

    // 判断该过滤器是否需要被执行
    @Override
    public boolean shouldFilter() {

        return true;
    }

    // 过滤器的具体实现逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }
}
