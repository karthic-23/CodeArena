package com.karthic.codearena.service;

import com.karthic.codearena.dto.SubmissionResponse;
import com.karthic.codearena.model.*;
import com.karthic.codearena.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    public SubmissionResponse submitCode(String email, Long problemId, String code, String language) {

        // 🔹 1. Get user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 2. Get problem
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        // 🔥 3. Combine visible + hidden test cases
        String allInput;
        String allExpected;

        if (problem.getHiddenInput() != null && !problem.getHiddenInput().isEmpty()
                && problem.getHiddenOutput() != null && !problem.getHiddenOutput().isEmpty()) {

            // ✅ KEY FIX: combine both
            allInput = problem.getInputExample() + "\n\n" + problem.getHiddenInput();
            allExpected = problem.getOutputExample() + "\n" + problem.getHiddenOutput();

        } else {
            allInput = problem.getInputExample();
            allExpected = problem.getOutputExample();
        }

        // 🔥 4. Split test cases
        String[] inputs = allInput.split("\\n\\n");
        String[] outputs = allExpected.split("\\n");

        if (inputs.length != outputs.length) {
            throw new RuntimeException("Mismatch between input and output test cases");
        }

        int totalTestCases = inputs.length;
        int passedCount = 0;

        String status = "ACCEPTED";
        String errorType = null;

        // 🔥 5. Execute test cases
        for (int i = 0; i < inputs.length; i++) {

            String input = inputs[i].trim();
            String expected = outputs[i].trim();

            String actual = CodeExecutor.executeJava(code, input);

            System.out.println("----- TEST CASE " + (i + 1) + " -----");
            System.out.println("INPUT:\n" + input);
            System.out.println("EXPECTED: " + expected);
            System.out.println("ACTUAL: " + actual);

            // 🔥 Handle execution errors FIRST
            if (actual.equals("COMPILATION_ERROR") ||
                actual.equals("RUNTIME_ERROR") ||
                actual.equals("TIME_LIMIT_EXCEEDED")) {

                status = actual;
                errorType = actual;
                break;
            }

            // 🔹 Check correctness
            if (actual != null && actual.trim().equals(expected)) {
                passedCount++;
            } else {
                status = "WRONG_ANSWER";
                errorType = "WRONG_ANSWER";
                break;
            }
        }

        // 🔹 Save submission (minimal)
        Submission submission = new Submission(user, problem, code, language, status);
        submissionRepository.save(submission);

        // 🔹 Return clean DTO response
        return new SubmissionResponse(
                status,
                passedCount,
                totalTestCases,
                errorType
        );
    }

    // 🔹 GET by userId
    public java.util.List<Submission> getUserSubmissions(Long userId) {
        return submissionRepository.findByUserId(userId);
    }

    // 🔹 GET by problemId
    public java.util.List<Submission> getProblemSubmissions(Long problemId) {
        return submissionRepository.findByProblemId(problemId);
    }

    // 🔥 NEW: GET by user + problem
    public java.util.List<Submission> getUserProblemSubmissions(Long userId, Long problemId) {
        return submissionRepository.findByUserIdAndProblemId(userId, problemId);
    }

    // 🔥 BEST: GET using email (secure)
    public java.util.List<Submission> getByUserEmailAndProblemId(String email, Long problemId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return submissionRepository.findByUserIdAndProblemId(user.getId(), problemId);
    }
}