package com.margin.disqo.service.token.model;

import com.margin.disqo.entity.ApiAuthAccessToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuthAccessTokenRequest {
    private ApiAuthAccessToken token;
}
