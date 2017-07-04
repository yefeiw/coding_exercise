package com.yefeiw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        // write your code here
        final String INPUT_FILENAME = "rawQuery3.txt";

        Logger logger = Logger.getLogger("crawler Test");
        QueryHandler queryHandler = new QueryHandler(INPUT_FILENAME);
        Crawler crawler = new Crawler();
        ObjectMapper objectMapper = new ObjectMapper();
        crawler.initProxy();
        List<Ad> results = new ArrayList<Ad>(1000);
        //counter of output files and input number
        int fileNum = 0;
        String starter = "";
        try {
            while (starter != null) {
                //read next valid input line
                String input = queryHandler.getLine();
                if(input.trim().length() == 0) {
                    continue;
                }
                if (input == null) {
                    throw (new EOFException("EOF reached"));
                }
                logger.info("input query is "+ input);
                String[] words = input.split(",");
                //let the crawler process the ads
                crawler.getAmazonProds(words,results,0);
                logger.info("total ads get from this page is "+results.size());
                if(results.size() == 0) {
                    logger.warning("Input empty for keywords "+words[0].trim());
                    throw(new EOFException("input empty"));
                }
                //write output to files
                try {
                    objectMapper.writeValue(new File("output_" + fileNum++ + ".json"), results);
                } catch (Exception e) {
                    logger.warning("File output error");
                    e.printStackTrace();
                }
                results = new ArrayList<Ad>(1000);
            }
        }catch (Exception e) {
            e.printStackTrace();
    }
    }
}
