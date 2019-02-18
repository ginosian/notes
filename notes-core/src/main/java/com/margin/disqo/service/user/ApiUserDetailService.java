package com.margin.disqo.service.user;

import com.margin.disqo.entity.ApiUser;
import com.margin.disqo.entity.ApiUserDetail;

public interface ApiUserDetailService {

    ApiUserDetail loadUserByUsername(String username);

    boolean isEmailUsed(String email);

    boolean isCorrectPassword(String userId, String password);

    boolean isUserActive(ApiUser user);
}
