import java.io.*;
import java.util.*;



class Solution {
	// function: next permutation
	// idea: 1. from back to front, find the first i so that str[i] < str[i+1]
	// 		 2. if not found, return the reversed string. If found, continue;
	//       3. from back to front, find the first j so that str[j] > str[i];
	//		 4. swap i and j;
	//       5. sort str.substring(i+1) so that it will now be increasing.
	//       time complexity: linear to string size;
	//       space complexity: constant in theory, choose to be linear in this specific implementation
	String nextPermutation(String input) {
		if (input.length() < 2 ){
			return input;
		}
		char[] buffer = input.toCharArray();
		int target =  -1;
		//strategy: find the first character lesser than the one immediately behind it.
		int i = buffer.length -1 ;
		for(; i > 0; i--) {
			if (buffer[i-1] < buffer[i]) {
				target = i-1;
				break;
			}
		}
		if (target == -1) {
			//if target is not updated, reverse the whole array and return.
			return new StringBuilder(input).reverse().toString();
		}
		//swap it with the first one that is larger than it.
		for (int j = buffer.length -1; j >= i; j--) {
			if (buffer[j] > buffer[target]){
				swap(buffer,j,target);
				break;
			}
		}
		//sort the substring after target;
		int start = i;
		int end = buffer.length - 1;
		while(start < end) {
			swap(buffer,start,end);
			start++;
			end--;
		}
		//return the end array;
		return new String(buffer);
	}
	private void swap(char[] buf, int i, int j) {
		//System.out.println("swapping " + buf[i] + " with " + buf[j]);
		char temp = buf[i];
		buf[i] = buf[j];
		buf[j] = temp;
	}
}


class StringNextPermutation {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] input = {
			"abcd",
			"a",
			"",
			"abdc",
			"cbda",
			"dcba"
		};
		for (String str : input) {
			System.out.println(sol.nextPermutation(str));
		}
	}
}