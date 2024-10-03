package com.pe.claims.aplication.config.filters;

import com.pe.claims.aplication.Helpers.ClientAuthenticationHelper;
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
        // Verificar si la ruta es una de las rutas permitidas para Swagger
       if (path.startsWith("/swagger-ui/") ||
                path.startsWith("/v3/api-docs/") ||
                path.equals("/swagger-ui.html") ||
                path.equals("/swagger-ui/index.html") ||
                path.equals("/webjars/springfox-swagger-ui/") ||
                path.startsWith("/swagger-resources/")) {
            return true;
        }

        // Verificar si la ruta debería ser filtrada basándose en el prefijo de configuración
        return !path.startsWith(config.getPathPrefix());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {

//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            // Si es una solicitud preflight, permite que pase sin validar la API key
//            filterChain.doFilter(request, response);
//            //response.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }

        String reqApiKey = request.getHeader("Api-Key");
        boolean isApiKeyValid = authServiceHelper.validateApiKey(reqApiKey);

        if (!"OPTIONS".equalsIgnoreCase(request.getMethod()) & !isApiKeyValid) {
            //return 401 Unauthorized
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key");
            return;
        }

        //apiKey is valid. Signal to Spring Security, this is an authenticated request
        var authenticationToken = new UsernamePasswordAuthenticationToken(reqApiKey,
                reqApiKey, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //continue process the request
        filterChain.doFilter(request, response);

    }
}