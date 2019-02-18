package com.margin.disqo.service.note.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDeleteRequest {
    private Long noteId;
    private Long userId;
}
