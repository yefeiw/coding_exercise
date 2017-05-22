import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    Map<Integer, Boolean> states;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //special cases
        if (desiredTotal <= maxChoosableInteger) return true;
        if (((1 + maxChoosableInteger) * maxChoosableInteger / 2) < desiredTotal) {
            //if the sum of all choices are still not enough for the desired total, draw(no winner)
            return false;
        }
        //initialize global variables
        states  = new HashMap<Integer, Boolean>();
        used = new boolean[maxChoosableInteger+1];
        //call helper
        return helper(desiredTotal);
    }
    boolean helper(int total) {
        //immediate returns
        if (total <= 0) {
            return false;
        }

        int key = format(used);
        if (!states.containsKey(key)) {
            //This state is not covered. Let's see what we can do
            for (int i  = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    if (false == helper(total - i)) {
                        //found a losing solution for the other player
                        states.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            //reaching here means nothing is found and thus return false;
            states.put(key, false);
            return false;
        }
        return states.get(key);
    }
    int format(boolean[] used) {
        int ret = 0;
        for (boolean b : used) {
            ret <<= 1;
            if (b) {
                ret |= 1;
            }
        }
        return ret;
    }
}

public class CanIWin {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int max = utils.generateRandomInt(20);
            int sum = utils.generateRandomInt(300);
            boolean output = sol.canIWin(max, sum);
            //print
            System.out.println(max);
            System.out.println(sum);
            System.out.println(output);


            System.out.println("Test executed without crashes, please manually verify input");

        }
    }
}
