package com.margin.disqo.rest;

import com.margin.disqo.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/{userId}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface NotesEndpoint {

    @POST
    @Path("")
    ResponseDTO<NoteDTO> create(@PathParam("userId") Long userId, NoteCreationDTO noteCreationDTO);

    @GET
    @Path("/{noteId}")
    ResponseDTO<NoteDTO> get(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId);

    @GET
    @Path("")
    ListResponseDTO<NoteDTO> getAll(
            @PathParam("userId") Long userId,
            @QueryParam("page") int page,
            @QueryParam("size") int size);

    @PUT
    @Path("/{noteId}")
    ResponseDTO<NoteDTO> update(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId, NoteUpdateDTO noteUpdateDTO);

    @PATCH
    @Path("/{noteId}")
    ResponseDTO<NoteDTO> partialUpdate(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId, NoteUpdateDTO noteUpdateDTO);

    @DELETE
    @Path("/{noteId}")
    ResponseDTO<Boolean> delete(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId);

}
