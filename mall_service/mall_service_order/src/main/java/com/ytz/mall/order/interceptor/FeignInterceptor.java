package com.ytz.mall.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName: FeignInterceptor
 * @Description: TODO feign调用拦截
 * @author: yangtz
 * @date: 2020/11/2
 * @Version: V1.0
 */
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) attributes;

        if (requestAttributes != null) {
            //1.获取请求对象
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                //2.获取请求对象中的所有的头信息(网关传递过来的)
                while (headerNames.hasMoreElements()) {
                    //头的名称
                    String name = headerNames.nextElement();
                    //头名称对应的值
                    String value = request.getHeader(name);
                    //3.将头信息传递给fegin (restTemplate)
                    requestTemplate.header(name,value);
                }
            }
        }
    }
}
