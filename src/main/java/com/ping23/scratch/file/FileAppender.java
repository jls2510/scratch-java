package com.ping23.scratch.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import lombok.Data;

@Data
public class FileAppender {
    
    private static final Boolean APPEND_MODE = Boolean.TRUE;

    private final String filepath;
    
    private final File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    /**
     * Constructor
     * 
     * @throws IOException
     */
    public FileAppender(final String filepath) {

        if (filepath == null) {
            throw new IllegalArgumentException("filepath must not be null");
        }

        this.filepath = filepath;
        file = new File(filepath);

    }

    /**
     * Write a line to the file
     * 
     * @throws IOException
     */
    public void writeLine(final String line) throws IOException {
        
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        if (fileWriter == null || bufferedWriter == null) {
            fileWriter = new FileWriter(file, APPEND_MODE);
            bufferedWriter = new BufferedWriter(fileWriter);   
        }
        
        bufferedWriter.append(line);
        bufferedWriter.newLine();
        
        bufferedWriter.flush();
    }

}
