package com.example.image.repository;

import com.example.image.entity.Rating;
import com.example.image.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Rating findFirstByIsRatedOrderBySerialAsc(boolean isRated);
    List<Rating> findAllByUserId(Long userId);
    Rating findByUserIdAndSerial(Long userId, Long serial);
    List<Rating> findDistinctMovieIdByUserId(Long userId);
}
