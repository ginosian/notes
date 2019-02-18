package com.margin.disqo.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {
    private Long noteId;
    private Long userId;
    private String title;
    private String note;
}
