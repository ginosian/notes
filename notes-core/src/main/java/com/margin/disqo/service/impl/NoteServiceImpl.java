package com.margin.disqo.service.impl;

import com.margin.disqo.entity.Note;
import com.margin.disqo.repository.NoteRepository;
import com.margin.disqo.repository.UserRepository;
import com.margin.disqo.service.NoteService;
import com.margin.disqo.service.component.NoteConverter;
import com.margin.disqo.service.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteConverter noteConverter;

    @Override
    public NoteModel create(final NoteCreationRequest request) {
        notNull(request, "Request can not be null.");
        //TODO Validate content and User Id.
        return noteConverter.convert(noteRepository.save(noteConverter.convert(request)));
    }

    @Override
    public NoteModel get(final Long id) {
        notNull(id, "ID can not be null.");
        //TODO Validate content and User Id.
        return noteConverter.convert(noteRepository.getOne(id));
    }

    @Override
    public List<NoteModel> getAll(final PagingMetaData pagingMetaData) {
        notNull(pagingMetaData, "Paging metadata can not be null.");
        //TODO Validate content and User Id.
        return null;
    }

    @Override
    public NoteModel update(final NoteUpdateRequest request) {
        notNull(request, "Request can not be null.");
        //TODO Validate content and User Id.
        return noteConverter.convert(noteRepository.save(noteConverter.convert(request)));
    }

    @Override
    public NoteModel partialUpdate(final NoteUpdateRequest request) {
        notNull(request, "Request can not be null.");
        //TODO Validate content and User Id.
        final Note note = noteRepository.getOne(request.getNoteId());
        final String title = request.getTitle();
        final String noteText = request.getNote();
        if(StringUtils.isNotEmpty(title)){
            note.setTitle(title);
        }
        if(StringUtils.isNotEmpty(noteText)){
            note.setNote(noteText);
        }
        return noteConverter.convert(noteRepository.save(noteConverter.convert(request)));
    }

    @Override
    public boolean delete(final NoteDeleteRequest request) {
        notNull(request, "Request can not be null.");
        //TODO Validate content and User Id.
        final Note note = noteRepository.getOne(request.getNoteId());
        noteRepository.delete(note);
        return true;
    }
}
