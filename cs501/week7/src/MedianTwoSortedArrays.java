import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
   public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int left = (len1 + len2 + 1)/2;
        int right = (len1 + len2+2) / 2;
        return(getKth(nums1,0,nums2,0,left)+getKth(nums1,0,nums2,0,right)) / 2.0;
    }
    int getKth(int[] l, int startL, int[] r, int startR, int k) {
        if (startL > l.length -1) return r[startR + k -1];
        if (startR > r.length -1) return l[startL+ k -1];
        if(k == 1) return Math.min(l[startL],r[startR]);

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (startL + k/2 - 1 < l.length) aMid = l[startL+k/2 -1];
        if (startR + k/2 - 1 < r.length) bMid = r[startR+k/2 -1];
        if(aMid < bMid) {
            return getKth(l,startL+k/2, r, startR, k - k/2);
        } else {
            return getKth(l,startL,r,startR+k/2, k-k/2);
        }
    }
}

public class MedianTwoSortedArrays {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
       
        for(int iteration = 0; iteration < testIteration; iteration++) {
            int[] left = utils.generateRandomArray(testIteration);
            int[] right = utils.generateRandomArray(testIteration);
            Arrays.sort(left);
            Arrays.sort(right);
            double output = sol.findMedianSortedArrays(left, right);
            //print
            utils.printArray(left);
            utils.printArray(right);
            System.out.println(output);
            System.out.println("============");
        }
        
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
