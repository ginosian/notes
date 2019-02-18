package com.margin.disqo.service.note.impl;

import com.margin.disqo.entity.ApiUser;
import com.margin.disqo.entity.Note;
import com.margin.disqo.exception.ApiException;
import com.margin.disqo.repository.NoteRepository;
import com.margin.disqo.repository.UserRepository;
import com.margin.disqo.service.note.NoteService;
import com.margin.disqo.service.note.component.NoteConverter;
import com.margin.disqo.service.note.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteConverter noteConverter;

    @Transactional
    @Override
    public NoteModel create(final NoteCreationRequest request) {
        notNull(request, "Request can not be null.");
        final Long userId = request.getUserId();
        notNull(userId, "UserId can not be null.");
        final ApiUser apiUser = userRepository.getOne(userId);
        final Note note = noteConverter.convert(request);
        note.setUser(apiUser);
        return noteConverter.convert(noteRepository.save(note));
    }

    @Override
    public NoteModel get(final Long id) {
        notNull(id, "ID can not be null.");
        return noteConverter.convert(noteRepository.getOne(id));
    }

    @Override
    public List<NoteModel> getAll(final PagingMetaData pagingMetaData) {
        notNull(pagingMetaData, "Paging metadata can not be null.");
        final PageRequest page = new PageRequest(pagingMetaData.getPage(), pagingMetaData.getSize());
        final Page<Note> noteList = noteRepository.findAll(page);
        final List<NoteModel> notes = noteList.stream().map(note -> noteConverter.convert(note)).collect(Collectors.toList());
        return notes;
    }

    @Override
    public List<NoteModel> getAll() {
        final List<Note> noteList = noteRepository.findAll();
        return noteList.stream().map(note -> noteConverter.convert(note)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public NoteModel update(final NoteUpdateRequest request) {
        notNull(request, "Request can not be null.");
        final Long userId = request.getUserId();
        notNull(userId, "UserId can not be null.");
        final Long noteId = request.getNoteId();
        notNull(userId, "NoteId can not be null.");
        if(!userRepository.existsById(userId)){
            throw new ApiException(String.format("User doesn't exist:'%s'", userId), 400);
        }
        final Note note = noteRepository.getOne(noteId);
        note.setTitle(request.getTitle());
        note.setNote(request.getNote());

        return noteConverter.convert(noteRepository.save(note));
    }

    @Transactional
    @Override
    public NoteModel partialUpdate(final NoteUpdateRequest request) {
        notNull(request, "Request can not be null.");
        final Long userId = request.getUserId();
        notNull(userId, "UserId can not be null.");
        final Long noteId = request.getNoteId();
        notNull(userId, "NoteId can not be null.");
        if(!userRepository.existsById(userId)){
            throw new ApiException(String.format("User doesn't exist:'%s'", userId), 400);
        }
        final Note note = noteRepository.getOne(noteId);
        final String title = request.getTitle();
        final String noteText = request.getNote();
        if(StringUtils.isNotEmpty(title)){
            note.setTitle(title);
        }
        if(StringUtils.isNotEmpty(noteText)){
            note.setNote(noteText);
        }
        return noteConverter.convert(noteRepository.save(note));
    }

    @Transactional
    @Override
    public boolean delete(final NoteDeleteRequest request) {
        notNull(request, "Request can not be null.");
        final Long userId = request.getUserId();
        notNull(userId, "UserId can not be null.");
        final Long noteId = request.getNoteId();
        notNull(userId, "NoteId can not be null.");
        if(!userRepository.existsById(userId)){
            throw new ApiException(String.format("User doesn't exist:'%s'", userId), 400);
        }
        final Note note = noteRepository.getOne(noteId);
        noteRepository.delete(note);
        return true;
    }
}
