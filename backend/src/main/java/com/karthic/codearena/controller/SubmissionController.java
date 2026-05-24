package com.karthic.codearena.controller;

import com.karthic.codearena.dto.SubmissionResponse;
import com.karthic.codearena.dto.SubmissionDTO;
import com.karthic.codearena.service.SubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    // 🔥 SUBMIT CODE
    @PostMapping
    public SubmissionResponse submitCode(
            @RequestParam Long problemId,
            @RequestParam String language,
            @RequestBody String code,
            org.springframework.security.core.Authentication authentication
    ) {
        String email = authentication.getName();
        return submissionService.submitCode(email, problemId, code, language);
    }

    // 🔹 GET submissions by userId (SAFE)
    @GetMapping("/user/{userId}")
    public List<SubmissionDTO> getUserSubmissions(@PathVariable Long userId) {
        return submissionService.getUserSubmissions(userId);
    }

    // 🔹 GET submissions by problemId (SAFE)
    @GetMapping("/problem/{problemId}")
    public List<SubmissionDTO> getProblemSubmissions(@PathVariable Long problemId) {
        return submissionService.getProblemSubmissions(problemId);
    }

    // 🔹 GET submissions by user + problem
    @GetMapping("/user/{userId}/problem/{problemId}")
    public List<SubmissionDTO> getUserProblemSubmissions(
            @PathVariable Long userId,
            @PathVariable Long problemId
    ) {
        return submissionService.getUserProblemSubmissions(userId, problemId);
    }

    // 🔹 GET logged-in user submissions
    @GetMapping("/my/problem/{problemId}")
    public List<SubmissionDTO> getMySubmissionsForProblem(
            @PathVariable Long problemId,
            org.springframework.security.core.Authentication authentication
    ) {
        String email = authentication.getName();
        return submissionService.getByUserEmailAndProblemId(email, problemId);
    }
}