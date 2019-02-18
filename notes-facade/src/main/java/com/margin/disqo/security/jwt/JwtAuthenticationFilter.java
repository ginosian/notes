package com.margin.disqo.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.margin.disqo.BeanMapper;
import com.margin.disqo.dto.AuthRequestDTO;
import com.margin.disqo.auth.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    private AuthenticationFacade authenticationFacade;
    private BeanMapper beanMapper;

    @Autowired
    public JwtAuthenticationFilter(final AuthenticationFacade authenticationFacade, final BeanMapper beanMapper) {
        this.authenticationFacade = authenticationFacade;
        this.beanMapper = beanMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            final AuthRequestDTO credentials = new ObjectMapper().readValue(req.getInputStream(), AuthRequestDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = (String)auth.getCredentials();
        res.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
