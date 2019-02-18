package com.margin.disqo.security.jwt;

import com.google.common.collect.Lists;
import com.margin.disqo.auth.AuthenticationFacade;
import com.margin.disqo.entity.ApiUserDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private AuthenticationFacade authenticationFacade;

    public JWTAuthorizationFilter(AuthenticationFacade authenticationManager) {
        super(authenticationManager);
        this.authenticationFacade = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        final String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(new ApiUserDetail(), "token", Lists.newArrayList()));
                chain.doFilter(req, res);
            } catch (Exception e) {
                SecurityContextHolder.getContext().setAuthentication(null);
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private void getAuthentication(final String header) throws AuthException {
        final String token = header.replaceAll("Bearer ", "");
        authenticationFacade.authenticateByApiAccessToken(token);
    }
}
