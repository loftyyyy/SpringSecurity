package com.rho.srpingsecurity.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

//@Component
public class StaticAuthenticationFilter extends OncePerRequestFilter {

    @Value("${static.authorization.key}")
    private String staticKey;

    private final Logger logger = Logger.getLogger(StaticAuthenticationFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String staticAuthenticationKey = request.getHeader("X-Auth-Key");
        if(!staticAuthenticationKey.equals(staticKey)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("Authorization key not existing " + staticAuthenticationKey);
            return;
        }

        logger.info("Auth key " + request.getHeader("X-Auth-Key"));
        filterChain.doFilter(request, response);
    }
}
