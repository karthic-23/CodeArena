package com.karthic.codearena.dto;

public class SubmissionDTO {

    private Long id;
    private String userEmail;
    private Long problemId;
    private String problemTitle;
    private String difficulty;
    private String status;
    private int failedTestCase;
    private String language;

    public SubmissionDTO(Long id,
                         String userEmail,
                         Long problemId,
                         String problemTitle,
                         String difficulty, 
                         String status,
                         int failedTestCase,
                         String language) {
        this.id = id;
        this.userEmail = userEmail;
        this.problemId = problemId;
        this.problemTitle = problemTitle;
        this.difficulty = difficulty;
        this.status = status;
        this.failedTestCase = failedTestCase;
        this.language = language;
    }

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public Long getProblemId() {return problemId;}
    public String getProblemTitle() { return problemTitle; }
    public String getDifficulty() { return difficulty; }
    public String getStatus() { return status; }
    public int getFailedTestCase() { return failedTestCase; }
    public String getLanguage() { return language; }
}