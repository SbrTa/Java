package com.example.image.controller;

import com.example.image.Entity.Movie;
import com.example.image.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.UUID;

@Controller
public class MovieController {
    private Path rootLocation;
    private MovieRepository movieRepository;

    public MovieController(Path rootLocation, MovieRepository movieRepository) {
        this.rootLocation = rootLocation;
        this.movieRepository = movieRepository;
    }

    @PostMapping("/movie/add")
    public String addMovie(@RequestParam("file") MultipartFile image,
                            @RequestParam("name") String name, RedirectAttributes redirectAttributes){
        String uuid = UUID.randomUUID().toString();
        String imagePath = this.rootLocation.resolve(uuid + image.getOriginalFilename()).toString();

        Movie movie = new Movie();
        movie.setMovieName(name);
        movie.setMoviePath(imagePath);
        movieRepository.save(movie);
        return "redirect:/";
    }
}
