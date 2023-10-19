package com.workshop.postal.SecurityV2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.secret}")
    private String apiSecret;

    private final String[] endpointsWithoutAuthentication = {"/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/api/clientes",
            "/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/swagger-ui/**"
    } ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        boolean isEndpointWithoutAuthentication = false;

        for (String endpoint : endpointsWithoutAuthentication) {
            System.out.println("endpoint: " + endpoint);
            System.out.println("path: " + path);
            if (path.contains(endpoint)) {
                isEndpointWithoutAuthentication = true;
                break;
            }
        }

        if (isEndpointWithoutAuthentication) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestApiKey = request.getHeader("X-API-KEY");
        String requestApiSecret = request.getHeader("X-API-SECRET");

        if (apiKey.equals(requestApiKey) && apiSecret.equals(requestApiSecret)) {

            UsernamePasswordAuthenticationToken authenticationToken =//Token de autenticacion
                    new UsernamePasswordAuthenticationToken(null, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);

        } else {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized");

        }
    }
}
