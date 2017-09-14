/*
 * [157] Read N Characters Given Read4
 *
 * https://leetcode.com/problems/read-n-characters-given-read4
 *
 * algorithms
 * Easy (28.95%)
 * Total Accepted:    35.3K
 * Total Submissions: 121.9K
 * Testcase Example:  '""\n0'
 *
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * 
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * 
 * 
 * 
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * 
 * 
 * 
 * Note:
 * The read function will only be called once for each test case.
 * 
 */
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int bytesRead  = 0;
        boolean eof = false;
        while(bytesRead < n && !eof) {
        	char[] temp = new char[4];
        	int curRead = read4(temp);
        	if (curRead < 4) {
        		eof = true;
        	}
        	int count = Math.min(n - bytesRead, curRead);
        	for(int i = 0; i < count; i++) {
        		buf[bytesRead++] = temp[i];
        	}
        	if (bytesRead > n) {
        		bytesRead = n;	
        	}
        }
        return bytesRead;
    }
}
