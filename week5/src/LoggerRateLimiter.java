import java.util.*;
class Logger {
    ////////////////// Internal State Variables///////////////////
    private final HashMap<Integer> dict = new HashMap<Integer>();
    

    /** Initialize your data structure here. */
    public Logger() {
        
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!dict.containsKey(message)) {
            //message never printed, doomed true;
            dict.put(message, timestamp);
            return true;
        } else {
            int lastStamp  = dict.get(message);
            int diff = timestamp - lastStamp;
            if (diff <10) {
                return false;
            } else {
                dict.put(message, timestamp);
                return true;
            }
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

public class DesignSnakeGame {
    public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    int width = testInput.generateRandomInt()+1;
    int height = testInput.generateRandomInt()+1;
    int [][]food = testInput.generateRandomMatrix(testIteration, Math.min(width,height));
    SnakeGame game = new SnakeGame(width, height, food);
    String[] move = {"U","L","R","D"};
    for (int i = 0; i < testIteration; i++) {
       int m = Math.abs(testInput.generateRandomInt()) % 4;
       String curMove = move[m];
       int ret = game.move(curMove);
       System.out.println("CurMove is " +curMove + " and the score is " + ret);
    }
    System.out.println("Test executed without crashes, please manually verify input");
 }
}