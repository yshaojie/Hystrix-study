package com.self.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 * In a standard Java web application,
 * you can use a Servlet Filter to initialize
 * this lifecycle by implementing a filter similar to this:
 * Created by shaojieyue on 8/17/15.
 */
public class HystrixRequestContextServletFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            filterChain.doFilter(request, response);
        } finally {
            context.shutdown();
        }
    }

    public void destroy() {

    }
}
