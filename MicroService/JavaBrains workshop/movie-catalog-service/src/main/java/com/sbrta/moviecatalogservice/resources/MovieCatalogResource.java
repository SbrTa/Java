package com.sbrta.moviecatalogservice.resources;

import com.sbrta.moviecatalogservice.model.CatalogItem;
import com.sbrta.moviecatalogservice.model.Movie;
import com.sbrta.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SbrTa
 * @since 8/2/2020  4:54 PM
 */

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = webClientBuilder.build()
                .get()
                .uri("http://RATINGS-DATA-SERVICE/ratings/user/1")
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();

        return userRating.getRatings().stream().map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(), "Science fiction", rating.getRating());
        }).collect(Collectors.toList());
        // For each movie, get movie info
    }
}
