import java.util.*;

public class KthLargest {
	public int kthLargestElement(int k, int[] nums) {
		if (!isValidInput(k,nums)) {
			return 0;
		}
		return helper(nums,0,nums.length -1, nums.length - k+1);
	}
	public boolean isValidInput(int k, int[] nums) {
		if (k <= 0) return false;
		if (nums == null || 0 == nums.length) {
			return false;
		}
		return true;
	}
	public int helper(int[] nums, int start, int end, int k) {
		//find the kth largest element in nums from start to end
		if(start == end) {
			//found, return
			return nums[start];
		}
		//the idea is pretty much like quick sort.
		int position = partition(nums,start,end);
		if (position + 1 == k) {
			//special case, we have already found the K-th largest position
			return nums[position];
		} else if (position +1 < k) {
			return helper(nums,position + 1, end, k);
		} else {
			//position + 1 < k, search the lower half
			return helper(nums,start, position -1, k);
		}
	}
	public int partition(int[] nums, int start, int end) {
		//partition exactly as quicksort
		int left = start;
		int right = end;
		//usually we choose left, for lower probability of n2 solution we can also randomize.
		int pivot = nums[left];

		  while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
		return left;
	}
} 