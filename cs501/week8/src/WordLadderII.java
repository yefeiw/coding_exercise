import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
// Normal BFS will do the job. No need to think of harder algorithms.
// Note on the logic of string removal. StringBuffer will be very dangerous.
// Note especially when borrowing help from the solution. Remember to check the code flow before hitting submit button!

class Solution {
      public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<String>();
        for (String word : wordList) {
            dict.add(word);
        }
        //permanent map of words.
        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        //temporary maps consisting of the current search results.
        Map<String, List<List<String>>> tmpMap = new HashMap<String, List<List<String>>>();
        ArrayDeque<String> queue =  new ArrayDeque<String>();
        //furnish the seed structure of BFS
        queue.add(beginWord);
        List<List<String>> starter = new ArrayList<List<String>>();
        List<String> entry = new ArrayList<String>();
        entry.add(beginWord);
        starter.add(entry);
        map.put(beginWord, starter);
        //conduct search
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int t = 0; t < size; t++) {
                String front = queue.remove();
                int len = front.length();
                for (int i = 0; i < len; i++) {
                    StringBuffer sb = new StringBuffer(front);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        String key = sb.toString();
                        if ((!dict.contains(key)) || map.containsKey(key)) {
                            //don't search if it's already visited or is not found in dict.
                            continue;
                        }
                        List<List<String>> newList = tmpMap.get(key);
                        if (null == newList){ 
                            //Only add to search if the entry is not found in tmpMap
                            newList = new ArrayList<List<String>>();
                            queue.add(key);
                            tmpMap.put(key,newList);
                        }


                        //get the previous list of strings
                        List<List<String>> pre = map.get(front);
                        for (List<String> list : pre) {
                            List<String> copy =  new ArrayList<String>(list);
                            copy.add(key);
                            newList.add(copy);
                        }
                    }
                }
            }
            if (tmpMap.containsKey(endWord)) {
                //here it means the word has been found. We can return it now;
                return tmpMap.get(endWord);
            }
            map.putAll(tmpMap);
        }
        //reaching here means nothing is found
        return new ArrayList<List<String>>();

    }
}

public class WordLadderII {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        // Generating word dictionary has been a little hard, since we will test it on leetcode.

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
