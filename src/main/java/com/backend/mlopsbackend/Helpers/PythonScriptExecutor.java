package com.backend.mlopsbackend.Helpers;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PythonScriptExecutor {
    // Copies the content of the file from resources to a temporary file
    // (Python interpreter cannot execute scripts directly from within a JAR file)
    public File ExtractFileFromResources(String resourcePath) throws Exception {
        Resource resource = new ClassPathResource(resourcePath);
        InputStream inputStream = resource.getInputStream();
        // Create a temporary file which will be deleted on exit
        File tempFile = Files.createTempFile("script-",  getFileExtension(resourcePath)).toFile();
        tempFile.deleteOnExit();
        try (OutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    // Executes the python script passed by parameter
    public Pair<Integer,String> ExecutePythonScript(File scriptFile, File dataFile, String dataPath, boolean needToGetReturnString, boolean needsDatabase ) throws Exception {
        String tempDir = System.getProperty("java.io.tmpdir");
        List<String> commands = new ArrayList<>();
        commands.add("python3");
        commands.add(scriptFile.getAbsolutePath());
        commands.add(tempDir);
        if (dataFile != null){
            commands.add(dataFile.getAbsolutePath());
        }
        if (dataPath != null){
            commands.add(dataPath);
        }
        if (needsDatabase){
            String database_name = System.getenv("DATABASE_NAME");
            String database_username = System.getenv("DATABASE_USERNAME");
            String database_password = System.getenv("DATABASE_PASSWORD");
            String database_host = System.getenv("DATABASE_HOST");
            commands.add(database_name);
            commands.add(database_username);
            commands.add(database_password);
            commands.add(database_host);
        }

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            if (needToGetReturnString) output.append(line);
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            // Handle the case where the script execution failed
            System.err.println("Script execution failed with exit code " + exitCode + " and output " +  output);
        }
        return new Pair<>(exitCode, output.toString());
    }
}
