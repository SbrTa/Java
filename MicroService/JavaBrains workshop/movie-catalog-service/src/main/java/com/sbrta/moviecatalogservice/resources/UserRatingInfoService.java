package com.sbrta.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sbrta.moviecatalogservice.model.Rating;
import com.sbrta.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

/**
 * @author SbrTa
 * @since 8/4/2020  12:40 AM
 */

@Service
public class UserRatingInfoService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating", commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")
    })
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/user/" + userId, UserRating.class);

       /* return webClientBuilder.build()
                .get()
                .uri("http://RATINGS-DATA-SERVICE/ratings/user/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
//                .timeout(Duration.ofMillis(1000))
//                .doOnError(err -> System.out.println("\n\nRATING ERROR = " + err.getMessage() + "\n\n"))
                .block();*/
    }

    private UserRating getFallbackUserRating(String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(
                Arrays.asList(new Rating("0", 0))
        );
        return userRating;
    }
}