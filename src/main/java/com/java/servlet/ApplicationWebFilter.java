package com.java.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/todo/*") // This filter will only apply to URLs under /secured
public class ApplicationWebFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ApplicationWebFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        logger.info("Incoming request " + httpServletRequest.getMethod() + ":" + httpServletRequest.getRequestURI());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        logger.info("Incoming response: " + httpServletResponse.getStatus());

    }

    @Override
    public void destroy() {
        logger.info("Web filter destroyed");
    }
}
