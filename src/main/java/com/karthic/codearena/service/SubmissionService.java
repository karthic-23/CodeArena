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

    // 🔥 CREATE SUBMISSION (MULTI TEST CASE)
    public Submission submitCode(String email, Long problemId, String code, String language) {

        // 🔹 1. Get user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 2. Get problem
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        // 🔹 3. Get all inputs & outputs
        String allInput = problem.getInputExample();
        String allExpected = problem.getOutputExample();

        // 🔥 Split test cases
        String[] inputs = allInput.split("\\n\\n");   // separate test cases
        String[] outputs = allExpected.split("\\n");  // expected outputs

        // 🔹 Safety check
        if (inputs.length != outputs.length) {
            throw new RuntimeException("Mismatch between input and output test cases");
        }

        boolean allPassed = true;

        // 🔥 4. Run each test case
        for (int i = 0; i < inputs.length; i++) {

            String input = inputs[i].trim();
            String expected = outputs[i].trim();

            String actual = CodeExecutor.executeJava(code, input);

            // 🔍 Debug logs (VERY useful)
            System.out.println("----- TEST CASE " + (i + 1) + " -----");
            System.out.println("INPUT:\n" + input);
            System.out.println("EXPECTED: " + expected);
            System.out.println("ACTUAL: " + actual);

            // 🔹 Compare output
            if (actual == null || !actual.trim().equals(expected)) {
                allPassed = false;
                break;
            }
        }

        // 🔹 Final status
        String status = allPassed ? "ACCEPTED" : "WRONG_ANSWER";

        // 🔹 Save submission
        Submission submission = new Submission(user, problem, code, language, status);

        return submissionRepository.save(submission);
    }

    // 🔹 GET BY USER
    public List<Submission> getUserSubmissions(Long userId) {
        return submissionRepository.findByUserId(userId);
    }

    // 🔹 GET BY PROBLEM
    public List<Submission> getProblemSubmissions(Long problemId) {
        return submissionRepository.findByProblemId(problemId);
    }
}