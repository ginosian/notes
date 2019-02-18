package com.margin.disqo.rest;

import com.margin.disqo.exception.ApiException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHelper {
    public ApiException resolve(final Exception exception, final int httpStatusCode){
        if(exception instanceof ApiException){
            return (ApiException) exception;
        } else {
            return create(exception.getMessage(), httpStatusCode);
        }
    }

    private ApiException create(final String message, final int httpStatusCode){
        final ApiException exception = new ApiException(message, httpStatusCode);
        return exception;
    }


}
