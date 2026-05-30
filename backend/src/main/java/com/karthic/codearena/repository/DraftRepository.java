package com.karthic.codearena.repository;

import com.karthic.codearena.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DraftRepository
        extends JpaRepository<Draft, Long> {

    Optional<Draft> findByUserIdAndProblemId(
            Long userId,
            Long problemId);
}