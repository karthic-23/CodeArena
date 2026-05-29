package com.karthic.codearena.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(columnDefinition = "TEXT")
    private String code;

    private String language;

    private String status;

    private LocalDateTime submittedAt;

    // 🔥 NEW FIELDS
    private Integer failedTestCase;

    @Column(columnDefinition = "TEXT")
    private String expectedOutput;

    @Column(columnDefinition = "TEXT")
    private String actualOutput;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    @PrePersist
    protected void onSubmit() {
        this.submittedAt = LocalDateTime.now();
    }

    public Submission() {}

    public Submission(User user, Problem problem, String code, String language, String status) {
        this.user = user;
        this.problem = problem;
        this.code = code;
        this.language = language;
        this.status = status;
    }

    // 🔹 GETTERS
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Problem getProblem() { return problem; }
    public String getCode() { return code; }
    public String getLanguage() { return language; }
    public String getStatus() { return status; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }

    public Integer getFailedTestCase() { return failedTestCase; }
    public String getExpectedOutput() { return expectedOutput; }
    public String getActualOutput() { return actualOutput; }
    public String getErrorMessage() { return errorMessage; }

    // 🔹 SETTERS
    public void setUser(User user) { this.user = user; }
    public void setProblem(Problem problem) { this.problem = problem; }
    public void setCode(String code) { this.code = code; }
    public void setLanguage(String language) { this.language = language; }
    public void setStatus(String status) { this.status = status; }

    public void setFailedTestCase(Integer failedTestCase) { this.failedTestCase = failedTestCase; }
    public void setExpectedOutput(String expectedOutput) { this.expectedOutput = expectedOutput; }
    public void setActualOutput(String actualOutput) { this.actualOutput = actualOutput; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}