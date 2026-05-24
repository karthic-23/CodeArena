package com.karthic.codearena.service;

import com.karthic.codearena.dto.SubmissionResponse;
import com.karthic.codearena.dto.SubmissionDTO;
import com.karthic.codearena.model.*;
import com.karthic.codearena.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    // ============================================
    // 🔥 SUBMIT CODE (CORE LOGIC)
    // ============================================
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

            allInput = problem.getInputExample() + "\n\n" + problem.getHiddenInput();
            allExpected = problem.getOutputExample() + "\n" + problem.getHiddenOutput();

        } else {
            allInput = problem.getInputExample();
            allExpected = problem.getOutputExample();
        }

        String[] inputs = allInput.split("\\n\\n");
        String[] outputs = allExpected.split("\\n");

        if (inputs.length != outputs.length) {
            throw new RuntimeException("Mismatch between input and output test cases");
        }

        int totalTestCases = inputs.length;
        int passedCount = 0;

        String status = "ACCEPTED";
        String errorType = null;

        int failedTestCaseIndex = -1;
        String failedExpected = null;
        String failedActual = null;

        // ============================================
        // 🔥 4. EXECUTE ALL TEST CASES
        // ============================================
        for (int i = 0; i < inputs.length; i++) {

            String input = inputs[i].trim();
            String expected = outputs[i].trim();

            String actual = CodeExecutor.executeJava(code, input);

            // 🔴 Handle execution errors
            if (actual.equals("COMPILATION_ERROR") ||
                actual.equals("RUNTIME_ERROR") ||
                actual.equals("TIME_LIMIT_EXCEEDED")) {

                status = actual;
                errorType = actual;
                failedTestCaseIndex = i + 1;
                break;
            }

            // 🔹 Check correctness
            if (actual != null && actual.trim().equals(expected)) {
                passedCount++;
            } else {
                status = "WRONG_ANSWER";
                errorType = "WRONG_ANSWER";

                failedTestCaseIndex = i + 1;
                failedExpected = expected;
                failedActual = actual;
                break;
            }
        }

        // ============================================
        // 💾 5. STORE FULL RESULT IN DB
        // ============================================
        Submission submission = new Submission(user, problem, code, language, status);

        submission.setFailedTestCase(failedTestCaseIndex);
        submission.setExpectedOutput(failedExpected);
        submission.setActualOutput(failedActual);
        submission.setErrorMessage(errorType);

        submissionRepository.save(submission);

        // ============================================
        // 👀 6. RUN ONLY VISIBLE TEST CASES (FOR UI)
        // ============================================
        List<String> visibleOutputs = new ArrayList<>();

        String[] visibleInputs = problem.getInputExample().split("\\n\\n");

        for (String input : visibleInputs) {
            String output = CodeExecutor.executeJava(code, input.trim());
            visibleOutputs.add(output);
        }

        // ============================================
        // 📤 7. RETURN CLEAN RESPONSE
        // ============================================
        return new SubmissionResponse(
                status,
                passedCount,
                totalTestCases,
                errorType,
                failedTestCaseIndex,
                visibleOutputs
        );
    }

    // ============================================
    // 📊 GET SUBMISSIONS (SAFE DTO)
    // ============================================

    public List<SubmissionDTO> getUserSubmissions(Long userId) {
        return submissionRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SubmissionDTO> getProblemSubmissions(Long problemId) {
        return submissionRepository.findByProblemId(problemId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SubmissionDTO> getUserProblemSubmissions(Long userId, Long problemId) {
        return submissionRepository.findByUserIdAndProblemId(userId, problemId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SubmissionDTO> getByUserEmailAndProblemId(String email, Long problemId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return submissionRepository.findByUserIdAndProblemId(user.getId(), problemId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ============================================
    // 🔧 HELPER METHOD (DTO CONVERSION)
    // ============================================
    private SubmissionDTO convertToDTO(Submission sub) {
        return new SubmissionDTO(
                sub.getId(),
                sub.getUser().getEmail(),
                sub.getProblem().getTitle(),
                sub.getStatus(),
                sub.getFailedTestCase() != null ? sub.getFailedTestCase() : -1,
                sub.getLanguage()
        );
    }
}