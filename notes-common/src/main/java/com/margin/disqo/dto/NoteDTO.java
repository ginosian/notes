package com.margin.disqo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private String title;
    private String note;
    private LocalDateTime created;
    private LocalDateTime updated;
}
