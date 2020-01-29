package com.example.image.service;

import com.example.image.entity.Movie;
import com.example.image.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService extends BaseService{

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void saveMovie(String name, MultipartFile logo) throws IOException {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setLogo(this.convertMultipartToBase64Img(logo));
        movieRepository.save(movie);
    }

    public void removeMovie(Long id) {
        movieRepository.delete(id);
    }

    public List<Movie> getAllMovieLogos(){
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }
}
