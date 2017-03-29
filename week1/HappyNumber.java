import java.util.*;

class Solution {
    public boolean isHappy (int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        while(n != 1) {
            if (set.contains(n)) {
                //now that there is a loop, it will not go back to 1.
                return false;
            }
            set.add(n);
            n = calculateNext(n);
        }
        //jumping out means it is now 1.
        return true;

    }
    
    int calculateNext(int n) {
        int ret = 0;
        while (n != 0) {
            int digit = n % 10;
            ret += (digit * digit);
            n /= 10;
        }
        return ret;
    }


    //Solution: Jiuzhang
     private int getNextHappy(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
    
    public boolean ref(int n) {
        HashSet<Integer> hash = new HashSet<Integer>();
        while (n != 1) {
            if (hash.contains(n)) {
                return false;
            }
            hash.add(n);
            n = getNextHappy(n);
        }
        return true;
    }
}

public class HappyNumber{
 public static void main (String args[]) {
        int testIteration = 10000;
        //small test set for manual verification
    // int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input = testInput.generateRandomInt();
        if (sol.isHappy(input) != sol.ref(input)) {
            System.out.println ("Error: Mismatch found");
            System.out.println(sol.isHappy(input));
            System.out.println("vs.");
            System.out.println(sol.ref(input));
            System.exit(1);
        }
    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
1
2
3
4
10000
123345
*/