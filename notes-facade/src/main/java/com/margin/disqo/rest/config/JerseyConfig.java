package com.margin.disqo.rest.config;

import com.margin.disqo.rest.impl.InfoEndpointImpl;
import com.margin.disqo.rest.impl.NotesEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("/api/v1")
public class JerseyConfig extends AbstractJerseyConfig {

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(NotesEndpointImpl.class);
        register(InfoEndpointImpl.class);
    }
}
