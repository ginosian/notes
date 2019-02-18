package com.margin.disqo.service.model;

import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuthAccessTokenCreationRequest {
    private ApiUserDetail userDetail;
    private TokenType tokenType;
    private boolean isActive;
    private Date expires;
}
