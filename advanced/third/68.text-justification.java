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
        if (words.length == 0) {
          return ret;
        }
        List<String> line = new ArrayList<>();
        int lineLength = 0;
        for(String word : words) {
          if (line.size() > 0 && lineLength + 1 + word.length() > maxWidth) {
            //output the current line
            ret.add(outputLine(line, maxWidth, false));
            line = new ArrayList();
            lineLength = 0;
          }
          line.add(word);
          lineLength += word.length();
          if (line.size() > 1) {
            lineLength += 1;
          }
        }
        if (!line.isEmpty()) {
          ret.add(outputLine(line,maxWidth,true));
        }
        return ret;
    }
    //util functions
    String outputLine(List<String> line, int maxWidth, boolean isEoF) {
      if (isEoF || line.size() == 1) {
        return outputLeftJustified(line, maxWidth);
      } else {
        return outputBothJustified(line, maxWidth);
      }
    }

    String outputLeftJustified(List<String> line, int maxWidth) {
      StringBuilder sb = new StringBuilder();
      int lineLength = 0;
      for (int i  = 0; i < line.size(); i++) {
        if (i > 0) {
          sb.append(" ");
          lineLength++;
        }
        sb.append(line.get(i));
        lineLength += line.get(i).length();
      }
      int remainder = maxWidth - lineLength;
      for (int i = 0; i < remainder; i++) {
        sb.append(" ");
      }
      return sb.toString();
    }

    String outputBothJustified(List<String> line, int maxWidth) {
      //pseudo-contants
      int m = line.size()-1;
      int lineLength = 0;
      StringBuilder sb = new StringBuilder();
      for (String word : line) {
        lineLength += word.length();
      }
      int minSpaces = (maxWidth - lineLength) / m;
      int extraSpaces = (maxWidth - lineLength) % m;
      for (int i  = 0; i <=m ; i++) {
        int numSpaces = (i <= extraSpaces) ? minSpaces + 1 : minSpaces;
        if (i > 0) {
          for (int j = 0; j < numSpaces; j++) {
            sb.append (" ");
          }
        }
        sb.append(line.get(i));
      }
      return sb.toString();
    }
}
