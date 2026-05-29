package com.karthic.codearena.service;

import com.karthic.codearena.model.Problem;
import com.karthic.codearena.repository.ProblemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    // CREATE
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    // GET ALL
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    // GET BY ID
    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
    }
}