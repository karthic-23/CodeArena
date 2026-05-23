package com.karthic.codearena.service;

import java.io.*;
import java.util.concurrent.*;

public class CodeExecutor {

    public static String executeJava(String code, String input) {
        try {
            // 🔹 1. Write code to file
            File file = new File("Main.java");
            FileWriter writer = new FileWriter(file);
            writer.write(code);
            writer.close();

            // 🔹 2. Compile
            Process compile = Runtime.getRuntime().exec("javac Main.java");
            compile.waitFor();

            BufferedReader compileError = new BufferedReader(
                    new InputStreamReader(compile.getErrorStream())
            );

            if (compileError.readLine() != null) {
                return "COMPILATION_ERROR";
            }

            // 🔹 3. Run program
            Process run = Runtime.getRuntime().exec("java Main");

            // 🔥 Send input
            BufferedWriter processInput = new BufferedWriter(
                    new OutputStreamWriter(run.getOutputStream())
            );

            processInput.write(input);
            processInput.newLine();
            processInput.flush();
            processInput.close();

            // 🔥 Run with timeout
            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<String> future = executor.submit(() -> {
                BufferedReader outputReader = new BufferedReader(
                        new InputStreamReader(run.getInputStream())
                );

                StringBuilder output = new StringBuilder();
                String line;

                while ((line = outputReader.readLine()) != null) {
                    output.append(line);
                }

                return output.toString();
            });

            String result;

            try {
                // 🔥 TIME LIMIT: 2 seconds
                result = future.get(2, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                run.destroy();
                executor.shutdown();
                return "TIME_LIMIT_EXCEEDED";
            }

            run.waitFor();

            // 🔥 Runtime error check
            BufferedReader runtimeError = new BufferedReader(
                    new InputStreamReader(run.getErrorStream())
            );

            if (runtimeError.readLine() != null) {
                executor.shutdown();
                return "RUNTIME_ERROR";
            }

            executor.shutdown();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}