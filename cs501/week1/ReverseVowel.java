import java.util.*;

class Solution {
 public String reverseVowels(String s) {
    if (!isValid(s)) {
        return s;
    }
    char[] chars = s.toCharArray();
    List<Integer> positions = new ArrayList<Integer>();
    for (int i = 0; i < chars.length; i++) {
        if (isVowel(chars[i])) {
            positions.add(i);
        }
    }
        //at this point we have the positions of all vowels, now let's reverse them
    int left = 0; 
    int right = positions.size() -1;
    while(left < right) {
            //swap elements of left and right;
        int leftpos = positions.get(left);
        int rightpos = positions.get(right);
        char temp = chars[leftpos];
        chars[leftpos] = chars[rightpos];
        chars[rightpos] = temp;
        left++;
        right--;
    }
    return new String(chars);

}
//solution::leetcode
public String reverseVowelsRef(String s) {
    if(s == null || s.length()==0) return s;
    String vowels = "aeiouAEIOU";
    char[] chars = s.toCharArray();
    int start = 0;
    int end = s.length()-1;
    while(start<end){
        
        while(start<end && !vowels.contains(chars[start]+"")){
            start++;
        }
        
        while(start<end && !vowels.contains(chars[end]+"")){
            end--;
        }
        
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;
        
        start++;
        end--;
    }
    return new String(chars);
}

boolean isValid(String s) {
    if (null == s || s.length() == 0) {
        return false;
    }
    return true;
}
boolean isVowel(char c) {
    if (c == 'a'||c=='e'||c=='i'||c=='o'||c=='u') {
        return true;
    }
    if (c == 'A'||c=='E'||c=='I'||c=='O'||c=='U') {
        return true;
    }
    return false;
}

}

public class ReverseVowel {
   public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10000;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        String input = testInput.generateRandomString();
        System.out.println(input);
        String output = sol.reverseVowels(input);
        String ref = sol.reverseVowelsRef(input);
        if (!output.equals(ref)) {
            System.out.println("Mismatch Occurred!");
            System.out.println(output);
            System.out.println("vs.");
            System.out.println(ref);
            break;
        }



    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
"abcabcbb"
"76faf6c3-fef0-48e5-bc5f-b1d74d1110d3"
""
"c"
"1234567890"
*/