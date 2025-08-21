package com.movieflix.movieflix.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super();
    }

    public CategoryNotFoundException() {
        super("Category Not Found");
    }
}
