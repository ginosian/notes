package com.margin.disqo.dto;

import com.margin.disqo.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDTO<T> {
    private ApiException exception;
    private long totalItems;
    private long page;
    private long size;
    private List<T> items = new ArrayList<>();
}
