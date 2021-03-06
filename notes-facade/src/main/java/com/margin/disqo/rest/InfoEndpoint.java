package com.margin.disqo.rest;

import com.margin.disqo.dto.info.InfoDTO;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InfoEndpoint {
    @GET
    @Path("")
    ResponseEntity<InfoDTO> info();
}
