public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret =  new ArrayList<>();
        backtrackHelper(nums, ret, 0, new ArrayList<>());
        return ret;
    }

    private void backtrackHelper(int[] nums, List<List<Integer>> ret, int start, ArrayList<Integer> stack) {
    	ret.add(new ArrayList<Integer>(stack));
    	if (start >= nums.length) {
    		return;
    	}
    	for(int j = start; j < nums.length; j++) {
    		stack.add(nums[j]);
    		backtrackHelper(nums,ret, j+1, stack);
    		stack.remove(stack.size() -1);
    	}
    }
}