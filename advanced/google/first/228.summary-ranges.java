/*
 * [228] Summary Ranges
 *
 * https://leetcode.com/problems/summary-ranges/description/
 *
 * algorithms
 * Medium (32.20%)
 * Total Accepted:    98.7K
 * Total Submissions: 306.4K
 * Testcase Example:  '[0,1,2,4,5,7]'
 *
 * 
 * Given a sorted integer array without duplicates, return the summary of its
 * ranges.
 * 
 * Example 1:
 * 
 * Input: [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * 
 * 
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 */
class Solution {
    public List<String> summaryRanges(int[] nums) {
       List<String> ret = new ArrayList();
       if (nums.length == 1) {
           ret.add(nums[0]+"");
           return ret;
       } 
       int prev = 0;
       for (int i = 0; i < nums.length;) {
           while(i < nums.length - 1 && nums[i+1] - nums[i] == 1) i++;
           if (i == prev) {
               ret.add(nums[prev]+"");
           } else {
               ret.add(nums[prev]+"->"+nums[i]);
           }
           i++;
           prev = i;
       }

       return ret;
    }
}
