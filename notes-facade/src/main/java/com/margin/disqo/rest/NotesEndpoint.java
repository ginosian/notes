package com.margin.disqo.rest;

import com.margin.disqo.dto.*;
import com.margin.disqo.dto.note.NoteCreationDTO;
import com.margin.disqo.dto.note.NoteDTO;
import com.margin.disqo.dto.note.NoteUpdateDTO;
import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Api(
        tags = {"notes"},
        authorizations = {@Authorization("accessTokenAuth")}
)
@Path("/{userId}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface NotesEndpoint {


    @ApiOperation(
            value = "Create a note.",
            notes = "Should be used to create notes.",
            response = ResponseDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful creation of a Note", response = NoteDTO.class),
            @ApiResponse(code = 400, message = "Invalid values provided for Note creation.", response = ResponseDTO.class),
            @ApiResponse(code = 500, message = "Unexpected error occurred, please contact support: marta.h.ginosian@gmail.com.", response = ResponseDTO.class)
    })
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
