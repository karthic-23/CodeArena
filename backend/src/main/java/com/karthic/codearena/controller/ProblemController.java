package com.karthic.codearena.controller;

import com.karthic.codearena.model.Problem;
import com.karthic.codearena.service.ProblemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    // CREATE PROBLEM
    @PostMapping
    public Problem createProblem(@RequestBody Problem problem) {
        return problemService.createProblem(problem);
    }

    // GET ALL PROBLEMS
    @GetMapping
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    // GET PROBLEM BY ID
    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }
}