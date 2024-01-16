package com.github.gifarj.cinema.criteria;

public enum FilmSort {
    ID("id"),
    NAME("name"),
    YEAR("year");

    private final String field;

    FilmSort(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return field;
    }
}
