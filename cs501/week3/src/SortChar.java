import java.util.*;

class Entry {
    public char c;
    public int freq;
    public Entry() {
        c = 0;
        freq  = 0;
    }
    public Entry(char c, int freq) {
        this.c  = c;
        this.freq = freq;
    }
}
class MyComparator implements Comparator<Entry> {
    public int compare(Entry a, Entry b) {
        return b.freq - a. freq;
    }
}
class Solution {
    public String frequencySort(String s) {
        if(null == s || s.length() < 1)  {
            return "";
        }
        Entry[] entries = new Entry[256];
        for (int i = 0; i < 256; i++) {
            entries[i] = new Entry((char)i,0);
        } 
        for (int i = 0; i < s.length(); i++) {
            char c =  s.charAt(i);
            entries[c].freq++;
        }
        Arrays.sort(entries, new MyComparator());
        StringBuffer sb = new StringBuffer();
        for (Entry e : entries) {
            for (int i = 0; i < e.freq; i++) {
                sb.append(e.c);
            }
        }
        return sb.toString();
    }
}
public class SortChar {
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
           String input  = testInput.generateRandomString();
           String output = sol.frequencySort(input);
           System.out.printf("input: %s, output: %s\n",input,output);
        }
        System.out.println("Test Executed, please manually check input");
    }
}
/* test cases used at leetcode:
[]
[0]
[0,1,2,3,4,5,6,7]
[0,1]
[0,1,2]
*/