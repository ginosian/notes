package com.margin.disqo.rest;

import com.margin.disqo.dto.ListResponseDTO;
import com.margin.disqo.dto.NoteCreationDTO;
import com.margin.disqo.dto.NoteDTO;
import com.margin.disqo.dto.NoteUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequestMapping(path = "/{userId}/notes")
public interface NotesEndpoint {

    @PostMapping(path = "")
    ResponseEntity<NoteDTO> create(@PathParam("userId") Long userId, @RequestBody NoteCreationDTO noteCreationDTO);

    @GetMapping(path = "/{noteId}")
    ResponseEntity<NoteDTO> get(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId);

    @GetMapping(path = "")
    ResponseEntity<ListResponseDTO<NoteDTO>> getAll(
            @PathParam("userId") Long userId,
            @PathVariable("page") int page,
            @PathVariable("size") int size);

    @PutMapping(path = "/{noteId}")
    ResponseEntity<NoteDTO> update(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId, @RequestBody NoteUpdateDTO noteUpdateDTO);

    @PatchMapping(path = "/{noteId}")
    ResponseEntity<NoteDTO> partialUpdate(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId, @RequestBody NoteUpdateDTO noteUpdateDTO);

    @DeleteMapping(path = "/{noteId}")
    ResponseEntity<Boolean> delete(@PathParam("userId") Long userId, @PathParam("noteId") Long noteId);

}
