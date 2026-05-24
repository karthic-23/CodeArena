package com.karthic.codearena.controller;

import com.karthic.codearena.service.CodeExecutor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/run")
public class RunController {

    @PostMapping
    public String runCode(
            @RequestParam String language,
            @RequestBody String code,
            @RequestParam String input
    ) {
        // Only Java for now
        return CodeExecutor.executeJava(code, input);
    }
}