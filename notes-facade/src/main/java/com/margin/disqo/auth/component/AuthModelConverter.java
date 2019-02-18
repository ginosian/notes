package com.margin.disqo.auth.component;

import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.enums.TokenType;
import com.margin.disqo.service.token.model.ApiAuthAccessTokenCreationRequest;

import java.util.Date;

public class AuthModelConverter {
    public static ApiAuthAccessTokenCreationRequest convert(final ApiUserDetail userDetail, final boolean isRememberMe) {
        final ApiAuthAccessTokenCreationRequest apiAuthAccessTokenCreationRequest = new ApiAuthAccessTokenCreationRequest();
        apiAuthAccessTokenCreationRequest.setUserDetail(userDetail);
        apiAuthAccessTokenCreationRequest.setTokenType(isRememberMe ? TokenType.LOGIN_REMEMBER_ME : TokenType.LOGIN);
        apiAuthAccessTokenCreationRequest.setActive(true);
        apiAuthAccessTokenCreationRequest.setExpires(new Date());
        return apiAuthAccessTokenCreationRequest;
    }
}