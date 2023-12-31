package com.career_link.kenya.security;

import com.career_link.kenya.services.UserDetailsServiceImpl;
import com.career_link.kenya.utils.ApplicationConstants;
import com.career_link.kenya.utils.LoggingTypes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public AuthFilter(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    /**
     * Note that a request that does not pass a filter cannot be processed . the @return statement after a filter is used
     * to terminate the function ensuring that there us no further processing after the next filter is invoked.
     */

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        ApplicationConstants.LOG(request.getServletPath(), LoggingTypes.INFO);

        if (request.getServletPath().contains("/api/v1/auth") || request.getServletPath().contains("/api/v1/test") || request.getServletPath().contains("/get-students")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        jwt = authHeader.substring(7);
        ApplicationConstants.LOG(jwt, LoggingTypes.INFO);
        userEmail = jwtTokenProvider.getUsernameFromToken(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userEmail);

            if (jwtTokenProvider.validateJwtToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
