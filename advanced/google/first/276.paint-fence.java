/*
 * [276] Paint Fence
 *
 * https://leetcode.com/problems/paint-fence/description/
 *
 * algorithms
 * Easy (35.06%)
 * Total Accepted:    34K
 * Total Submissions: 97.1K
 * Testcase Example:  '3\n2'
 *
 * There is a fence with n posts, each post can be painted with one of the k
 * colors.
 * 
 * You have to paint all the posts such that no more than two adjacent fence
 * posts have the same color.
 * 
 * Return the total number of ways you can paint the fence.
 * 
 * Note:
 * n and k are non-negative integers.
 * 
 * Example:
 * 
 * 
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways
 * are:
 * 
 * post1  post2  post3      
 * ⁠-----      -----  -----  -----       
 * ⁠  1         c1     c1     c2 
 * 2         c1     c2     c1 
 * 3         c1     c2     c2 
 * 4         c2     c1     c1  
 * ⁠  5         c2     c1     c2
 * 6         c2     c2     c1
 * 
 * 
 */
// Idea source:  https://blog.csdn.net/qq508618087/article/details/50863010
class Solution {
    public int numWays(int n, int k) {
       if (n == 0 || k == 0 || (k == 1 && n > 2)) {
           return 0;
       } 
       int same = 0, diff = k, total = k;
       for (int i = 2; i <= n; i++) {
        //if color[n] == color[n-1], then this round there is only one choice, total choice is how many different ways there is between n-1 and n-2.
        //if color[n] != color[n-1], then this round we have (k-1) * (total choices previously)   
            same = diff;
            diff = (k-1) * total;
            total = same + diff;
       }

       return total;
    }
}
