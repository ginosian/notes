package com.margin.disqo.auth.impl;

import com.margin.disqo.auth.AuthenticationFacade;
import com.margin.disqo.auth.component.AuthModelConverter;
import com.margin.disqo.auth.component.AuthValidationStrategy;
import com.margin.disqo.auth.model.APIAuthenticationResponse;
import com.margin.disqo.auth.model.AuthenticationRequest;
import com.margin.disqo.auth.model.AuthenticationResponse;
import com.margin.disqo.entity.ApiAuthAccessToken;
import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.service.ApiAuthAccessTokenService;
import com.margin.disqo.service.ApiUserDetailService;
import com.margin.disqo.service.model.ApiAuthAccessTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

import static org.springframework.util.Assert.hasText;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Autowired
    private ApiUserDetailService apiUserDetailService;

    @Autowired
    private ApiAuthAccessTokenService apiAuthAccessTokenService;

    @Autowired
    private AuthValidationStrategy authValidationStrategy;

    @Value("#{'${authenticationService.masterApiUserDetail.passwordHash}' ?: null}")
    private String masterApiUserDetailPasswordHash;

    @Override
    public AuthenticationResponse authenticateByCredentials(final AuthenticationRequest request) throws AuthException {
        authValidationStrategy.validate(request);
        final ApiUserDetail userDetail = apiUserDetailService.loadUserByUsername(request.getUsername());
        authValidationStrategy.validate(userDetail);
        final ApiAuthAccessToken apiAuthAccessToken = apiAuthAccessTokenService.createApiAccessToken(AuthModelConverter.convert(userDetail, request.isRememberMe()));
        return new AuthenticationResponse(userDetail, apiAuthAccessToken.getToken());
    }

    @Override
    public AuthenticationResponse authenticateByApiAccessToken(final String token) throws AuthException {
        ApiAuthAccessToken existingToken = apiAuthAccessTokenService.findByToken(token).orElse(null);
        authValidationStrategy.validateForRefreshing(existingToken);
        if (authValidationStrategy.isExpiredLoginToken(existingToken)) {
            apiAuthAccessTokenService.inactivateApiAccessToken(new ApiAuthAccessTokenRequest(existingToken));
            throw new AuthException(String.format("Api user access token is expired:'%s'.", existingToken.getApiUserDetail().getUser().getId()));
        }
        if (authValidationStrategy.isExpiringRefreshToken(existingToken)) {
            existingToken = apiAuthAccessTokenService.refreshApiAccessToken(new ApiAuthAccessTokenRequest(existingToken));
        }
        return new AuthenticationResponse(existingToken.getApiUserDetail(), existingToken.getToken());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthException {
        return new APIAuthenticationResponse(authenticateByCredentials((AuthenticationRequest) authentication.getDetails()));
    }

    @Override
    public void logout(String token) {
        hasText(token, "token can not be null or empty.");
        ApiAuthAccessToken existingToken = apiAuthAccessTokenService.findByToken(token).orElse(null);
        if (existingToken != null) {
            apiAuthAccessTokenService.deleteApiAccessToken(new ApiAuthAccessTokenRequest(existingToken));
        }
    }
}
