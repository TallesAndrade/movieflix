package com.movieflix.movieflix.exceptions;

public class StreamingNotFoundException extends RuntimeException {
    public StreamingNotFoundException(String message) {
        super(message);
    }

    public StreamingNotFoundException() {
        super("Streaming Not Found");
    }
}
