package com.karthic.codearena.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 MANY submissions → ONE user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 🔗 MANY submissions → ONE problem
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(columnDefinition = "TEXT")
    private String code;

    private String language; // JAVA, PYTHON, etc.

    private String status; // ACCEPTED, WRONG_ANSWER

    private LocalDateTime submittedAt;

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

    // Getters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Problem getProblem() { return problem; }
    public String getCode() { return code; }
    public String getLanguage() { return language; }
    public String getStatus() { return status; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }

    // Setters
    public void setUser(User user) { this.user = user; }
    public void setProblem(Problem problem) { this.problem = problem; }
    public void setCode(String code) { this.code = code; }
    public void setLanguage(String language) { this.language = language; }
    public void setStatus(String status) { this.status = status; }
}