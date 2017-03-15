import java.util.*;
//import Input.*;

public class Intersection {

    public static void main (String args[]) {
        int testIteration = 10000;
        int[] input1,input2;
        int[] output;
        int[] ref;

        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            input1 = testInput.generateRandomArray();
            input2 = testInput.generateRandomArray();
            //System.out.println("generated input size"+input.size());
            output = sol.intersection(input1, input2);
            ref = sol.intersection(input1,input2);
            if(output != ref) {
                System.out.println("diff found, exiting");
                return;
            }
        }
        System.out.println("test passed, ready to submit");
    }

};
class Solution{
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        int size1 = nums1.length;
        int size2 = nums2.length;
        //Need to use int properties, hence need to declare full class type.
        HashMap<Integer,Integer> dict = new HashMap<Integer, Integer>();
        //furnish with nums1 
        for (int i = 0; i < size1; i++) {
            //dict.put(nums1[i], dict.get(nums1[i]) == null ? 1 :dict.get(nums1[i])+1);
            int count = dict.containsKey(nums1[i]) ? dict.get(nums1[i]).intValue() : 0;
            dict.put(nums1[i],count+1);
        }
        //lookup with nums2
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < size2; i++) {
            int count = dict.containsKey(nums2[i]) ? dict.get(nums2[i]).intValue() : 0;

            if (count == 0) {
                //not found
                continue;
            } else {
                results.add(nums2[i]);
                dict.put(nums2[i],count-1);
            }
        }
        //return
        int[] ret = new int[results.size()];
        for(int i = 0; i < results.size(); i++) {
            ret[i] = results.get(i);
        }
        return ret;
        
    }
};