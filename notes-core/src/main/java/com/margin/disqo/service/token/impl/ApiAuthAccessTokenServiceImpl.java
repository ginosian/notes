package com.margin.disqo.service.token.impl;

import com.margin.disqo.entity.ApiAuthAccessToken;
import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.enums.TokenType;
import com.margin.disqo.repository.ApiAuthAccessTokenRepository;
import com.margin.disqo.service.token.ApiAuthAccessTokenService;
import com.margin.disqo.service.token.TokenService;
import com.margin.disqo.service.token.component.ApiAuthAccessTokenConverter;
import com.margin.disqo.service.token.model.ApiAuthAccessTokenCreationRequest;
import com.margin.disqo.service.token.model.ApiAuthAccessTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Service
public class ApiAuthAccessTokenServiceImpl implements ApiAuthAccessTokenService {

    @Value("${security.jwt.expiration.seconds:3600}")
    private int AUTH_ACCESS_TOKEN_EXPIRATION_SECONDS;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApiAuthAccessTokenRepository tokenRepository;

    @Override
    public Optional<ApiAuthAccessToken> findByToken(final String token) {
        hasText(token, "token can not be null");
        return Optional.ofNullable(tokenRepository.findByToken(token));
    }

    @Override
    public Optional<ApiAuthAccessToken> findByUserDetailId(final Long userDetailId) {
        notNull(userDetailId, "userDetailId can not be null");
        return Optional.ofNullable(tokenRepository.findByUser(userDetailId));
    }

    @Override
    public ApiAuthAccessToken createApiAccessToken(final ApiAuthAccessTokenCreationRequest request) {
        notNull(request, "request can not be null");
        final ApiUserDetail userDetail = request.getUserDetail();
        final TokenType tokenType = request.getTokenType();
        notNull(userDetail, "request.userDetail can not be null");
        notNull(tokenType, "request.tokenType can not be null");

        final Long userId = userDetail.getUser().getId();
        final Date expires = createExpirationDate(new Date().getTime());
        request.setExpires(expires);
        final String token = tokenService.create(request);

        final ApiAuthAccessToken apiAuthAccessToken = tokenRepository.save(ApiAuthAccessTokenConverter.convert(request, token));
        return apiAuthAccessToken;
    }

    @Override
    public ApiAuthAccessToken refreshApiAccessToken(final ApiAuthAccessTokenRequest request) {
        notNull(request, "request can not be null");
        final ApiAuthAccessToken token  = request.getToken();
        notNull(token, "request.token can not be null");

        token.setExpires(createExpirationDate(new Date().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        token.setActive(true);
        return tokenRepository.save(token);
    }

    @Override
    public void inactivateApiAccessToken(final ApiAuthAccessTokenRequest request) {
        notNull(request, "request can not be null");
        final ApiAuthAccessToken apiAuthAccessToken  = request.getToken();
        notNull(apiAuthAccessToken, "request.apiAuthAccessToken can not be null");

        apiAuthAccessToken.setActive(false);
        tokenRepository.save(apiAuthAccessToken);
    }

    @Override
    public void deleteApiAccessToken(ApiAuthAccessTokenRequest request) {
        notNull(request, "request can not be null");
        final ApiAuthAccessToken apiAuthAccessToken  = request.getToken();
        notNull(apiAuthAccessToken, "request.apiAuthAccessToken can not be null");

        apiAuthAccessToken.setActive(false);
        tokenRepository.save(apiAuthAccessToken);
    }

    private Date createExpirationDate(final long time){
        return new Date(1000L * AUTH_ACCESS_TOKEN_EXPIRATION_SECONDS + time);
    }
}
