public class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String vowels= "aeiouAEIOU";
        char[] buffer = s.toCharArray();
        Set<Character> dict = new HashSet<Character>();
        for (int i = 0; i < vowels.length(); i++) {
            dict.add(vowels.charAt(i));
        }
        List<Integer> positions = new ArrayList<Integer>();
        for(int i = 0; i < buffer.length;i++) {
            if (dict.contains(buffer[i])) {
                positions.add(i);
            }
        }
        int start = 0; int end = positions.size() - 1;
        while(start < end) {
            int i = positions.get(start);
            int j = positions.get(end);
            char temp = buffer[i];
            buffer[i] = buffer[j];
            buffer[j] = temp;
            start++;
            end--;
        }
        return new String(String.valueOf(buffer));
    }
}
