import java.util.*;

class Solution {
   public String largestNumber(int[] nums) {
    if (nums == null || nums.length == 0 ) {
        return "";
    }
    String[] buffer = new String[nums.length];
    for (int i = 0; i < buffer.length; i++) {
        buffer[i] = Integer.toString(nums[i]);
    }
    Arrays.sort(buffer,new MyComparator());
    StringBuffer sb = new StringBuffer();
    for (String s : buffer) {
        sb.append(s);
    }
    //special notice: remove zeros in the head.
    for(int i =0;sb.length() > 1; i++) {
        if (sb.charAt(0) != '0') {
            break;
        }
        sb.deleteCharAt(0);
    }
    return sb.toString();
   } 
}
class MyComparator implements Comparator<String> {
    public int compare(String a, String b) {
        return ((b+a).compareTo(a+b));
    }
}
public class LargestNumber{
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            int[] input = testInput.generateRandomArray();
            String output = sol.largestNumber(input);
            System.out.printf(" output is %s\n",output);
        }
        System.out.println("Test Executed, please manually check input");
    }
}
/* test cases used at leetcode:
[]
[0]
[0,1,2,3,4,5,6,7]
[0,1]
[0,1,2]
*/