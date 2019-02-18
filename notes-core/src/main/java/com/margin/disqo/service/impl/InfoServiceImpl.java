package com.margin.disqo.service.impl;

import com.margin.disqo.dto.info.InfoDTO;
import com.margin.disqo.service.InfoService;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Override
    public InfoDTO get() {
        return new InfoDTO("This Disqo Notes application and it works just fine.");
    }
}
