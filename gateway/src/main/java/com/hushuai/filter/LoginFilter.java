package com.hushuai.filter;

import com.hushuai.config.FilterProperties;
import com.hushuai.config.JwtProperties;
import com.hushuai.utils.CookieUtils;
import com.hushuai.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * created by it_hushuai
 * 2020/4/14 21:58
 */
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties prop;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;//处理顺序，越小越靠前
    }

    @Override
    public boolean shouldFilter() {
        // 请求路径白名单放行
        //1.获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //2.获取request
        HttpServletRequest request = context.getRequest();
        // 获取路径
        String path = request.getRequestURI();
        // 判断白名单
        return !isAllowPath(path);
    }

    private boolean isAllowPath(String path) {
        for (String allowPath : filterProperties.getAllowPaths()) {
            if (path.startsWith(allowPath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取Request
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 获取token
        String token = CookieUtils.getCookieValue(request, prop.getCookieName());
        // 校验token
        //获取参数access_token
        try {
            // 校验通通过，放行
            JwtUtils.getInfoFromToken(token, prop.getPublicKey());
        } catch (Exception e) {
            // 校验不通过，返回403
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
