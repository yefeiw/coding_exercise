/*
 * [89] Gray Code
 *
 * https://leetcode.com/problems/gray-code
 *
 * algorithms
 * Medium (41.39%)
 * Total Accepted:    95.2K
 * Total Submissions: 230K
 * Testcase Example:  '0'
 *
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * 
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code
 * sequence. Sorry about that.
 */
class Solution {
    public List<Integer> grayCode(int n) {
        if (n < 0) {
        	return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        if (n == 0) return ret;
        ret.add(1);
        if (n ==  1) return ret;
        for(int t =2; t <= n; t++) {
        	List<Integer> next = new ArrayList<>();
        	for(int i = 0; i < ret.size(); i++) {
        		next.add(ret.get(i));

        	}
        	for(int i = ret.size() -1; i>=0; i--) {
        		//BUGWARNING: precedence of <<
        		next.add((1<<(t-1)) + ret.get(i));
        	}
        	ret = next;
        }
        return ret;
    }
}
