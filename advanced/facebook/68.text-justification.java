/*
 * [68] Text Justification
 *
 * https://leetcode.com/problems/text-justification
 *
 * algorithms
 * Hard (19.08%)
 * Total Accepted:    57.7K
 * Total Submissions: 302.3K
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
		List<String> lines = new ArrayList<>();
		List<String> curLine = new ArrayList<>();
		int lineLength = 0;
        //tell whether the current word fits in the current line.
        //if not clear the current line.
        //and anyways output.
		for(String word : words) {
			int minLength = curLine.isEmpty() ? word.length() : 1 + word.length();
			if (minLength + lineLength > maxWidth) {
				lines.add(format(curLine,maxWidth,false));
				curLine = new ArrayList<>();
				lineLength = 0;
        		//set the current word to the first word.
				minLength--;
			}
			curLine.add(word);
			lineLength += minLength;
		}
        //Last line might exist here
		if (!curLine.isEmpty()) {
			lines.add(format(curLine,maxWidth,true));
		}
		return lines;
	}
    //util functions
	String format(List<String> input,int limit,boolean isEof) {
		StringBuilder sb = new StringBuilder();
		int curSize = 0;
		int wordSize = 0;
		for (String s : input) {
			curSize += s.length() + 1;
			wordSize += s.length();
		}
		if (curSize > 0) curSize -= 1;

		if (input.size() == 1|| isEof) {
			for (int i = 0; i < input.size(); i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append(input.get(i));

			}
			for (int k = 0;k < limit - curSize; k++) {
				System.out.println(limit+","+curSize);
				sb.append(' ');
			}
			return sb.toString();
		} else {
			int numSpaces = limit - wordSize;
			int share = numSpaces / (input.size()-1);
			int remainder = numSpaces % (input.size()-1);
			System.out.printf("share is %d, remainder is %d\n",share,remainder);
			for(int i = 0; i < input.size(); i++) {
				if (i > 0) {
					int spaces = share + ((remainder  >  0) ? 1 : 0);
					for ( int j = 0; j < spaces; j++) {
						sb.append(' ');
					}
					remainder--;
				}
				sb.append(input.get(i));
			}
			return sb.toString();
		}
	}

}
