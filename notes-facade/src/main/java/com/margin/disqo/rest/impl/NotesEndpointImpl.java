package com.margin.disqo.rest.impl;

import com.margin.disqo.dto.ListResponseDTO;
import com.margin.disqo.dto.NoteCreationDTO;
import com.margin.disqo.dto.NoteDTO;
import com.margin.disqo.dto.NoteUpdateDTO;
import com.margin.disqo.rest.NotesEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesEndpointImpl implements NotesEndpoint {

    @Override
    public ResponseEntity<NoteDTO> create(Long userId, NoteCreationDTO noteCreationDTO) {
        return null;
    }

    @Override
    public ResponseEntity<NoteDTO> get(Long userId, Long noteId) {
        return null;
    }

    @Override
    public ResponseEntity<ListResponseDTO<NoteDTO>> getAll(Long userId, Long noteId, int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<NoteDTO> update(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<NoteDTO> partialUpdate(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<NoteDTO> delete(Long userId, Long noteId) {
        return null;
    }
}
