package com.margin.disqo.validation.component;


import com.margin.disqo.validation.component.general.TextLengthValidationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TitleValidationComponent {

    @Autowired
    private TextLengthValidationComponent textLengthValidationComponent;

    public void validate(final String titleText){
        textLengthValidationComponent.validate(titleText, 50);
    }
}
