package com.movieflix.movieflix.service;

import com.movieflix.movieflix.controller.request.MovieRequest;
import com.movieflix.movieflix.controller.response.MovieResponse;
import com.movieflix.movieflix.entity.Category;
import com.movieflix.movieflix.entity.Movie;
import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.exceptions.MovieNotFound;
import com.movieflix.movieflix.mapper.MovieMapper;
import com.movieflix.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService ,StreamingService streamingService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public MovieResponse save(MovieRequest movieRequest) {
        Movie movie = MovieMapper.toMovie(movieRequest);

        movie.setCategories(getCategories(movieRequest.categories()));
        movie.setStreamings(getStreamings(movieRequest.streamings()));

        Movie savedMovie = movieRepository.save(movie);

        return MovieMapper.toMovieResponse(savedMovie);
    }

    public List<MovieResponse> getAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    private List<Category> getCategories(List<Long> categories) {
        return categories.stream()
                .map(categoryService::getCategoryOrThrow)
                .toList();

    }

    private List<Streaming> getStreamings(List<Long> streamings) {
        return streamings.stream()
                .map(streamingService::getStreamingOrThrow)
                .toList();
    }

    public MovieResponse getMovieById(Long id){
        return MovieMapper.toMovieResponse(getMovieOrThrow(id));

    }

    public void deleteMovie(Long id){
        movieRepository.delete(getMovieOrThrow(id));
    }

    public MovieResponse updateMovie(Long id , MovieRequest movieRequest){
        Movie movie = getMovieOrThrow(id);
        List<Category> categories = getCategories(movieRequest.categories());
        List<Streaming> streamings = getStreamings(movieRequest.streamings());
        movie.setTitle(movieRequest.title());
        movie.setDescription(movieRequest.description());
        movie.setRating(movieRequest.rating());
        movie.setReleaseDate(movieRequest.releaseDate());

        movie.getCategories().clear();
        movie.getCategories().addAll(categories);
        movie.getStreamings().clear();
        movie.getStreamings().addAll(streamings);

        movieRepository.save(movie);
        return MovieMapper.toMovieResponse(movie);


    }

    public List<MovieResponse> moviesByCategory(Long id){
        List<Movie> movies = movieRepository.findMovieByCategories(List.of(Category.builder().id(id).build()));
        return movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    private Movie getMovieOrThrow(Long id){
        return movieRepository.findById(id)
                .orElseThrow(MovieNotFound::new);
    }
}
