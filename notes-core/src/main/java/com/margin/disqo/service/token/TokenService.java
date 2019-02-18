package com.margin.disqo.service.token;

import com.margin.disqo.service.token.model.ApiAuthAccessTokenCreationRequest;

public interface TokenService {

    String create(ApiAuthAccessTokenCreationRequest request);

    String refresh(final String token);

    String getUserDetailId(final String token);
}
