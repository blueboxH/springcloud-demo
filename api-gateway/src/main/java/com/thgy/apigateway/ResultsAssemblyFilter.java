package com.thgy.apigateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thgy.common.result.ResultEnum;
import com.thgy.common.result.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * 统一组装返回结果
 * @author box
 * @since 2018/11/6/8:39
 */
@Component
public class ResultsAssemblyFilter extends ZuulFilter {
    private RequestContext ctx;
    private String requestUrl;
    private static Logger log = LoggerFactory.getLogger(ResultsAssemblyFilter.class);

    // 过滤器的类型, 决定过滤器在请求的哪个生命周期中运行
    @Override
    public String filterType() {
        return POST_TYPE;
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
        ctx = RequestContext.getCurrentContext();
        requestUrl = ctx.getRequest().getRequestURI();

        // swagger 不做处理
        if (requestUrl.endsWith("/v2/api-docs")) return false;
        return true;
    }

    // 过滤器的具体实现逻辑
    @Override
    public Object run() {
        String body = null;
        InputStream stream = ctx.getResponseDataStream();
        try {
            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            JSONObject jsonBody = (JSONObject) JSON.parse(body);
            if (jsonBody == null){
                ctx.setResponseBody(ResultBody.success().toJsonString());
                return null;
            }

            // 从下面微服务返回回来的异常结果, 直接抛出, 让error统一处理
            if (jsonBody.get("status") != null){
                log.error("请求: {} 错误, 返回结果为: {}", requestUrl, body);
//                ctx.setResponseBody(ResultBody.error(jsonBody.get("status").toString(), jsonBody.get("message").toString()).toJsonString());
//                return null;
                throw new ResultException(jsonBody.get("status").toString(), jsonBody.get("message").toString());
            }
            if (jsonBody.get("success") != null){
                log.debug("不处理封装过 {} 请求", requestUrl);
                return null;
            }

            // 添加请求结果进入data
            ctx.setResponseBody(ResultBody.success(jsonBody).toJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            ctx.setResponseBody(ResultBody.success(body).toJsonString());
        }

        return null;
    }

}
