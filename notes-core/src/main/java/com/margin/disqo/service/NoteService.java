package com.margin.disqo.service;

import com.margin.disqo.service.model.*;

import java.util.List;

public interface NoteService {
    NoteModel create(NoteCreationRequest request);
    NoteModel get(Long id);
    List<NoteModel> getAll(PagingMetaData pagingMetaData);
    NoteModel update(NoteUpdateRequest request);
    NoteModel partialUpdate(NoteUpdateRequest request);
    boolean delete(NoteDeleteRequest request);
}
