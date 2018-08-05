import java.util.List;

/*
 * [315] Count of Smaller Numbers After Self
 *
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 *
 * algorithms
 * Hard (35.27%)
 * Total Accepted:    52.7K
 * Total Submissions: 149.6K
 * Testcase Example:  '[5,2,6,1]'
 *
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * 
 * Input: [5,2,6,1]
 * Output: [2,1,1,0] 
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * 
 * 
 */
class Solution {
    /**
     * This solution uses BIT update and find.
     * Need to read more and understand why BIT will help in this case
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums.length == 0) {
            return ret;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int[] nums2 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums2[i] = nums[i] - min + 1;
            max = Math.max(nums2[i],max);
        }
        int[] tree = new int[max + 1];
        for (int i = nums2.length-1; i >= 0; i--) {
            ret.add(0,get(nums2[i]-1,tree));
            update(nums2[i],tree);
        }

        return ret;
    }
    private int get (int i, int[] tree) {
        int num = 0;
        while(i > 0) {
            num += tree[i];
            i -= (i & (-i));
        }
        return num;
    }

    private void update(int i, int[] tree) {
        while (i < tree.length) {
            tree[i]++;
            i += (i & (-i));
        }
    }
}
