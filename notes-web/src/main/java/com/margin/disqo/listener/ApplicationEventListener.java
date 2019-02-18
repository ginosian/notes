package com.margin.disqo.listener;

import com.margin.disqo.rest.InfoEndpoint;
import com.margin.disqo.rest.impl.InfoEndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    @Autowired
    private InfoEndpoint endpoint;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        endpoint.info();
    }
}
