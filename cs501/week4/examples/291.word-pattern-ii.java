public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        //use java annotations to handle null exception
        if (pattern.length() == 0 || str.length() == 0 ) {
            return false;//requires matching to be non empty
        }
        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> set = new HashSet<String>();
        return isMatch(str, pattern, 0,0,map,set);
    }
    boolean isMatch(String str, String pattern, int i, int j, Map<Character, String>map, Set<String> set) {
        if (i == str.length() && j == pattern.length()) {
            return true;
        } else if (i == str.length() || j == pattern.length()) {
            return false;
        }

        char curP = pattern.charAt(j);

        //case 1: current pattern exists -->check prefix
        if (map.containsKey(curP)) {
            String matchS = map.get(curP);
            if (str.startsWith(matchS,i)) {
                return false;
            }
            return isMatch(str, pattern, i+matchS.length(), j+1, map, set);
        } else {
            for (int k = i; k < str.length(); k++)  {
                String matchS = str.substring(j, k+1);
                if (!set.contains(matchS)) {
                    map.put(curP, matchS);
                    set.add(matchS);
                    if (isMatch(str, pattern, k+1, j+1, map,set)) {
                        return true;
                    }
                    map.remove(curP);
                    set.remove(matchS);
                }
            }
        }
        return false;
    }
}
