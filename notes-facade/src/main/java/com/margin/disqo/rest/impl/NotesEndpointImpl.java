package com.margin.disqo.rest.impl;

import com.margin.disqo.BeanMapper;
import com.margin.disqo.dto.*;
import com.margin.disqo.dto.note.NoteCreationDTO;
import com.margin.disqo.dto.note.NoteDTO;
import com.margin.disqo.dto.note.NoteUpdateDTO;
import com.margin.disqo.rest.component.ExceptionHelper;
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
        ResponseDTO<NoteDTO> responseEntity;
        try {
            validationService.validate(noteCreationDTO);
            final NoteCreationRequest noteCreationRequest = beanMapper.map(noteCreationDTO, NoteCreationRequest.class);
            noteCreationRequest.setUserId(userId);
            final NoteModel noteModel = noteService.create(noteCreationRequest);
            final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
            responseEntity = new ResponseDTO<>(null, response);
        } catch (Exception e){
            responseEntity = new ResponseDTO<>(exceptionHelper.resolve(e, 500), null);
        }
        return responseEntity;
    }

    @Override
    public ResponseDTO<NoteDTO> get(Long userId, Long noteId) {
        ResponseDTO<NoteDTO> responseEntity;
        try {
            final NoteModel noteModel = noteService.get(noteId);
            final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
            responseEntity = new ResponseDTO<>(null, response);
        } catch (Exception e){
            responseEntity = new ResponseDTO<>(exceptionHelper.resolve(e, 500), null);
        }
        return responseEntity;
    }

    @Override
    public ListResponseDTO<NoteDTO> getAll(Long userId, int page, int size) {
        ListResponseDTO<NoteDTO> responseEntity;
        try {
            final List<NoteModel> noteModels = noteService.getAll(new PagingMetaData(page, size));
            final List<NoteDTO> responseList = beanMapper.mapAsList(noteModels, NoteDTO.class);
            responseEntity = new ListResponseDTO<>(null, responseList.size(), page, size, responseList);
        }catch (Exception e){
            responseEntity = new ListResponseDTO<>(exceptionHelper.resolve(e, 500), 0, page, size, null);
        }
        return responseEntity;
    }

    @Override
    public ListResponseDTO<NoteDTO> getAll(Long userId) {
        ListResponseDTO<NoteDTO> responseEntity;
        try {
            final List<NoteModel> noteModels = noteService.getAll();
            final List<NoteDTO> responseList = beanMapper.mapAsList(noteModels, NoteDTO.class);
            responseEntity = new ListResponseDTO<>(null, responseList.size(), 0, responseList.size(),responseList);
        }catch (Exception e){
            responseEntity = new ListResponseDTO<>(exceptionHelper.resolve(e, 500), 0, 0, 0, null);
        }
        return responseEntity;
    }

    @Override
    public ResponseDTO<NoteDTO> update(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        ResponseDTO<NoteDTO> responseEntity;
        try {
            validationService.validate(noteUpdateDTO);
            final NoteUpdateRequest request = beanMapper.map(noteUpdateDTO, NoteUpdateRequest.class);
            request.setUserId(userId);
            request.setNoteId(noteId);
            final NoteModel noteModel = noteService.update(request);
            final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
            responseEntity = new ResponseDTO<>(null, response);
        } catch (Exception e){
            responseEntity = new ResponseDTO<>(exceptionHelper.resolve(e, 500), null);
        }
        return responseEntity;
    }

    @Override
    public ResponseDTO<NoteDTO> partialUpdate(Long userId, Long noteId, NoteUpdateDTO noteUpdateDTO) {
        ResponseDTO<NoteDTO> responseEntity;
        try {

            validationService.validate(noteUpdateDTO);
            final NoteUpdateRequest request = beanMapper.map(noteUpdateDTO, NoteUpdateRequest.class);
            request.setUserId(userId);
            request.setNoteId(noteId);
            final NoteModel noteModel = noteService.partialUpdate(request);
            final NoteDTO response = beanMapper.map(noteModel, NoteDTO.class);
            responseEntity = new ResponseDTO<>(null, response);
        } catch (Exception e){
            responseEntity = new ResponseDTO<>(exceptionHelper.resolve(e, 500), null);
        }
        return responseEntity;
    }

    @Override
    public ResponseDTO<Boolean> delete(Long userId, Long noteId) {
        ResponseDTO<Boolean> responseEntity;
        try {
            final Boolean response = noteService.delete(new NoteDeleteRequest(noteId, userId));
            responseEntity = new ResponseDTO<>(null, response);
        } catch (Exception e){
            responseEntity = new ResponseDTO<>(exceptionHelper.resolve(e, 500), null);
        }
        return responseEntity;
    }
}
