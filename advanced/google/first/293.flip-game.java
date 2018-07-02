/*
 * [293] Flip Game
 *
 * https://leetcode.com/problems/flip-game/description/
 *
 * algorithms
 * Easy (57.48%)
 * Total Accepted:    34K
 * Total Submissions: 59.2K
 * Testcase Example:  '"++++"'
 *
 * You are playing the following Flip Game with your friend: Given a string
 * that contains only these two characters: + and -, you and your friend take
 * turns to flip two consecutive "++" into "--". The game ends when a person
 * can no longer make a move and therefore the other person will be the
 * winner.
 * 
 * Write a function to compute all possible states of the string after one
 * valid move.
 * 
 * Example:
 * 
 * 
 * Input: s = "++++"
 * Output: 
 * [
 * ⁠ "--++",
 * ⁠ "+--+",
 * ⁠ "++--"
 * ]
 * 
 * 
 * Note: If there is no valid move, return an empty list [].
 * 
 */
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> ret = new ArrayList<>();
        StringBuilder sb =  new StringBuilder(s);
       for (int i = 0; i < s.length()-1; i++) {
            if (sb.substring(i,i+2).equals("++")) {
                ret.add(sb.replace(i, i+2, "--").toString());
                sb.replace(i,i+2,"++");
            }
       }
       
       return ret;
    }
}
