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

        // ============================================
        // 🔥 3. BUILD TEST CASE LISTS (FIXED)
        // ============================================

        List<String> inputs = new ArrayList<>();
        List<String> outputs = new ArrayList<>();

        // ✅ Visible test cases (FIXED: use ;)
        if (problem.getInputExample() != null && !problem.getInputExample().isEmpty()) {
            inputs.addAll(Arrays.asList(problem.getInputExample().split(";")));
        }

        if (problem.getOutputExample() != null && !problem.getOutputExample().isEmpty()) {
            outputs.addAll(Arrays.asList(problem.getOutputExample().split(";")));
        }

        // ✅ Hidden test cases (already correct)
        if (problem.getHiddenInput() != null && !problem.getHiddenInput().isEmpty()) {
            inputs.addAll(Arrays.asList(problem.getHiddenInput().split(";")));
        }

        if (problem.getHiddenOutput() != null && !problem.getHiddenOutput().isEmpty()) {
            outputs.addAll(Arrays.asList(problem.getHiddenOutput().split(";")));
        }

        if (inputs.size() != outputs.size()) {
            throw new RuntimeException("Mismatch between test cases");
        }

        int totalTestCases = inputs.size();
        int passedCount = 0;

        String status = "ACCEPTED";
        String errorType = null;

        int failedTestCaseIndex = -1;
        String failedExpected = null;
        String failedActual = null;

        // ============================================
        // 🔥 4. EXECUTE TEST CASES
        // ============================================

        for (int i = 0; i < inputs.size(); i++) {

            String input = inputs.get(i).trim();
            String expected = outputs.get(i).trim();

            String actual = CodeExecutor.executeJava(code, input);

            // 🔴 Handle errors
            if (actual.equals("COMPILATION_ERROR") ||
                actual.equals("RUNTIME_ERROR") ||
                actual.equals("TIME_LIMIT_EXCEEDED")) {

                status = actual;
                errorType = actual;
                failedTestCaseIndex = i + 1;
                break;
            }

            // ✅ Normalize outputs (CRITICAL FIX)
            actual = actual.replace("\r", "").trim().replaceAll("\\s+", " ");
            expected = expected.replace("\r", "").trim().replaceAll("\\s+", " ");

            // ✅ Compare properly
            if (actual.equals(expected)) {
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
        // 💾 5. SAVE SUBMISSION
        // ============================================

        Submission submission = new Submission(user, problem, code, language, status);

        submission.setFailedTestCase(failedTestCaseIndex);
        submission.setExpectedOutput(failedExpected);
        submission.setActualOutput(failedActual);
        submission.setErrorMessage(errorType);

        submissionRepository.save(submission);

        // ============================================
        // 👀 6. RUN VISIBLE TEST CASES (FOR UI)
        // ============================================

        List<String> visibleOutputs = new ArrayList<>();

        if (problem.getInputExample() != null) {
            String[] visibleInputs = problem.getInputExample().split(";");

            for (String input : visibleInputs) {
                String output = CodeExecutor.executeJava(code, input.trim());
                visibleOutputs.add(output.trim());
            }
        }

        // ============================================
        // 📤 7. RESPONSE
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
    // 📊 GET SUBMISSIONS
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

        // 🔥 STEP 1: Get user from email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 STEP 2: Fetch submissions using userId (IMPORTANT FIX)
        List<Submission> submissions =
                submissionRepository.findByUserIdAndProblemId(user.getId(), problemId);

        // 🔥 STEP 3: Convert to DTO
        return submissions.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // ============================================
    // 🔧 DTO CONVERSION
    // ============================================

    private SubmissionDTO convertToDTO(Submission sub) {
        return new SubmissionDTO(
                sub.getId(),
                sub.getUser().getEmail(),
                sub.getProblem().getId(),   
                sub.getProblem().getTitle(),
                sub.getStatus(),
                sub.getFailedTestCase() != null ? sub.getFailedTestCase() : -1,
                sub.getLanguage()
        );
    }

    public List<SubmissionDTO> getByUserEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Submission> submissions =
                submissionRepository.findByUserId(user.getId());

        return submissions.stream()
                .map(this::convertToDTO)
                .toList();
    }
}