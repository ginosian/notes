package com.margin.disqo.rest.impl;

import com.margin.disqo.dto.ListResponseDTO;
import com.margin.disqo.dto.NoteCreationDTO;
import com.margin.disqo.dto.NoteDTO;
import com.margin.disqo.dto.NoteUpdateDTO;
import com.margin.disqo.rest.NotesEndpoint;
import com.margin.disqo.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesEndpointImpl implements NotesEndpoint {

    @Autowired
    private ValidationService validationService;

    @Override
    public ResponseEntity<NoteDTO> create(Long userId, NoteCreationDTO noteCreationDTO) {
        validationService.validate(noteCreationDTO);
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
        validationService.validate(noteUpdateDTO);
        return null;
    }

    @Override
    public ResponseEntity<NoteDTO> partialUpdate(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        validationService.validate(noteUpdateDTO);
        return null;
    }

    @Override
    public ResponseEntity<NoteDTO> delete(Long userId, Long noteId) {
        return null;
    }
}
