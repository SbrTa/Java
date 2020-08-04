package com.sbrta.moviedataservice.resources;

import com.sbrta.moviedataservice.models.Rating;
import com.sbrta.moviedataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author SbrTa
 * @since 8/3/2020  12:32 AM
 */

@RestController
@RequestMapping("/ratings")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating("Monpura", 5);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId) {
        /*try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }*/
        return new UserRating(
                userId,
                Arrays.asList(
                        new Rating("100", 5),
                        new Rating("200", 4)
                )
        );
    }

}
