package com.pe.claims.aplication.config.filters;

import com.pe.claims.aplication.Helpers.ClientAuthenticationHelper;
import com.pe.claims.aplication.Helpers.JwtUtil;
import com.pe.claims.aplication.Mapper.ClaimMapper;
import com.pe.claims.core.Entities.UserRole;
import com.pe.claims.infraestructure.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

//    private final ClientAuthenticationHelper authServiceHelper;
//    private final ApiKeyFilterConfig config;

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ClaimMapper claimMapper;
//    public ApiKeyFilter(ClientAuthenticationHelper authServiceHelper, ApiKeyFilterConfig config) {
//        this.authServiceHelper = authServiceHelper;
//        this.config = config;
//
//    }
    private final ClientAuthenticationHelper authServiceHelper;
    private final ApiKeyFilterConfig config;
    private final UserService userService; // Esto se inyectará a través del constructor
    private final ClaimMapper claimMapper; // Esto también se inyectará

    @Autowired
    public ApiKeyFilter(ClientAuthenticationHelper authServiceHelper,
                        ApiKeyFilterConfig config,
                        UserService userService,
                        ClaimMapper claimMapper) {
        this.authServiceHelper = authServiceHelper;
        this.config = config;
        this.userService = userService;
        this.claimMapper = claimMapper;
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
                String document = jwtUtil.extractDocumentNumber(token);
                boolean isTokenValid = jwtUtil.validateToken(token,customerName);

                if (isTokenValid) {

                    var user = userService.findByDocument(document);
                    List<GrantedAuthority> authorities;
                    if(user!=null){
                        authorities = user.getUserRoles().stream()
                                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                                .collect(Collectors.toList());
                    }
                    else{
                        authorities = List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
                    }


                    var authenticationToken = new UsernamePasswordAuthenticationToken(customerName,
                            null, authorities);
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