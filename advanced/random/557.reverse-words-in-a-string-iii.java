class Solution {
    public String reverseWords(String s) {
        if (s.length() < 2) {
        	return s;
        }
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length() -1;
        while(start <=end) {
        	int nextSpace = start;
        	while(nextSpace <=end && s.charAt(nextSpace) != ' ') {
        		nextSpace++;
        	}
        	for (int i = nextSpace -1; i >= start; i--) {
        		sb.append(s.charAt(i));
        	}
        	if (nextSpace <=end) {
        		sb.append(' ');
        	}
        	start = nextSpace+1;
        }
        return sb.toString();
    }
}