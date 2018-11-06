package com.thgy.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 检测token的过滤器,
 * 在启动入口加入相应的Bean或者 在这加上 @Component 注解
 */
@Component
public class AccessFilter extends ZuulFilter {

    private RequestContext ctx;
    private HttpServletRequest request;

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    private static String NO_AUTH_API[] = {
            "swagger",  // swagger
            "/error",  // swagger
            "/csrf"  // swagger
    };

    // 过滤器的类型, 决定过滤器在请求的哪个生命周期中运行
    @Override
    public String filterType() {
        // zuul 中默认定义了以下4中不同生命周期的锅炉其类型:
        // - pre: 可以在请求被路由前调用
        // - routing: 在路由请求时调用
        // - error: 在处理请求发生错误时调用
        // - post: 在routing 和 error 过滤器之后被调用

        return "pre";
    }

    // 过滤器的执行顺序, 当请求在一个阶段中存在多个过滤器时, 决定执行的顺序
    @Override
    public int filterOrder() {
        // 数字越小优先级越高
        return 0;
    }

    // 判断该过滤器是否需要被执行
    @Override
    public boolean shouldFilter() {
        ctx = RequestContext.getCurrentContext();
        request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();
        log.info("send {} request to {}", request.getMethod(), requestUrl);

        // swagger里面的不做验证, OPTIONS操作不做验证
        if (requestUrl.equals("/") || request.getMethod().equals("OPTIONS")) return true;

//        // 不用检查权限的api
//        for (String api : NO_AUTH_API) {
//            if (requestUrl.contains(api)){
//                log.debug("请求 [{}] 跳过权限验证", requestUrl);
//                return false;
//            }
//        }
        if (requestUrl.endsWith("/v2/api-docs")){
            return false;
        }

        return false;
    }

    // 过滤器的具体实现逻辑
    @Override
    public Object run() throws ZuulException {
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null){
            log.warn("access token is empty!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
