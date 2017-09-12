/*
 * [49] Group Anagrams
 *
 * https://leetcode.com/problems/group-anagrams
 *
 * algorithms
 * Medium (35.18%)
 * Total Accepted:    149.8K
 * Total Submissions: 425.7K
 * Testcase Example:  '["eat","tea","tan","ate","nat","bat"]'
 *
 * Given an array of strings, group anagrams together.
 * 
 * 
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * Return:
 * 
 * [
 * ⁠ ["ate", "eat","tea"],
 * ⁠ ["nat","tan"],
 * ⁠ ["bat"]
 * ]
 * 
 * Note: All inputs will be in lower-case.
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        if (strs.length == 0) return ret;
        for(String str  : strs) {
        	String key = getKey(str);
        	if(!map.containsKey(key)) {
        		map.put(key, new ArrayList<>());
        	}
        	List<String> list = map.get(key);
        	list.add(str);
        }
        for(List<String> entry : map.values()) {
        	ret.add(entry);
        }
        return ret;
    }
    private String getKey(String input) {
    	char[] array = input.toCharArray();
    	Arrays.sort(array);
    	return String.valueOf(array);
    }
}
