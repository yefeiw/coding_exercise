import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
   public int wordsTyping(String[] sentence, int rows, int cols) {
        String s  = String.join(" ",sentence)+" ";
        int start = 0;
        int len = s.length();
        for(int i = 0; i < rows; i++) {
            start += cols;
            if(s.charAt(start%len) == ' ') {
                start++;
            } else {
                while(start > 0 && s.charAt((start-1)%len)!= ' ') start--;
            }
        }
        return start / len;
    }
}

public class ScreenFitting {
    static public void main(String args[]) {
        int testIteration = 10000;
        Input  utils = new Input();
        Solution sol = new Solution();

        try {
            List <String> lines = Files.readAllLines(Paths.get("./week9/homework/screen_fitting_input.txt"), StandardCharsets.UTF_8);
            testIteration = lines.size();
            for (int i = 0; i < testIteration; i++) {
                String[] input = lines.get(i).split(" ");
                int rows = utils.generateRandomInt(testIteration)+1;
                int cols = utils.generateRandomInt(testIteration)+1;
                int output = sol.wordsTyping(input,rows,cols);
                //print
                System.out.println(lines.get(i));
                System.out.println(rows);
                System.out.println(cols);
                System.out.println(output);
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
            return;
        }

        System.out.println("Test executed without crashes, please manually verify input");

    }
}
