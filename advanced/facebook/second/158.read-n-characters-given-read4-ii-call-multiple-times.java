/*
 * [158] Read N Characters Given Read4 II - Call multiple times
 *
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times
 *
 * algorithms
 * Hard (24.22%)
 * Total Accepted:    29.4K
 * Total Submissions: 121.2K
 * Testcase Example:  '""\n[read(0)]'
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
 * The read function may be called multiple times.
 * 
 */
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
	List<Character> rem = new ArrayList<>();
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        //first stage, clear the remaining bytes
        int totalRead = 0;
        int cnt = Math.min(n,rem.size());
        for(int i = 0; i < cnt;i++) {
        	buf[totalRead++] = rem.get(0);
        	rem.remove(0);
        }
        //second stage, read and copy;
       	boolean eof = false;
       	while(totalRead < n && !eof) {
       		char[] temp =  new char[4];
       		int curRead = read4(temp);
       		if (curRead < 4) {
       			eof = true;
       		}
       		for (int i  = 0; i < curRead; i++) {
       			if (totalRead < n) {
       				buf[totalRead++] = temp[i];
       			} else {
       				rem.add(temp[i]);
       			}
       		}
       	}
       	
       	return totalRead;
    }
}
