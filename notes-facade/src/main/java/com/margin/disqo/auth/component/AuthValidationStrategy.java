package com.margin.disqo.auth.component;

import com.margin.disqo.auth.model.AuthenticationRequest;
import com.margin.disqo.entity.ApiAuthAccessToken;
import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.enums.TokenType;
import com.margin.disqo.exception.ApiException;
import com.margin.disqo.service.ApiAuthAccessTokenService;
import com.margin.disqo.service.model.ApiAuthAccessTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.util.Assert.hasText;

@Component
public class AuthValidationStrategy {
    @Autowired
    private ApiAuthAccessTokenService apiAuthAccessTokenService;

    public void validate(final ApiUserDetail userDetail){
        notNull(userDetail, "userDetail can not be null");
        final Long userId = userDetail.getUser().getId();
        final String username = userDetail.getUsername();
        final String plainPassword = userDetail.getPassword();
        notNull(userId, "userDetail.userId can not be null or empty.");
        hasText(username, "userDetail.username can not be null or empty.");
        hasText(plainPassword, "userDetail.plainPassword can not be null or empty.");

        if(!userDetail.getApproved()){
            throw new ApiException(String.format("Authentication failed for for user:'%s' as email:'%s' is not verified.", userId, username), 401);
        }
        if(!PasswordHashHelper.isPasswordCorrect(plainPassword)){
            throw new ApiException(String.format("Password validation failed for user:'%s'.", userId), 401);
        }
    }

    public void validate(final AuthenticationRequest request){
        Assert.notNull(request, "authenticationRequest.request cannot be null.");
        Assert.notNull(request.getUsername(), "authenticationRequest.request.username cannot be null.");
        Assert.notNull(request.getPassword(), "authenticationRequest.request.plainPassword cannot be null.");
    }

    public void validateForRefreshing(final ApiAuthAccessToken token){
        if(token == null || (!isRememberMe(token) && (isExpired(token.getExpires()) || !token.getActive()))){
            apiAuthAccessTokenService.inactivateApiAccessToken(new ApiAuthAccessTokenRequest(token));
            throw new ApiException("ApiAccessToken does not exist or is invalid or is expired.", 401);
        }
    }

    public boolean isExpiringRefreshToken(final ApiAuthAccessToken token){
        return isRememberMe(token) && (isExpired(token.getExpires()) || isExpiring(token.getExpires()));
    }

    public boolean isExpiredLoginToken(final ApiAuthAccessToken token){
        return isLogin(token) && (isExpired(token.getExpires()) || !token.getActive());
    }

    private boolean isExpiring(final LocalDateTime expirationTime) {
        return expirationTime.plusMinutes(1).isAfter(LocalDateTime.now());
    }

    private boolean isExpired(final LocalDateTime expirationTime) {
        return expirationTime.isBefore(LocalDateTime.now());
    }

    private boolean isRememberMe(final ApiAuthAccessToken token) {
        return token.getTokenType().equals(TokenType.LOGIN_REMEMBER_ME);
    }

    private boolean isLogin(final ApiAuthAccessToken token) {
        return token.getTokenType().equals(TokenType.LOGIN);
    }


}
