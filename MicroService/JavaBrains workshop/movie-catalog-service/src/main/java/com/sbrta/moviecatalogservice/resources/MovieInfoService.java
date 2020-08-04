package com.sbrta.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sbrta.moviecatalogservice.model.CatalogItem;
import com.sbrta.moviecatalogservice.model.Movie;
import com.sbrta.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author SbrTa
 * @since 8/4/2020  12:39 AM
 */

@Service
public class MovieInfoService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")
    })
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
        /*Movie movie = webClientBuilder.build()
                .get()
                .uri("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();*/
        return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie not found", "", 0);
    }
}
