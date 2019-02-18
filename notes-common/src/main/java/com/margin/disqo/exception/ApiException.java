package com.margin.disqo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
    private final String message;
    private final int httpStatusCode;
}
