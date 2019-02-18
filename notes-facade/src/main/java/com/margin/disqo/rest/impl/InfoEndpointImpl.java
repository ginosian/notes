package com.margin.disqo.rest.impl;

import com.margin.disqo.dto.InfoDTO;
import com.margin.disqo.rest.InfoEndpoint;
import com.margin.disqo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InfoEndpointImpl implements InfoEndpoint {

    @Autowired
    private InfoService infoService;

    @Override
    public ResponseEntity<InfoDTO> info() {
        return new ResponseEntity<>(infoService.get(), HttpStatus.OK);
    }
}
