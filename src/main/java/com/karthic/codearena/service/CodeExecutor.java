package com.karthic.codearena.service;

import java.io.*;

public class CodeExecutor {

    public static String executeJava(String code, String input) {
        try {
            // 1. Write code to file
            File file = new File("Main.java");
            FileWriter writer = new FileWriter(file);
            writer.write(code);
            writer.close();

            // 2. Compile
            Process compile = Runtime.getRuntime().exec("javac Main.java");
            compile.waitFor();

            BufferedReader compileError = new BufferedReader(
                    new InputStreamReader(compile.getErrorStream())
            );

            if (compileError.readLine() != null) {
                return "COMPILATION_ERROR";
            }

            // 3. Run program
            Process run = Runtime.getRuntime().exec("java Main");

            // 🔥 SEND INPUT TO PROGRAM
            BufferedWriter processInput = new BufferedWriter(
                    new OutputStreamWriter(run.getOutputStream())
            );

            processInput.write(input);
            processInput.newLine();
            processInput.flush();
            processInput.close();

            // 🔥 READ OUTPUT
            BufferedReader outputReader = new BufferedReader(
                    new InputStreamReader(run.getInputStream())
            );

            StringBuilder output = new StringBuilder();
            String line;

            while ((line = outputReader.readLine()) != null) {
                output.append(line);
            }

            run.waitFor();

            return output.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}