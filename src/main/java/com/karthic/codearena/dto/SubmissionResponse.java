package com.karthic.codearena.dto;

public class SubmissionResponse{

    private String status;
    private int passedTestCases;
    private int totalTestCases;
    private String errorType;

    public SubmissionResponse(String status, int passedTestCases, int totalTestCases, String errorType) {
        this.status = status;
        this.passedTestCases = passedTestCases;
        this.totalTestCases = totalTestCases;
        this.errorType = errorType;
    }

    // Getters
    public String getStatus() { return status; }
    public int getPassedTestCases() { return passedTestCases; }
    public int getTotalTestCases() { return totalTestCases; }
    public String getErrorType() { return errorType; }
}