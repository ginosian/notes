package com.margin.disqo.service.note.component;

import com.margin.disqo.entity.Note;
import com.margin.disqo.service.note.model.NoteCreationRequest;
import com.margin.disqo.service.note.model.NoteModel;
import com.margin.disqo.service.note.model.NoteUpdateRequest;
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
        noteModel.setCreated(request.getCreated());
        noteModel.setUpdated(request.getUpdated());
        return noteModel;
    }
}
