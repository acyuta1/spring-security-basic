package com.acyuta.springsecurity2.customUserDetails;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter: Performs activities/business logic when a request is made,
 *          then passes onto the next filter.
 *          One such famous filter: csrf.
 */
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    /*
    Use this when implementing Filter. But since OncePerRequestFilter implements Filter and its method doFilter, we must now provide
    implementation for doFilterInternal.
     */
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) {
            log.info("successfully logged in {}", authentication.getName());
        }
    }
}
