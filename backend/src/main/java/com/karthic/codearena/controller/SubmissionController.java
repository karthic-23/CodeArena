package com.karthic.codearena.controller;

import com.karthic.codearena.dto.SubmissionResponse;
import com.karthic.codearena.dto.SubmissionDTO;
import com.karthic.codearena.service.SubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;

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

    @GetMapping("/status/{problemId}")
    public String getProblemStatus(
            @PathVariable Long problemId,
            org.springframework.security.core.Authentication authentication
    ) {
        String email = authentication.getName();

        List<SubmissionDTO> submissions =
                submissionService.getByUserEmailAndProblemId(email, problemId);

        for (SubmissionDTO sub : submissions) {
            if ("ACCEPTED".equalsIgnoreCase(sub.getStatus())) {
                return "SOLVED";
            }
        }

        return "UNSOLVED";
    }

    @GetMapping("/solved")
    public List<Long> getSolvedProblems(
            org.springframework.security.core.Authentication authentication
    ) {
        String email = authentication.getName();

        List<SubmissionDTO> submissions =
                submissionService.getByUserEmail(email);

        return submissions.stream()
                .filter(s -> "ACCEPTED".equalsIgnoreCase(s.getStatus()))
                .map(SubmissionDTO::getProblemId)
                .distinct()
                .toList();
    }

    @GetMapping("/stats")
    public Map<String, Object> getUserStats(Authentication authentication) {

        String email = authentication.getName();

        List<SubmissionDTO> submissions =
                submissionService.getByUserEmail(email);

        // ✅ only accepted
        List<SubmissionDTO> accepted = submissions.stream()
                .filter(s -> "ACCEPTED".equalsIgnoreCase(s.getStatus()))
                .toList();

        // ✅ unique solved problems
        Map<Long, SubmissionDTO> unique = new HashMap<>();

        for (SubmissionDTO s : accepted) {
            unique.putIfAbsent(s.getProblemId(), s);
        }

        long solved = unique.size();

        long easy = unique.values().stream()
                .filter(s -> "EASY".equalsIgnoreCase(s.getDifficulty()))
                .count();

        long medium = unique.values().stream()
                .filter(s -> "MEDIUM".equalsIgnoreCase(s.getDifficulty()))
                .count();

        long hard = unique.values().stream()
                .filter(s -> "HARD".equalsIgnoreCase(s.getDifficulty()))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("solved", solved);
        stats.put("total", 50);
        stats.put("easy", easy);
        stats.put("medium", medium);
        stats.put("hard", hard);

        return stats;
    }

    @GetMapping("/leaderboard")
    public List<Map<String, Object>> getLeaderboard() {
        return submissionService.getLeaderboard();
    }
        
}