import java.util.*;

class Solution {
    Random random = new Random();
    //function: getSamples
    //input:  int[] input --- input array
    //input:  int[] weight --- weight of each element in the array in integer form
    //ouput:  int output --- which number in the array to return  (not the index)
    public int getSample(int[] input, int[] weight) {
    	//input validation
    	if (input == null || weight == null || input.length == 0 || input.length != weight.length) {
    		//throw exception
    		return Integer.MAX_VALUE;
    	}
    	int sum = 0;
    	//calculate sum of weights
    	for(int w : weight) {
    		sum += w;
    	}
    	//pick a random weight and check which number caused it to return
    	int target = random.nextInt(sum);
    	for(int i = 0; i < weight.length; i++) {
    		target -= weight[i];
    		if (target < 0) {
    			return input[i];
    		}
    	}
    	//reaching here means the last element
    	return input[input.length -1];
    }
}

public class WeightedRandomSampling {
   public static void main (String args[]) {
    // int testIteration = 10000;
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for(int i = 0; i < testIteration; i++) {
        int[] input =  testInput.generateRandomArray(testIteration);
        int[] weight = new int[input.length];
        for(int w = 0; w < weight.length; w++) {
        	weight[w] = testInput.generateRandomInt(testIteration);
        }
        //print input
        testInput.printArray(input);
        testInput.printArray(weight);
        for(int j = 0; j < weight.length; j++) {
        	int cand = sol.getSample(input,weight);
        	System.out.println("output is "+ cand);
        }
    }
    
    System.out.println("Test executed with no crashes, please verify on LeetCode");
}
}
