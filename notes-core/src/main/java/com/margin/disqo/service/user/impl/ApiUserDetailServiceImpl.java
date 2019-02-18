package com.margin.disqo.service.user.impl;

import com.margin.disqo.entity.ApiUser;
import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.repository.ApiUserDetailRepository;
import com.margin.disqo.service.user.ApiUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service(value = "api_user_detail_service")
public class ApiUserDetailServiceImpl implements ApiUserDetailService, UserDetailsService {

    @Autowired
    private ApiUserDetailRepository apiUserDetailRepository;

    @Override
    public ApiUserDetail loadUserByUsername(String username) {
        return apiUserDetailRepository.findByUsername(username);
    }

    @Override
    public boolean isEmailUsed(String email) {
        return false;
    }

    @Override
    public boolean isCorrectPassword(String userId, String password) {
        return false;
    }

    @Override
    public boolean isUserActive(ApiUser user) {
        return false;
    }
}
