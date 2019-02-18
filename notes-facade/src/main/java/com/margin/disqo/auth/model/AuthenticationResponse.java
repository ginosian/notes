package com.margin.disqo.auth.model;

import com.margin.disqo.entity.ApiUserDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private ApiUserDetail apiUserDetail;
    private String token;
}
