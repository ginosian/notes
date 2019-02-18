package com.margin.disqo;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeanMapper extends ConfigurableMapper {
    private MapperFactory factory;

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
    }

}
