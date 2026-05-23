package com.karthic.codearena.repository;

import com.karthic.codearena.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByUserId(Long userId);

    List<Submission> findByProblemId(Long problemId);

    List<Submission> findByUserIdAndProblemId(Long userId, Long problemId);
}