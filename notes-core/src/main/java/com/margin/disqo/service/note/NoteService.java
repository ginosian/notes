package com.margin.disqo.service.note;

import com.margin.disqo.service.note.model.*;

import java.util.List;

public interface NoteService {
    NoteModel create(NoteCreationRequest request);
    NoteModel get(Long id);
    List<NoteModel> getAll(PagingMetaData pagingMetaData);
    List<NoteModel> getAll();
    NoteModel update(NoteUpdateRequest request);
    NoteModel partialUpdate(NoteUpdateRequest request);
    boolean delete(NoteDeleteRequest request);
}
