import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public boolean containsNearbyAlmostDuplicateTreeSet(int[] nums, int k, int t) {
        if ( k <= 0 || nums.length < 2) {
            return false;
        }
        TreeSet<Long> set =  new TreeSet<Long>();
        for (int i = 0 ; i < nums.length; i++) {
            Long cand = (long)nums[i];
            Long floor = set.floor(cand);
            Long ceiling = set.ceiling(cand);
            if (floor != null && Math.abs(floor - cand) <= t) return true;
            if (ceiling != null && Math.abs(ceiling - cand) <= t) return true;
            if (i >= k) set.remove((long)nums[i - k]);
            set.add((long)cand);
        }
        //reaching here means nothing is found
        return false;
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if ( k <= 0 || nums.length < 2) {
            return false;
        }
        HashMap<Long, Long> buckets = new HashMap<Long, Long>();
        for (int i = 0; i < nums.length; i++) {
            Long cand = (long) nums[i];
            Long width = (long) t + 1;
            Long id = getID(cand, width);
            if (buckets.containsKey(id)) return true;
            if (buckets.containsKey(id - 1) && Math.abs(cand - buckets.get(id - 1)) <= t) {
                return true;
            }
            if (buckets.containsKey(id + 1) && Math.abs(cand - buckets.get(id + 1)) <= t) {
                return true;
            }
            //reaching here means nothing has been found so far. We need to add
            buckets.put(id, cand);
            if (i >= k) buckets.remove(getID((long)nums[i - k], width));
        }
        //reaching here means nothing has been found
        return false;
    }
    long getID(long value, long width) {
        return (value - (long)Integer.MIN_VALUE) / width;
    }
}
//best solution: https://leetcode.com/articles/contains-duplicate-iii/#approach-2-binary-search-tree-accepted

public class ContainsDuplicate {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int iteration = 0; iteration < testIteration; iteration++) {
            int[] input = utils.generateRandomArray(testIteration);
            int k = utils.generateRandomInt(testIteration);
            int t = utils.generateRandomInt(testIteration);
            boolean output = sol.containsNearbyAlmostDuplicate(input, k, t);
            //print
            utils.printArray(input);
            System.out.println(k);
            System.out.println(t);
            System.out.println(output);
            System.out.println("+++++++++++++++++++++++");
        }

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
