package com.margin.disqo.rest.impl;

import com.margin.disqo.BeanMapper;
import com.margin.disqo.dto.ListResponseDTO;
import com.margin.disqo.dto.NoteCreationDTO;
import com.margin.disqo.dto.NoteDTO;
import com.margin.disqo.dto.NoteUpdateDTO;
import com.margin.disqo.rest.NotesEndpoint;
import com.margin.disqo.service.NoteService;
import com.margin.disqo.service.model.*;
import com.margin.disqo.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotesEndpointImpl implements NotesEndpoint {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public ResponseEntity<NoteDTO> create(Long userId, NoteCreationDTO noteCreationDTO) {
        validationService.validate(noteCreationDTO);
        final NoteCreationRequest noteCreationRequest = beanMapper.map(noteCreationDTO, NoteCreationRequest.class);
        noteCreationRequest.setUserId(userId);
        final NoteModel noteModel = noteService.create(noteCreationRequest);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NoteDTO> get(Long userId, Long noteId) {
        final NoteModel noteModel = noteService.get(noteId);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListResponseDTO<NoteDTO>> getAll(Long userId, int page, int size) {
        final List<NoteModel> noteModels = noteService.getAll(new PagingMetaData(page, size));
        final List<NoteDTO> responseList = beanMapper.mapAsList(noteModels, NoteDTO.class);
        final ListResponseDTO<NoteDTO> response = new ListResponseDTO<>(null, responseList.size(), page, size, responseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NoteDTO> update(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        validationService.validate(noteUpdateDTO);
        final NoteUpdateRequest request = beanMapper.map(noteUpdateDTO, NoteUpdateRequest.class);
        request.setUserId(userId);
        request.setNoteId(noteId);
        final NoteModel noteModel = noteService.update(request);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NoteDTO> partialUpdate(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        validationService.validate(noteUpdateDTO);
        final NoteUpdateRequest request = beanMapper.map(noteUpdateDTO, NoteUpdateRequest.class);
        request.setUserId(userId);
        request.setNoteId(noteId);
        final NoteModel noteModel = noteService.partialUpdate(request);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> delete(Long userId, Long noteId) {
        final Boolean response = noteService.delete(new NoteDeleteRequest(userId, noteId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
