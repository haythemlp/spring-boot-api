package com.haythemlp.springboot.security;

import com.haythemlp.springboot.auth.AuthService;
import com.haythemlp.springboot.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

@Autowired
   private AuthService authService ;

    @Value("${auth.header}")
    private String TOKEN_HEADER;
@Autowired
private TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String header = httpServletRequest.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (header != null && securityContext.getAuthentication() == null) {

            String token=header.substring("Bearer ".length());
            String username =tokenUtil.getUsernameFromToken(token);
            System.out.println(username);
            if (username != null)
            {
                UserDetails user =authService.loadUserByUsername(username);
                if (tokenUtil.isTokenValid(token,user))
                {
                    UsernamePasswordAuthenticationToken authenticationToken
                            =new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }


        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
