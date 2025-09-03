package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.docs.MovieControllerDocs;
import com.movieflix.movieflix.controller.request.MovieRequest;
import com.movieflix.movieflix.controller.response.MovieResponse;
import com.movieflix.movieflix.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController implements MovieControllerDocs {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movieRequest));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovie(id, movieRequest));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> getAllMoviesByCategory(@RequestParam Long category){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.moviesByCategory(category));

    }
}
