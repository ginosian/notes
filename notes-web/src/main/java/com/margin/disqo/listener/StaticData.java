package com.margin.disqo.listener;

import com.google.common.collect.Lists;
import com.margin.disqo.entity.ApiUser;
import com.margin.disqo.entity.ApiUserDetail;
import com.margin.disqo.entity.Role;
import com.margin.disqo.enums.RoleType;

import java.util.List;
import java.util.Set;

public class StaticData {

    public static List<Role> createRoles() {
        final Role roleOwner = new Role();
        roleOwner.setType(RoleType.ROLE_OWNER);
        return Lists.newArrayList(roleOwner);
    }

    public static ApiUserDetail userDetails(final Set<Role> roles, final ApiUser user, final String email){
        final ApiUserDetail apiUserDetail = new ApiUserDetail();
        apiUserDetail.setUsername(email);
        apiUserDetail.setPassword("password");
        apiUserDetail.setRoles(roles);
        apiUserDetail.setUser(user);
        apiUserDetail.setApproved(true);
        return apiUserDetail;
    }

    public static ApiUser apiUser(){
        final ApiUser apiUser = new ApiUser();
        apiUser.setName("Some name");
        return apiUser;
    }
}
