package com.example.image.controller;

import com.example.image.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/app")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }



    @GetMapping("/movie")
    public String getMovie(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
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


    @GetMapping("/movie/gallery")
    public String getMovieGallery(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "MovieGallery";
    }

    @PostMapping("/movie/gallery")
    public String postMovieGallery(@RequestParam("selectedMovie") List<String> selectedMovies) {
        return "MovieGallery";
    }

    @GetMapping("/movie/import")
    public String importMovieGallery(Model model) throws IOException {
        movieService.addMovieFromResource();
        model.addAttribute("movies", movieService.getAllMovies());
        return "MovieGallery";
    }

    @GetMapping("/movie/test")
    public String importMovieGalleryTest(Model model) throws IOException {
        movieService.importMoviesFromExcel();
        model.addAttribute("movies", movieService.getAllMovies());
        return "MovieGallery";
    }





}
