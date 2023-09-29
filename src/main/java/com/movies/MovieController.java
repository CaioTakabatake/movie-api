package com.movies;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final List<Movie> movies = new ArrayList<>();

    @GetMapping("/")
    public List<Movie> getAllMovies() {
        return movies;
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        throw new MovieNotFoundException("Movie not found with id: " + id);
    }

    @PostMapping("/")
    public Movie addMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getId().equals(id)) {
                movies.set(i, updatedMovie);
                return updatedMovie;
            }
        }
        throw new MovieNotFoundException("Movie not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movies.removeIf(movie -> movie.getId().equals(id));
    }
}
