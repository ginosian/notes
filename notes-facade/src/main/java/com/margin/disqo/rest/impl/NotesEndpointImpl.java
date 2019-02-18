package com.margin.disqo.rest.impl;

import com.margin.disqo.BeanMapper;
import com.margin.disqo.dto.*;
import com.margin.disqo.dto.note.NoteCreationDTO;
import com.margin.disqo.dto.note.NoteDTO;
import com.margin.disqo.dto.note.NoteUpdateDTO;
import com.margin.disqo.rest.ExceptionHelper;
import com.margin.disqo.rest.NotesEndpoint;
import com.margin.disqo.service.note.NoteService;
import com.margin.disqo.service.note.model.*;
import com.margin.disqo.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ExceptionHelper exceptionHelper;

    @Override
    public ResponseDTO<NoteDTO> create(Long userId, NoteCreationDTO noteCreationDTO) {
        validationService.validate(noteCreationDTO);
        final NoteCreationRequest noteCreationRequest = beanMapper.map(noteCreationDTO, NoteCreationRequest.class);
//        noteCreationRequest.setUserId(userId);
        final NoteModel noteModel = noteService.create(noteCreationRequest);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseDTO<>(null, response);
    }

    @Override
    public ResponseDTO<NoteDTO> get(Long userId, Long noteId) {
        final NoteModel noteModel = noteService.get(noteId);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseDTO<>(null, response);
    }

    @Override
    public ListResponseDTO<NoteDTO> getAll(Long userId, int page, int size) {
        final List<NoteModel> noteModels = noteService.getAll(new PagingMetaData(page, size));
        final List<NoteDTO> responseList = beanMapper.mapAsList(noteModels, NoteDTO.class);
        return new ListResponseDTO<>(null, responseList.size(), page, size, responseList);
    }

    @Override
    public ResponseDTO<NoteDTO> update(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        validationService.validate(noteUpdateDTO);
        final NoteUpdateRequest request = beanMapper.map(noteUpdateDTO, NoteUpdateRequest.class);
        request.setUserId(userId);
        request.setNoteId(noteId);
        final NoteModel noteModel = noteService.update(request);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseDTO<>(null, response);
    }

    @Override
    public ResponseDTO<NoteDTO> partialUpdate(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        validationService.validate(noteUpdateDTO);
        final NoteUpdateRequest request = beanMapper.map(noteUpdateDTO, NoteUpdateRequest.class);
        request.setUserId(userId);
        request.setNoteId(noteId);
        final NoteModel noteModel = noteService.partialUpdate(request);
        final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
        return new ResponseDTO<>(null, response);
    }

    @Override
    public ResponseDTO<Boolean> delete(Long userId, Long noteId) {
        final Boolean response = noteService.delete(new NoteDeleteRequest(userId, noteId));
        return new ResponseDTO<>(null, response);
    }
}
