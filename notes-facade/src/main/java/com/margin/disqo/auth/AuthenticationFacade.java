package com.margin.disqo.auth;

import com.margin.disqo.auth.model.AuthenticationRequest;
import com.margin.disqo.auth.model.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.security.auth.message.AuthException;

public interface AuthenticationFacade  extends AuthenticationManager {

    AuthenticationResponse authenticateByCredentials(AuthenticationRequest request) throws AuthException;

    AuthenticationResponse authenticateByApiAccessToken(String token) throws AuthException;

    Authentication authenticate(Authentication authentication);

    void logout(String token);
}
