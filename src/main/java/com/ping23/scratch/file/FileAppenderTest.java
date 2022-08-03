package com.ping23.scratch.file;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ping23.scratch.util.FileUtilities;

public class FileAppenderTest {

    private static final SimpleDateFormat REPORT_EXT_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    // get some content
    private static final String[] ALICE = FileUtilities.getFirstFiveLinesOfAliceInWonderland();

    public static void main(String[] args) {

        try {
            String timestamp = REPORT_EXT_FORMAT.format(new Date());
            final FileAppender report = new FileAppender("report/FileAppenderTestReport." + timestamp + ".txt");

                for (String line : ALICE) {
                    report.writeLine(line);
                }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
