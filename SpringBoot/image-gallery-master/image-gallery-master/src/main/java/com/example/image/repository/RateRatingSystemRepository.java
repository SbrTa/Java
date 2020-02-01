package com.example.image.repository;

import com.example.image.entity.Movie;
import com.example.image.entity.RateRatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRatingSystemRepository extends JpaRepository<RateRatingSystem,Long> {
}
