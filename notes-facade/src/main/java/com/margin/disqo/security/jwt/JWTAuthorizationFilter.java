package com.margin.disqo.security.jwt;

import com.margin.disqo.auth.AuthenticationFacade;
import com.margin.disqo.auth.model.AuthenticationResponse;
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
                final AuthenticationResponse authentication = getAuthentication(header);
                final ApiUserDetail userDetail = authentication.getApiUserDetail();
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userDetail, authentication.getToken(), userDetail.getAuthorities()));
                chain.doFilter(req, res);
            } catch (AuthException e) {
                SecurityContextHolder.getContext().setAuthentication(null);
                res.setStatus(401);
            } catch (IllegalArgumentException e){
                SecurityContextHolder.getContext().setAuthentication(null);
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private AuthenticationResponse getAuthentication(final String header) throws AuthException {
        final String token = header.replaceAll("Bearer ", "");
        return authenticationFacade.authenticateByApiAccessToken(token);
    }
}
