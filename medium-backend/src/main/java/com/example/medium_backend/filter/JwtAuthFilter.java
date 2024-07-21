package com.example.medium_backend.filter;

import com.example.medium_backend.config.UserInfoDetailsService;
import com.example.medium_backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // Extract token and username
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
            } catch (Exception e) {
                logger.error("Failed to extract username from token", e);
            }
        }

        // Validate the token and set authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                logger.info(userDetails.toString());
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    logger.info(authToken.toString());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Authentication successful for user: "+ username);
                } else {
                    logger.warn("Token validation failed for user: "+ username);
                }
            } catch (UsernameNotFoundException e) {
                logger.warn("User not found: "+ username, e);
            } catch (Exception e) {
                logger.error("Error occurred during authentication", e);
            }
        } else {
            if (username == null) {
                logger.warn("Username is null");
            } else {
                logger.info("Username is already authenticated: "+ username);
            }
        }

        filterChain.doFilter(request, response);
    }


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            try {
//                username = jwtService.extractUsername(token);
//            } catch (Exception e) {
//                logger.error("Failed to extract username from token", e);
//            }
//
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if (jwtService.validateToken(token, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                    logger.info("Authentication successful for user: {}");
//                } else {
//                    logger.warn("Token validation failed for user: {}"+ username);
//                }
//            } else {
//                if(username==null)
//                    logger.warn("Username is null{}");
//                else
//                    logger.info("Username is already authenticated: {}");
//            }
//        filterChain.doFilter(request, response);
//    }

}
