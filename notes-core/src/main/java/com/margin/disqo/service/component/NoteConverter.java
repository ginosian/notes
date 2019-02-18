package com.margin.disqo.service.component;

import com.margin.disqo.entity.Note;
import com.margin.disqo.service.model.NoteCreationRequest;
import com.margin.disqo.service.model.NoteModel;
import com.margin.disqo.service.model.NoteUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class NoteConverter {

    public Note convert(final NoteCreationRequest request){
        final Note note = new Note();
        note.setTitle(request.getTitle());
        note.setNote(request.getNote());
        return note;
    }

    public Note convert(final NoteUpdateRequest request){
        final Note note = new Note();
        note.setTitle(request.getTitle());
        note.setNote(request.getNote());
        return note;
    }

    public NoteModel convert(final Note request){
        NoteModel noteModel = new NoteModel();
        noteModel.setNote(request.getNote());
        noteModel.setTitle(request.getTitle());
        noteModel.setNoteId(request.getId());
        return noteModel;
    }
}
