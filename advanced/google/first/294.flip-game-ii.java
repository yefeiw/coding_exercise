/*
 * [294] Flip Game II
 *
 * https://leetcode.com/problems/flip-game-ii/description/
 *
 * algorithms
 * Medium (47.13%)
 * Total Accepted:    35.4K
 * Total Submissions: 75K
 * Testcase Example:  '"++++"'
 *
 * You are playing the following Flip Game with your friend: Given a string
 * that contains only these two characters: + and -, you and your friend take
 * turns to flip two consecutive "++" into "--". The game ends when a person
 * can no longer make a move and therefore the other person will be the
 * winner.
 * 
 * Write a function to determine if the starting player can guarantee a win.
 * 
 * Example:
 * 
 * 
 * Input: s = "++++"
 * Output: true 
 * Explanation: The starting player can guarantee a win by flipping the middle
 * "++" to become "+--+".
 * 
 * 
 * Follow up:
 * Derive your algorithm's runtime complexity.
 * 
 */
class Solution {
    public boolean canWin(String s) {
        if (s.length() < 2) {
            return false;
        }
        if (cache.containsKey(s)){
            return cache.get(s);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.substring(i, i + 2).equals("++")) {
                String cand = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(cand)) {
                    cache.put(cand,false);
                    return true;
                } else {
                    cache.put(cand, true);
                }
            }
        }
        return false;
    }
    Map<String, Boolean> cache = new HashMap();
}
