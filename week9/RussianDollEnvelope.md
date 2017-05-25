# Report: Russian Doll Envelope

Yefei Wang

## [Problem Statement](https://leetcode.com/problems/russian-doll-envelopes/#/description):
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

## Assumptions:
* This problem is straight forward. Meaning, there is no assumption without which you won't solve the problem.

## Key Problems:
* How to make sure 2D enclosure (larger in both length and height)

## Educated Brute-Force Solution 
(It's not good to think of a brute-force for the sake of a brute-force)

### Strategy:
* Search
	* Sort the array by one dimension.
	* For each item in the sorted list, search how many envelopes whose one dimension is less than the current one, the other dimension is also less than the current one.
	* Count and update the maximum according to the count.

### Would it work as-is? Would it have any obvious sub-optimizations?
It will functionally work and there is a strong indication that DP will be a faster algorithm.
* It will functionally work because
	* For each element, there is one dimension already sorted. Therefore, as long as we correctly searched the other direction, we have guarantee that the answer will be correct.
* DP/Memorization will be easily think of because:
	* For each element, we are anyways searching for all lesser elements. A brief walkthrough will easily identify many redundant searches, and therefore we can easily think of DP/memorization to do those searches only one. 

## Proposed Solution
* DP
	* Sort the array by one dimension.
	* For each item in the sorted list, search for the memorized number of enclose envelopes an update the current memorization.
	* Update the maximum in each item of the search and return the updated max in the end.

### Data Structure Deployed:
* 1D array to store the max number of enclose envelopes 


### Complexity:

* Time O(n*n) n is the number of envelopes
 	* O(n*lg(n)) to sort.
 	* O(n*n) to search for the current maximum number of envelopes. with O(1) comparison and O(1) update each time.
* Space O(n)
 	* 1D Array to store the temp results.

## Proposed Better Solution:

After searching for answers, there is a less-intuitive but more efficient solution using Binary search to optimize the DP process.

### Pre-requisite 
(Frankly I'll be amazed if you can come up with this one without doing the pre-requisite):
[Longest Increating Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/#/description)
[Recommended answer to Pre-req](https://segmentfault.com/a/1190000003819886)
Note: This answer is in Mandarin.

### Details:
* Sort the array so that
	* if length is not equal, sort by increasing length
	* if length is equal, sort by decreasing height
* For each item in the sorted list. search the proper position of addition using binary search and then update accordingly.
* After the element has been found, use DP as in the last solution to update and return the maximum.

### Complexity:
* Time O(n*lg(n)) n is the number of envelopes
	* O(n*lg(n)) to sort
	* O(n)* O(lg(n)) to update the dp array. The details are almost the same as the last problem. But here instead of linearly search for all previous elements, we used binary search instead.
* Space O(n)
	* 1D array to store the temporary results.

## Code

Code of both solutions is in the same file below. Now that we have two solutions, we can easily come up with a BET.

[click here](https://github.com/yefeiw/coding_exercise/blob/master/week9/homework/RussianDollEnvelop.java)


## Possible Follow-ups:
* Solution-wise:
	* How/Whether this problem can be parallelized? Suppose you have n computers, where n is comparable to the number of envelopes, will there be any time optimizations asymptotically?



