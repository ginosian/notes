package com.margin.disqo.service.note.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {
    private Long noteId;
    private Long userId;
    private String title;
    private String note;
    private LocalDateTime created;
    private LocalDateTime updated;
}
