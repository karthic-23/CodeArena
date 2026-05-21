package com.karthic.codearena.controller;

import com.karthic.codearena.model.Submission;
import com.karthic.codearena.service.SubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    // SUBMIT CODE
@PostMapping
    public Submission submitCode(
            @RequestParam Long problemId,
            @RequestParam String language,
            @RequestBody String code,
            org.springframework.security.core.Authentication authentication
    ) {
        // 🔹 Get logged-in user's email from JWT
        String email = authentication.getName();

        return submissionService.submitCode(email, problemId, code, language);
    }

    // GET USER SUBMISSIONS
    @GetMapping("/user/{userId}")
    public List<Submission> getUserSubmissions(@PathVariable Long userId) {
        return submissionService.getUserSubmissions(userId);
    }

    // GET PROBLEM SUBMISSIONS
    @GetMapping("/problem/{problemId}")
    public List<Submission> getProblemSubmissions(@PathVariable Long problemId) {
        return submissionService.getProblemSubmissions(problemId);
    }
}