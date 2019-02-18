package com.margin.disqo.validation.component.general;

import com.margin.disqo.exception.ApiException;
import org.springframework.stereotype.Component;

@Component
public class TextLengthValidationComponent {

    public void validate(final String text, int length){
        if(text.length() > length){
            throw new ApiException(String.format("Invalid text length:'%s'", length), 400);
        }
    }
}
