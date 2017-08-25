class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //sanity check
        List<List<Integer>> ret =  new ArrayList<>();
        if (nums == null || nums.length == 0) {
        	return ret;
        }
        //sort the arrays so that the duplicates will get together
        Arrays.sort(nums);
        List<Integer> stack = new ArrayList<>();
        helper(nums,0,stack,ret);
        return ret;
    }
    private void helper(int[] nums, int start, List<Integer> stack, List<List<Integer>> result) {
    	//bailout condition    	    		
    	result.add(new ArrayList<Integer>(stack));
    	if (start >=nums.length) {
    		return;
    	}
    	for (int i = start; i < nums.length;) {
    		int cur = nums[i];
    		stack.add(nums[i]);
    		helper(nums,i+1,stack,result);
    		stack.remove(stack.size() -1);
    		//simple de-dup: after starting the search, advance the pointer so that all dups are omitted.
    		while(i < nums.length && nums[i] == cur) i++;
    	}

    }
}