package com.ecommerce.codecase.log.filter;

import com.ecommerce.codecase.log.enums.EnumLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
@WebFilter("/*")
public class LogFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    private static final long THRESHOLD = EnumLogs.MS_THRESHOLD.getValue();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // empty
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Instant start = Instant.now();
        try {
            chain.doFilter(req, resp);
        } finally {
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            HttpServletRequest httpServletRequest = (HttpServletRequest) req;

            String pathVariable = httpServletRequest.getPathInfo() != null ? "/" + httpServletRequest.getPathInfo() : "";

            if (time >= THRESHOLD) {
                LOGGER.info("{} {}{} : {} ms ", httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), pathVariable , time);
            }

        }
    }

    @Override
    public void destroy() {
        // empty
    }

}
