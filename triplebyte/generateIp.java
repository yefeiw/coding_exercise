import java.util.*;
/*
    
    ipv4   [0, 255].[0, 255].[0, 255].[0, 255]
    are valid 
    
    ip1 = 172.24.68.181
    ip2 = 172.24.68.183
    ip3 = 1.255.255.255
    
    example:
    (ip1, ip2)  => ["172.24.68.181", "172.24.68.182"]
    
    
    ip1 < ip2
    ip2 > ip3

*/

/**
 *  lower ip
 *  upper ip
 * 
 *  return [lower, upper)
 * 
 * 
 */
//utils: stringsToNumber, isGreater,isEqual,increment,numbersToString 
class Solution {
 
public List<String> generateAllIpsInRange(String lower, String upper) {
    int[] lowerNumber = stringToNumbers(lower);
    int[] upperNumber = stringToNumbers(upper);
    
    List<String> ret = new ArrayList<String>();
    
    if (!isGreater(upperNumber, lowerNumber)) {
        return new ArrayList<String>();
    }
    int[] iter = lowerNumber;
    while (!isEqual(iter, upperNumber)) {
        ret.add(numbersToString(iter));
        increment(iter);
    }
    return ret;
}
//1.255.255.255
//2.0.0.0
private void increment(int[] nums) {
    for (int i = 3; i >= 0; i--) {
        int cand = nums[i]+1;
        if (cand <= 255) {
            //fit, break
            nums[i] = cand;
            break;
        }
        //assume valid ip, cand can only be 256
        nums[i] = 0;
    }
    
}

private int[] stringToNumbers(String input) {
    String[] buffer = input.split("\\.");
    int[] ret = new int[4];//assume valid IP address;
    for (int i = 0; i < 4; i++) {
        ret[i] = Integer.parseInt(buffer[i]);
    }
    return ret;
}

private String numbersToString(int[] numbers) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 4; i++) {
        sb.append(numbers[i]);
        if (i < 3) {
            sb.append(".");
        }
    }
    return sb.toString();
}

private boolean isGreater(int[] src, int[] cmp) {
    //assume valid ip
    for(int i = 0; i < 4; i++) {
        if (src[i] < cmp[i]) {
            return false;
        }
        if (src[i] > cmp[i]) {
            return true;
        }
    }
    //reaching here means they are equal
    return false;
}

private boolean isEqual(int[] src, int[] cmp) {
    //assume valid ip
    for (int i = 0; i < 4; i++) {
        if (src[i] != cmp[i]) {
            return false;
        }
    }
    return true;
}

public static void main(String[] args) {
    int testIteration = 10;
    Random random = new Random();
    Solution sol = new Solution();
    int testIter =  random.nextInt(testIteration);
    String firstLower = "1.255.255.255";
    String firstUpper = "2.0.0.1";
    List<String> firstOutput = sol.generateAllIpsInRange(firstLower, firstUpper);
    System.out.println(firstOutput);
    /*
    for (int iter = 0; iter < testIter; iter++) {
        StringBuffer lower = new StringBuffer();
        StringBuffer upper = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            lower.append(random.nextInt(256);
            if (i < 3) lower.append(".");
            upper.append(random.nextInt(256);
            if (i < 3) upper.append(".");
        }
        //print input
        System.out.println(lower.toString());
        System.out.println(upper.toString());
        List<String> output = sol.generateAllIpsInRange(lower.toString(), upper.toString());
        //print output
        System.out.println(output);
    }
    */
}
};






