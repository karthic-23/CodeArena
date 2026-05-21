package com.karthic.codearena.service;

import com.karthic.codearena.model.*;
import com.karthic.codearena.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    // CREATE SUBMISSION
    public Submission submitCode(String email, Long problemId, String code, String language) {

        // 🔹 Get user using email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 Get problem
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        // 🔥 Dummy evaluation
        String status = code.contains("return") ? "ACCEPTED" : "WRONG_ANSWER";

        Submission submission = new Submission(user, problem, code, language, status);

        return submissionRepository.save(submission);
    }

    // GET BY USER
    public List<Submission> getUserSubmissions(Long userId) {
        return submissionRepository.findByUserId(userId);
    }

    // GET BY PROBLEM
    public List<Submission> getProblemSubmissions(Long problemId) {
        return submissionRepository.findByProblemId(problemId);
    }
}