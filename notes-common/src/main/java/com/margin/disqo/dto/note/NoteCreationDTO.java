package com.margin.disqo.dto.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteCreationDTO implements NoteDeclaration{
    private String title;
    private String note;

    @Override
    public String getNoteTitle() {
        return title;
    }

    @Override
    public String getNoteText() {
        return note;
    }
}
