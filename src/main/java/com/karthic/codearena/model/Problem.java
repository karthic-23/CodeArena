package com.karthic.codearena.model;

import jakarta.persistence.*;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String difficulty; // EASY, MEDIUM, HARD

    @Column(columnDefinition = "TEXT")
    private String inputExample;

    @Column(columnDefinition = "TEXT")
    private String outputExample;

    public Problem() {}

    public Problem(String title, String description, String difficulty,
                   String inputExample, String outputExample) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getInputExample() { return inputExample; }
    public void setInputExample(String inputExample) { this.inputExample = inputExample; }

    public String getOutputExample() { return outputExample; }
    public void setOutputExample(String outputExample) { this.outputExample = outputExample; }
}