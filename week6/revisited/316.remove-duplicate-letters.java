/*
 * [316] Remove Duplicate Letters
 *
 * https://leetcode.com/problems/remove-duplicate-letters
 *
 * algorithms
 * Hard (29.76%)
 * Total Accepted:    33.5K
 * Total Submissions: 112.5K
 * Testcase Example:  '"bcabc"'
 *
 *
 * Given a string which contains only lowercase letters, remove duplicate
 * letters so that every letter appear once and only once. You must make sure
 * your result is the smallest in lexicographical order among all possible
 * results.
 *
 *
 *
 * Example:
 *
 *
 * Given "bcabc"
 * Return "abc"
 *
 *
 * Given "cbacdcbc"
 * Return "acdb"
 *
 *
 * Credits:Special thanks to @dietpepsi for adding this problem and creating
 * all test cases.
 */
class Solution {
    public String removeDuplicateLetters(String s) {
      if (s.length() == 0) {
        return s;
      }
      //data structures;
      int[] cnt = new int[256];
      Deque<Character> deque =  new ArrayDeque<>();
      Set<Character> set = new HashSet();
      //fill in the cnt for each character;
      for (int i = 0; i < s.length(); i++)  {
        cnt[s.charAt(i)]++;
      }
      //walk through the chracter and check if we need to add or remove the character.
      for(int i = 0; i < s.length(); i++) {
        //first do bookkeeping by decreasing the current character;
        cnt[s.charAt(i)]--;
        if (set.contains(s.charAt(i))) {
          continue;
        }
        if (deque.isEmpty() || s.charAt(i) > deque.peekLast()) {
          deque.add(s.charAt(i));
          set.add(s.charAt(i));
        } else {
          //we also need to add, but not without removal
          while(!deque.isEmpty() && s.charAt(i) < deque.peekLast() && cnt[deque.peekLast()] > 0) {
            char victim = deque.removeLast();
            set.remove(victim);
          }
          deque.add(s.charAt(i));
          set.add(s.charAt(i));
        }
      }
      //output
      StringBuilder sb = new StringBuilder();
      for (Character c : deque) {
        sb.append(c);
      }
      return sb.toString();
    }
}
