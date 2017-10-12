/*
 * [247] Strobogrammatic Number II
 *
 * https://leetcode.com/problems/strobogrammatic-number-ii
 *
 * algorithms
 * Medium (40.28%)
 * Total Accepted:    23.4K
 * Total Submissions: 58.1K
 * Testcase Example:  '2'
 *
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 */
class Solution {
  private static final String[][] pairs = {{"0","0"},{"1","1"},{"6,9"},{"8","8"},{"9","6"}};
    public List<String> findStrobogrammatic(int n) {
        return findStrobogrammatic(n,n);
    }

    //utils function
    private List<String> findStrobogrammatic(int curLen, int len) {
      if (curLen == 0) {
        return new ArrayList<String>(Arrays.asList(""));
      }
      if (curLen == 1) {
        return new ArrayList<String>(Arrays.asList("0","1","8"))  ;
      }

      List<String> next = findStrobogrammatic(curLen - 2, len);
      List<String> cur = new ArrayList<>();

      for(String s : next) {
        for (String[] p : pairs) {
          if (curLen == len && p[0] =="0") {
            continue;
          }

          curr.add(p[0]+s+p[1]);
        }
      }
      return cur;
    }
}
