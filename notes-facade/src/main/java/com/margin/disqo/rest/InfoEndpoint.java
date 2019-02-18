package com.margin.disqo.rest;

import com.margin.disqo.dto.InfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/info")
public interface InfoEndpoint {
    @GetMapping(path = "")
    ResponseEntity<InfoDTO> info();
}
