package com.example.image.repository;

import com.example.image.entity.Movie;
import com.example.image.entity.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChoiceRepository extends JpaRepository<UserChoice,Long> {
}
