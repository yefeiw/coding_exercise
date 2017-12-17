class Solution {
    public int[] twoSum(int[] numbers, int target) {
            int start = 0;
            int end = numbers.length-1;
            while(start < end) {
                int cand = numbers[start] + numbers[end];
                if (cand > target) {
                    end--;
                } else if (cand < target) {
                    start++;
                } else {
                    int[] ret = new int[2];
                    ret[0] = start+1;
                    ret[1] = end+1;
                    return ret;
                }
            }
            return new int[2];
    }
}
