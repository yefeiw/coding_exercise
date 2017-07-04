package com.yefeiw;

import javax.management.Query;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yefeiw on 7/3/17.
 */
public class QueryHandler {
    private String filename;
    BufferedReader buffers;
    public QueryHandler(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            buffers = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLine() {
        String line = "";
        try {
            while (line.trim().length() == 0) {
                line = buffers.readLine();
                if (line == null) {
                    System.out.println("EOL reached");
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line.trim();
    }

}
