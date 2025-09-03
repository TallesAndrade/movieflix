package com.movieflix.movieflix.config;

import com.movieflix.movieflix.exceptions.CategoryNotFoundException;
import com.movieflix.movieflix.exceptions.MovieNotFound;
import com.movieflix.movieflix.exceptions.StreamingNotFoundException;
import com.movieflix.movieflix.exceptions.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<String> handleNotFoundException(UsernameOrPasswordInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFoundHandler(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MovieNotFound.class)
    public ResponseEntity<String> movieNotFoundHandler(MovieNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(StreamingNotFoundException.class)
    public ResponseEntity<String> streamingNotFoundHandler(StreamingNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgumentException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) ->
        {errors.put(error.getField(),error.getDefaultMessage());}
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
