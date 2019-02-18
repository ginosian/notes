package com.margin.disqo.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteUpdateRequest {
    private Long noteId;
    private String title;
    private String note;
}
