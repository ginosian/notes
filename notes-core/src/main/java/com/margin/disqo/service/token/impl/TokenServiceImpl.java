package com.margin.disqo.service.token.impl;

import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.enums.TokenType;
import com.margin.disqo.service.token.TokenService;
import com.margin.disqo.service.token.component.JwtTokenComponent;
import com.margin.disqo.service.token.model.ApiAuthAccessTokenCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${security.jwt.calim.userdetailid:userdetailid}")
    private String CALIM_USER_DETAIL_ID;

    @Value("${security.jwt.calim.created:created}")
    private String CALIM_CREATED;

    @Value("${security.jwt.calim.type:type}")
    private String CALIM_TYPE;

    @Value("${security.jwt.calim.active:active}")
    private String CALIM_ACTIVE;

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @Override
    public String create(final ApiAuthAccessTokenCreationRequest request) {
        notNull(request, "request can not be null.");
        final ApiUserDetail userDetail = request.getUserDetail();
        final TokenType tokenType = request.getTokenType();
        final Long userDetailId = userDetail.getId();
        final Date expires = request.getExpires();
        notNull(userDetail, "request.userDetail can not be null.");
        notNull(userDetailId, "request.userDetail.id can not be null or empty.");
        notNull(tokenType, "request.tokenType can not be null.");
        notNull(expires, "request.expires can not be null.");

        final Date creationDate =  new Date();
        final boolean isActive = request.isActive();

        final Map<String, Object> claims = new HashMap<>();
        claims.put(CALIM_USER_DETAIL_ID, userDetailId);
        claims.put(CALIM_CREATED, creationDate);
        claims.put(CALIM_TYPE, tokenType);
        claims.put(CALIM_ACTIVE, isActive);

        return jwtTokenComponent.createToken(claims);
    }

    @Override
    public String refresh(final String token) {
        hasText(token, "request.token can not be null or empty.");

        final Map<String, Object> claims = jwtTokenComponent.getClaims(token);
        claims.remove(CALIM_CREATED);
        final Date creationDate =  new Date();
        claims.put(CALIM_CREATED, creationDate);
        return jwtTokenComponent.createToken(claims);
    }

    @Override
    public String getUserDetailId(final String token) {
        hasText(token, "token can not be null or empty.");
        return jwtTokenComponent.getClaim(token, CALIM_USER_DETAIL_ID);
    }
}
