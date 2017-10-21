/*
 * [526] Beautiful Arrangement
 *
 * https://leetcode.com/problems/beautiful-arrangement
 *
 * algorithms
 * Medium (54.79%)
 * Total Accepted:    15.1K
 * Total Submissions: 27.6K
 * Testcase Example:  '2'
 *
 * 
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement
 * as an array that is constructed by these N numbers successfully if one of
 * the following is true for the ith position (1 <= i <= N) in this array:
 * 
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 * 
 * 
 * 
 * 
 * Now given N, how many beautiful arrangements can you construct?
 * 
 * 
 * Example 1:
 * 
 * Input: 2
 * Output: 2
 * Explanation: 
 * The first beautiful arrangement is [1, 2]:
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * The second beautiful arrangement is [2, 1]:
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * 
 * 
 * 
 * Note:
 * 
 * N is a positive integer and will not exceed 15.
 * 
 * 
 */
class Solution {
    public int countArrangement(int N) {
        if (N < 3) {
        	return N;
        }
        Set<Integer> visited = new HashSet<>();
        int[] ret = new int[1];
        backtrackHelper(N,0,ret, visited);
        return ret[0];
    }

    //util functions
    private void backtrackHelper (int target, int pos, int[] ret, Set<Integer> visited) {
    	if (pos == target) {
    		ret[0]++;
    		return;
    	}
    	for (int i = 1; i <=target; i++) {
    		if (visited.contains(i)) {
    			continue;
    		}
    		if (i % (pos+1) == 0 || (pos+1) % i == 0) {
    			visited.add(i);
    			backtrackHelper(target, pos+1,ret, visited);
    			visited.remove(i);
    		}
    	}
    }
}
