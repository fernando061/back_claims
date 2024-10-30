package com.pe.claims.aplication.config.filters;

import com.pe.claims.aplication.Helpers.ClientAuthenticationHelper;
import com.pe.claims.aplication.Helpers.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class ApiKeyFilter extends OncePerRequestFilter {

    private final ClientAuthenticationHelper authServiceHelper;
    private final ApiKeyFilterConfig config;

    public ApiKeyFilter(ClientAuthenticationHelper authServiceHelper, ApiKeyFilterConfig config) {
        this.authServiceHelper = authServiceHelper;
        this.config = config;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var path = request.getRequestURI();

       if (path.startsWith("/swagger-ui/") ||
                path.startsWith("/v3/api-docs/") ||
                path.equals("/swagger-ui.html") ||
                path.equals("/swagger-ui/index.html") ||
                path.equals("/webjars/springfox-swagger-ui/") ||
                path.startsWith("/swagger-resources/")) {
            return true;
        }


       return !(path.startsWith(config.getPathPrefix()) || path.startsWith(config.getPathPrefixTwo()));

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {
        JwtUtil jwtUtil = new JwtUtil();
        String reqApiKey = request.getHeader("Api-Key");
        String authHeader = request.getHeader("Authorization");
        boolean isApiKeyValid = authServiceHelper.validateApiKey(reqApiKey);

        if (!"OPTIONS".equalsIgnoreCase(request.getMethod()) & !isApiKeyValid) {
            //return 401 Unauthorized
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key");
            return;
        }
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String customerName = jwtUtil.extractUsername(token);
                boolean isTokenValid = jwtUtil.validateToken(token,customerName);

                if (isTokenValid) {
                    var authenticationToken = new UsernamePasswordAuthenticationToken(customerName,
                            null, null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Bearer Token");
                    return;
                }

            } catch (Exception e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Bearer Token");
                return;
            }
        }
        else{
            var authenticationToken = new UsernamePasswordAuthenticationToken(reqApiKey,
                    reqApiKey, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);

    }
}