package com.karthic.codearena.dto;

import java.util.List;

public class SubmissionResponse {

    private String status;
    private int passedTestCases;
    private int totalTestCases;
    private String errorType;

    // 🔥 NEW FIELDS
    private int failedTestCase;
    private List<String> visibleOutputs;

    public SubmissionResponse(String status,
                              int passedTestCases,
                              int totalTestCases,
                              String errorType,
                              int failedTestCase,
                              List<String> visibleOutputs) {

        this.status = status;
        this.passedTestCases = passedTestCases;
        this.totalTestCases = totalTestCases;
        this.errorType = errorType;
        this.failedTestCase = failedTestCase;
        this.visibleOutputs = visibleOutputs;
    }

    // Getters
    public String getStatus() { return status; }
    public int getPassedTestCases() { return passedTestCases; }
    public int getTotalTestCases() { return totalTestCases; }
    public String getErrorType() { return errorType; }
    public int getFailedTestCase() { return failedTestCase; }
    public List<String> getVisibleOutputs() { return visibleOutputs; }
}