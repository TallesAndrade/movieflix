package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.request.MovieRequest;
import com.movieflix.movieflix.controller.response.MovieResponse;
import com.movieflix.movieflix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest movieRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movieRequest));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovie(id, movieRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> getAllMoviesByCategory(@RequestParam Long category){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.moviesByCategory(category));

    }
}
