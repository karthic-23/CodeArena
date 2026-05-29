package com.karthic.codearena.service;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class CodeExecutor {

    public static String executeJava(String code, String input) {

        String result = "";

        // 🔥 Unique ID (timestamp)
        long submissionId = System.currentTimeMillis();

        String folderName = "temp/submission_" + submissionId;
        String className = "Main_" + submissionId;
        String javaFileName = className + ".java";

        try {
            // ================================
            // 📁 1. Create directory
            // ================================
            Path dirPath = Paths.get(folderName);
            Files.createDirectories(dirPath);

            // ================================
            // ✏️ 2. Modify class name in code
            // ================================
            String modifiedCode = code.replaceAll("class\\s+Main", "class " + className);

            // ================================
            // 📄 3. Write Java file
            // ================================
            Path javaFilePath = dirPath.resolve(javaFileName);
            Files.write(javaFilePath, modifiedCode.getBytes());

            // ================================
            // ⚙️ 4. Compile
            // ================================
            ProcessBuilder compileProcess = new ProcessBuilder("javac", javaFileName);
            compileProcess.directory(new File(folderName));
            Process compile = compileProcess.start();

            boolean compiled = compile.waitFor(5, TimeUnit.SECONDS);

            if (!compiled || compile.exitValue() != 0) {
                return "COMPILATION_ERROR";
            }

            // ================================
            // ▶️ 5. Run program
            // ================================
            ProcessBuilder runProcess = new ProcessBuilder("java", className);
            runProcess.directory(new File(folderName));
            Process run = runProcess.start();

            // 🔹 Send input
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(run.getOutputStream()));
            writer.write(input);
            writer.newLine();
            writer.flush();
            writer.close();

            // 🔹 Read output
            BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            // 🔹 Timeout control
            boolean finished = run.waitFor(3, TimeUnit.SECONDS);

            if (!finished) {
                run.destroyForcibly();
                return "TIME_LIMIT_EXCEEDED";
            }

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            result = output.toString().trim();

        } catch (Exception e) {
            return "RUNTIME_ERROR";
        } finally {
            // ================================
            // 🧹 6. Cleanup (delete folder)
            // ================================
            try {
                deleteDirectory(new File(folderName));
            } catch (Exception ignored) {}
        }

        return result;
    }

    // 🔥 Helper: delete directory recursively
    private static void deleteDirectory(File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                deleteDirectory(subFile);
            }
        }
        file.delete();
    }
}