package com.karthic.codearena.dto;

public class SubmissionDTO {

    private Long id;
    private String userEmail;
    private String problemTitle;
    private String status;
    private int failedTestCase;
    private String language;

    public SubmissionDTO(Long id,
                         String userEmail,
                         String problemTitle,
                         String status,
                         int failedTestCase,
                         String language) {
        this.id = id;
        this.userEmail = userEmail;
        this.problemTitle = problemTitle;
        this.status = status;
        this.failedTestCase = failedTestCase;
        this.language = language;
    }

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getProblemTitle() { return problemTitle; }
    public String getStatus() { return status; }
    public int getFailedTestCase() { return failedTestCase; }
    public String getLanguage() { return language; }
}