package com.rho.srpingsecurity.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

public class RequestValidationFilter extends OncePerRequestFilter {
    private final Logger logger = Logger.getLogger(RequestValidationFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var requestId = request.getHeader("Request-Id");

        if(requestId == null || requestId.isBlank()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.info("Request Id is null");
            return;
        }
        logger.info("Request Id: " + requestId);
        filterChain.doFilter(request, response);
    }


}
