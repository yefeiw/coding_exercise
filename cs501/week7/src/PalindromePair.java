import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret =  new ArrayList<List<Integer>>();
        if (words == null || words.length == 0) {
            return ret;
        }
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        //put all words in to dict
        for (int i = 0; i < words.length; i++) {
            dict.put(words[i], i);
        }
        //search through all words
        for (int i = 0; i < words.length; i++) {
            String word =  words[i];
            int len = word.length();
            for (int length = 0; length <= len; length++) {
                String former = word.substring(0, length);
                String latter = word.substring(length);
                if (isPalindrome(former)) {
                    String index = new StringBuilder(latter).reverse().toString();
                    if (dict.containsKey(index) && dict.get(index) != i) {
                        //former is palindrome latter has reverse -> new string plus this string
                        List<Integer> newAns = new ArrayList<Integer>();
                        newAns.add(dict.get(index));
                        newAns.add(i);
                        ret.add(newAns);
                    }
                }
                if (isPalindrome(latter)) {
                    String index = new StringBuilder(former).reverse().toString();
                    //If latter is empty, it is equivalent to former being empty, this is already covered
                    if (dict.containsKey(index) && dict.get(index) != i && latter.length() > 0) {
                        List<Integer> newAns = new ArrayList<Integer>();
                        newAns.add(i);
                        newAns.add(dict.get(index));
                        ret.add(newAns);
                    }
                }
            }
        }
        return ret;
    }
    boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while(start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}

public class PalindromePair {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int iteration = 0; iteration < testIteration; iteration++) {
           String[] input = new String[testIteration];
           for (int i = 0; i < testIteration; i++) {
            input[i] = utils.generateRandomString(testIteration);
           }
            List<List<Integer>> output = sol.palindromePairs(input);
            //print
            System.out.println("============");
        }

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
