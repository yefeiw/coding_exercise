//Notes:
//1. Make sure we check for zero somehow.
//2. To check for zero, now that the input will not be zero headed, any
//zero headed number must be zero.
public class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length ==0) {
            return "";
        }
        String[] buffer = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            buffer[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(buffer, (String a, String b) -> ((b+a).compareTo(a+b)));
        //Special case handling if the string is zero
        String ret = String.join("",buffer);
        if (ret.charAt(0) == '0') return "0";
        else return ret;
    }
}
