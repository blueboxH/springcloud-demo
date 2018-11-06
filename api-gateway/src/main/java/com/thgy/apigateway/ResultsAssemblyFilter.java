package com.thgy.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author box
 * @since 2018/11/6/8:39
 */
@Component
public class ResultsAssemblyFilter extends ZuulFilter {

    /**
     * 检测token的过滤器,
     * 在启动入口加入相应的Bean或者 在这加上 @Component 注解
     */

    private static Logger log = LoggerFactory.getLogger(ResultsAssemblyFilter.class);

    // 过滤器的类型, 决定过滤器在请求的哪个生命周期中运行
    @Override
    public String filterType() {
        return "post";
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
    public Object run() {
        // 在这里实现对返回结果的组装
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse servletResponse = context.getResponse();
        servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());
        return null;
    }

}
