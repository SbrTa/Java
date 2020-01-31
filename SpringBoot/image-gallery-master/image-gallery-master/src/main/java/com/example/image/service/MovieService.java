package com.example.image.service;

import com.example.image.entity.Movie;
import com.example.image.model.ExcelColumnDetails;
import com.example.image.repository.MovieRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService extends BaseService{

    private MovieRepository movieRepository;
    private DataImportExportService dataExportService;

    public MovieService(MovieRepository movieRepository, DataImportExportService dataExportService) {
        this.movieRepository = movieRepository;
        this.dataExportService = dataExportService;
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

    public List<Movie> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public void exportMoviesToExcel(HttpServletResponse response){
        List<Movie> movies = this.getAllMovies();
        List<ExcelColumnDetails> columns = new ArrayList<>();
        columns.add(new ExcelColumnDetails("name", "name"));
        columns.add(new ExcelColumnDetails("logo", "logo"));
        dataExportService.exportAsExcel(response,"MovieData",columns,movies);
    }

    public void importMoviesFromExcel(){
        dataExportService.readFromExcel();
    }

    public void addMovieFromResource() throws IOException {
        Resource resource = new ClassPathResource("posters/react-square.png");
        InputStream input = resource.getInputStream();

        Movie movie = new Movie();
        movie.setName("android.png");
        movie.setLogo(this.convertInputStreamToBase64Img(input));
        movieRepository.save(movie);


    }
}
