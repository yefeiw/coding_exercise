/*
 * [68] Text Justification
 *
 * https://leetcode.com/problems/text-justification
 *
 * algorithms
 * Hard (19.19%)
 * Total Accepted:    58.8K
 * Total Submissions: 306.7K
 * Testcase Example:  '[""]\n0'
 *
 * 
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * ⁠
 * 
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * 
 * 
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the
 * right.
 * 
 * 
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * 
 * 
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * 
 * 
 * 
 * Return the formatted lines as:
 * 
 * [
 * ⁠  "This    is    an",
 * ⁠  "example  of text",
 * ⁠  "justification.  "
 * ]
 * 
 * 
 * 
 * 
 * Note: Each word is guaranteed not to exceed L in length.
 * 
 * 
 * 
 * click to show corner cases.
 * 
 * Corner Cases:
 * 
 * 
 * A line other than the last line might contain only one word. What should you
 * do in this case?
 * In this case, that line should be left-justified.
 * 
 * 
 */
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret =  new ArrayList<>();
        if (words.length == 0) {
        	return ret;
        }
        int curLen = 0;
       	List<String> line = new ArrayList<>();

        for(int i = 0; i < words.length; i++) {
        	int incomingLength = line.size() == 0 ? words[i].length() : 1+words[i].length();
        	if (incomingLength + curLen > maxWidth) {
        		ret.add(outputLine(line,false,maxWidth));
        		curLen = words[i].length();
        		line = new ArrayList<>();
        		line.add(words[i]);
        	} else {
        		line.add(words[i]);
        		curLen += incomingLength;
        	}
        }
        if (line.size() > 0) {
        	//if there is something remaining
        	ret.add(outputLine(line, true,maxWidth));
        }
        return ret;
    }
    //util functions
    private String outputLine(List<String> line, boolean eol, int limit) {
    	if (eol == true || line.size() == 1) {
    		return leftJustifyLine(line, limit);
    	} else {
    		return fullJustifyLine(line, limit);
    	}
    }
    private String leftJustifyLine(List<String> line, int limit) {
    	StringBuilder sb = new StringBuilder();
    	int idx = 0;
    	for (int i = 0; i < line.size(); i++) {
    		if(i > 0) {
    			sb.append(" ");
    			idx++;
    		}
    		sb.append(line.get(i));
    		idx+=line.get(i).length();
    	}
    	for(int i = idx; i < limit; i++) {
    		sb.append(" ");
    	}
    	return sb.toString();
    }
    private String fullJustifyLine(List<String> line, int limit) {
    	StringBuilder sb = new StringBuilder();
    	int numSpacing = line.size() -1;
    	//count word size, don't worry about the rest
    	int wordLength = 0;
    	for(String str : line) {
    		wordLength += str.length();
    	}
    	int totalSpaces = limit - wordLength;
    	int minSpace = totalSpaces / numSpacing;
    	int extras = totalSpaces % numSpacing;
    	for (int i = 0; i < line.size(); i++) {
    		if (i > 0) {
    			int spaces = minSpace + ((extras > 0)? 1 : 0);
    			extras--;
    			for (int j = 0; j < spaces; j++) {
    				sb.append(" ");
    			}
    		}
    		sb.append(line.get(i));
    	}
    	return sb.toString();
    }
}
