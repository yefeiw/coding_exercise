
//*******************************************************************
// Welcome to CompileJava!
// If you experience any issues, please contact us ('More Info')  -->
// Also, sorry that the "Paste" feature no longer works! GitHub broke
// this (so we'll switch to a new provider): https://blog.github.com\
// /2018-02-18-deprecation-notice-removing-anonymous-gist-creation/
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class
import java.util.*;
import static java.util.stream.Collectors.*;

// one class needs to have a main() method
public class HelloWorld {
    // arguments are passed using the text field below this editor
    public static void main(String[] args) {
        // int[] nums = {1,2,3,4,5,6,6,8,9};
        int[] nums = null;
        int k = 6;
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.getTopk(nums, k)));
    }
}

class Solution {
    public int[] getTopk(int[] nums, int k) {
        // remove duplicates
        int len = deduplicate(nums);
        // find top k largest using quick-select
        int[] ret = new int[k];
        find(nums, 0, len - 1, k, ret, 0);
        return ret;
    }

    private int deduplicate(int[] nums) {
        Set<Integer> used = new HashSet<>();
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            if (!used.contains(nums[i])) {
                used.add(nums[i]);
                i++;
            } else {
                swap(nums, i, j);
                j--;
            }
        }
        return i;
    }

    private void find(int[] nums, int s, int e, int k, int[] ret, int pos) {
        if (s > e || pos >= ret.length)
            return;
        int pivot = nums[s];
        int left = s;
        int right = e;
        int i = s;
        while (i <= right) {
            if (nums[i] <= pivot) {
                swap(nums, i, left);
                left++;
                i++;
            } else {
                swap(nums, i, right);
                right--;
            }
        }
        swap(nums, s, right);
        int len = e - right + 1;
        if (len <= k) {
            // array copy
            System.arraycopy(nums, right, ret, pos, len);
            find(nums, s, right - 1, k - len, ret, pos + len);
        } else {
            find(nums, right + 1, e, k, ret, pos);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}