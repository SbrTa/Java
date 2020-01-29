package com.example.image.controller;

import com.example.image.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/app")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public String getMovie(Model model) {
        model.addAttribute("movies", movieService.getAllMovieLogos());
        return "AddMovie";
    }

    @PostMapping("/movie")
    public String addMovie(@RequestParam("file") MultipartFile logo,
                           @RequestParam("name") String name) throws IOException {
        movieService.saveMovie(name,logo);
        return "redirect:/app/movie";
    }

    @PostMapping("/movie/remove")
    public String removeMovie(@RequestParam("id") Long id)  {
        movieService.removeMovie(id);
        return "redirect:/app/movie";
    }

}
