package com.margin.disqo.validation.impl;

import com.margin.disqo.dto.note.NoteDeclaration;
import com.margin.disqo.validation.ValidationService;
import com.margin.disqo.validation.component.NoteTextValidationComponent;
import com.margin.disqo.validation.component.TitleValidationComponent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.hasText;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private NoteTextValidationComponent noteTextValidationComponent;

    @Autowired
    private TitleValidationComponent titleValidationComponent;

    @Override
    public void validate(NoteDeclaration note) {
        final String text = note.getNoteText();
        final String title = note.getNoteTitle();
        hasText(title, "Title can not be null or empty");
        if(StringUtils.isNotEmpty(text)){
            noteTextValidationComponent.validate(text);
        }
        titleValidationComponent.validate(note.getNoteTitle());
    }
}
