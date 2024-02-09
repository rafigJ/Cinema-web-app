package com.github.gifarj.cinema.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Schema(name = "Page")
public class PageDto<E> {
    private List<E> content;
    private long totalElements;
    private int totalPages;

    private PageDto(List<E> content, long totalElements, int totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static <E> PageDto<E> of(Page<E> page) {
        return new PageDto<>(page.stream().toList(), page.getTotalElements(), page.getTotalPages());
    }
}
