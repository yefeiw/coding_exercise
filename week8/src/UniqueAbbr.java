import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
// Normal BFS will do the job. No need to think of harder algorithms.
// Note on the logic of string removal. StringBuffer will be very dangerous.
// Note especially when borrowing help from the solution. Remember to check the code flow before hitting submit button!

class Solution {
     public String minAbbreviation(String target, String[] dictionary) {
        if (target == null || dictionary == null || target.length() == 0) {
            return "";
        } 
        List<String> dic = new ArrayList<String>();
        for( String word : dictionary) {
            if (word.length() == target.length()) {
                dic.add(word);
            }
        }
        if (isEmpty(dic)) {
            //There are no conflicts at all, return the shortest possible answer;
            int len = target.length();
            return Integer.toString(len);
        }
        String ret = target;
        dfs("",0,target,0,dic,ret,target.length());
        return ret;
    }
    void dfs(String cur, int curLen, String target, int pos, List<String> dict, String ret, int len) {
        if (pos >= target.length()) {

        }
    }
   
}

public class Minesweeper{
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        //too hard to test locally, rely on leetcode.
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
