//package com.code9.usermicroservice.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@AllArgsConstructor
//public class RequestFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwt = jwtService.getToken(request);
//
//        if(jwt != null){
//            String username = jwtService.extractUsername(jwt);
//            if(username != null){
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if(jwtService.validateToken(jwt, userDetails)){
//                    TokenBasedAuthentication authToken = new TokenBasedAuthentication(userDetails);
//                    authToken.setToken(jwt);
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
