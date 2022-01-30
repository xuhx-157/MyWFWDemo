package cn.wfw.xuhx.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义过滤器，需要实现接口GlobalFilter
 */
@Order(-1)  //过滤器执行优先级，值越小优先级越高
@Component
public class LoginFileter implements GlobalFilter {

    /**
     *
     * @param exchange  可以获取到所有请求信息
     * @param chain     放行用的
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        String user = queryParams.getFirst("user");
        if("admin".equals(user)){
            //放行
            return chain.filter(exchange);
        }
        //拦截
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
