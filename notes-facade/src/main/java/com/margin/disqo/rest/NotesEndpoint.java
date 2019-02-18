package com.margin.disqo.rest;

import com.margin.disqo.dto.*;
import com.margin.disqo.dto.note.NoteCreationDTO;
import com.margin.disqo.dto.note.NoteDTO;
import com.margin.disqo.dto.note.NoteUpdateDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/{userId}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface NotesEndpoint {

    @POST
    @Path("")
    @PreAuthorize("hasRole('OWNER')")
    ResponseDTO<NoteDTO> create(@PathParam("userId") Long userId, NoteCreationDTO noteCreationDTO);

    @GET
    @Path("/{noteId}")
    ResponseDTO<NoteDTO> get(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId);

    @GET
    @Path("")
    @PreAuthorize("hasRole('OWNER')")
    ListResponseDTO<NoteDTO> getAll(
            @PathParam("userId") Long userId,
            @QueryParam("page") int page,
            @QueryParam("size") int size);

    @PUT
    @Path("/{noteId}")
    @PreAuthorize("hasRole('OWNER')")
    ResponseDTO<NoteDTO> update(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId, NoteUpdateDTO noteUpdateDTO);

    @PATCH
    @Path("/{noteId}")
    @PreAuthorize("hasRole('OWNER')")
    ResponseDTO<NoteDTO> partialUpdate(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId, NoteUpdateDTO noteUpdateDTO);

    @DELETE
    @Path("/{noteId}")
    @PreAuthorize("hasRole('OWNER')")
    ResponseDTO<Boolean> delete(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId);

}
