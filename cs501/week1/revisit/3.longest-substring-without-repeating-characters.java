public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int ret = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        int end = s.length();
        int iter = start;
        while(iter < end) {
            char c = s.charAt(iter);
            if (map.containsKey(c)) {
                start = Math.max(start,map.get(c)+1);
            }
            map.put(c,iter);
            iter++;
            ret = Math.max(ret,iter-start);
        }
        return ret;
    }
}
