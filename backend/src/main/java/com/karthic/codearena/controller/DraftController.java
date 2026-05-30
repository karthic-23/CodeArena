package com.karthic.codearena.controller;

import com.karthic.codearena.dto.DraftRequest;
import com.karthic.codearena.model.Draft;
import com.karthic.codearena.model.Problem;
import com.karthic.codearena.model.User;
import com.karthic.codearena.repository.DraftRepository;
import com.karthic.codearena.repository.ProblemRepository;
import com.karthic.codearena.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/drafts")
public class DraftController {

    @Autowired
    private DraftRepository draftRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @PostMapping
    public Draft saveDraft(
            @RequestBody DraftRequest request,
            Authentication authentication) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        Problem problem = problemRepository
                .findById(request.getProblemId())
                .orElseThrow();

        Draft draft = draftRepository
                .findByUserIdAndProblemId(
                        user.getId(),
                        problem.getId())
                .orElse(new Draft());

        draft.setUser(user);
        draft.setProblem(problem);
        draft.setCode(request.getCode());
        draft.setLanguage(request.getLanguage());

        return draftRepository.save(draft);
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<?> getDraft(
            @PathVariable Long problemId,
            Authentication authentication) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        Draft draft = draftRepository
                .findByUserIdAndProblemId(
                        user.getId(),
                        problemId)
                .orElse(null);

        if (draft == null) {
            return ResponseEntity.ok().body("{}");
        }

        return ResponseEntity.ok(draft);
    }
}