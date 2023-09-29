package com.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    existingMovie.setTitle(updatedMovie.getTitle());
                    existingMovie.setDescription(updatedMovie.getDescription());
                    existingMovie.setDuration(updatedMovie.getDuration());
                    return movieRepository.save(existingMovie);
                })
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
