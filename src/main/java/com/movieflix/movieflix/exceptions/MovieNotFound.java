package com.movieflix.movieflix.exceptions;

public class MovieNotFound extends RuntimeException {

    public MovieNotFound(String message) {
        super(message);
    }

    public MovieNotFound() {
        super("Movie not found");
    }
}
