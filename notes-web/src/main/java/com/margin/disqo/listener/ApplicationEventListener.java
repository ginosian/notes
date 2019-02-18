package com.margin.disqo.listener;

import com.google.common.collect.Sets;
import com.margin.disqo.entity.ApiUser;
import com.margin.disqo.entity.Role;
import com.margin.disqo.repository.AbstractRepository;
import com.margin.disqo.rest.InfoEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationEventListener {

    @Autowired
    private InfoEndpoint endpoint;

    @Autowired
    private AbstractRepository abstractRepository;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        final List<Role> roles = StaticData.createRoles();
        roles.forEach(role -> role =abstractRepository.save(role));

        final ApiUser apiUser = StaticData.apiUser();
        abstractRepository.save(apiUser);
        abstractRepository.save(StaticData.userDetails(Sets.newHashSet(roles), apiUser, "a@a.com"));

    }
}
