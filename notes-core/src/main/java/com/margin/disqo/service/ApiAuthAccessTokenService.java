package com.margin.disqo.service;

import com.margin.disqo.entity.ApiAuthAccessToken;
import com.margin.disqo.service.model.ApiAuthAccessTokenCreationRequest;
import com.margin.disqo.service.model.ApiAuthAccessTokenRequest;

import java.util.Optional;

public interface ApiAuthAccessTokenService {

    Optional<ApiAuthAccessToken> findByToken(String token);

    Optional<ApiAuthAccessToken> findByUserDetailId(Long userDetailId);

    ApiAuthAccessToken createApiAccessToken(ApiAuthAccessTokenCreationRequest request);

    ApiAuthAccessToken refreshApiAccessToken(ApiAuthAccessTokenRequest request);

    void inactivateApiAccessToken(ApiAuthAccessTokenRequest request);

    void deleteApiAccessToken(ApiAuthAccessTokenRequest request);
}
