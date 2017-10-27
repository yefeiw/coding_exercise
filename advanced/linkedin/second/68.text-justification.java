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
        List<String> ret = new ArrayList<>();

        if (words.length == 0) return ret;

        List<String> buffer = new ArrayList<>();
        int curLength = 0;
        for(int i = 0; i < words.length; i++) {
            int nextLength = words[i].length();
            if (buffer.size() > 0) nextLength++;
            if (nextLength + curLength > maxWidth) {
                ret.add(processLine(buffer, maxWidth,false));
                buffer = new ArrayList<>();
                curLength = 0;
                nextLength--;
            }
            buffer.add(words[i]);
            curLength += nextLength;
        }
        //Here the last line must contain at least one word
        ret.add(processLine(buffer,maxWidth,true));

        return ret;
    }

    //util functions
    String processLine(List<String> buffer, int maxWidth, boolean isEoF) {
        if (isEoF || buffer.size() == 1){
            return processLeftJustified(buffer,maxWidth);
        } else {
            return processBothJustified(buffer,maxWidth);
        }
    }

    String processLeftJustified(List<String> buffer, int maxWidth) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < buffer.size(); i++) {
            if(i > 0) sb.append(" ");
            sb.append(buffer.get(i));
        }
        int curLength = sb.length();
        for(int i = 0; i < maxWidth - curLength; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    String processBothJustified(List<String> buffer, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int curLength = 0;

        int numSpaces = buffer.size() - 1;
        int charLength = 0;
        for(String s : buffer) {
            charLength += s.length();
        }

        int spaceLength = maxWidth - charLength;
        int minSpace = spaceLength / numSpaces;
        int extraSpace = spaceLength % numSpaces;

        for (int i = 0; i < buffer.size(); i++) {
            if (i > 0) {
                int curSpaces = minSpace;
                if (i  <= extraSpace) {
                    curSpaces++;
                }
                for(int j = 0; j < curSpaces; j++) {
                    sb.append(" ");
                }
            }
            sb.append(buffer.get(i));
        }

        return sb.toString();
    }
}
