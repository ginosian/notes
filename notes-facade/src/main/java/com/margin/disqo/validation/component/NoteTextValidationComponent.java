package com.margin.disqo.validation.component;

import com.margin.disqo.validation.component.general.TextLengthValidationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteTextValidationComponent {

    @Autowired
    private TextLengthValidationComponent textLengthValidationComponent;

    public void validate(final String noteText){
        textLengthValidationComponent.validate(noteText, 100);
    }
}
