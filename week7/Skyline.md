# Report: Skyline Problem

Yefei Wang

## [Problem Statement](https://leetcode.com/problems/the-skyline-problem/#/description):
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

## Assumptions:
There are no key assumptions in this problem which you cannot resolve the problem without understanding such hints/assumptions. In other words, the description of this problem is straight-forward.

## Key Problems in this problem set:
1. How to track the skyline?
 	1. The skyline will always be the maximum height amongst all buildings in a certain x position.

## Intuitional Solution (a.k.a. brute-force):

### strategy:
Scan line: scan across the x-axis and find the maximum height in each x-axis. 
Optimize where possible.

## Would it work ideally? Would it work as-is?
It will functionally work but there are many apparent sub-optimizations.
* It will functionally work because once you have identified the maximum height at each x-position, you will naturally get the skyline.
* sub-optimizations:
 * need to search for the maximum height at each x-axis, O(n) per search where n is the number of buildings.
 * need to incrementally scan till the maximum x position. O(n) to known that position, O(x) to search where x is the maximum position

### Proposed solution (where would optimization be possible)?
* Optimize maximum search:
	* Use max heap, O(1) to search for maximum, O(lg(n)) to insert or remove an element.
* Optimize scan:
	* skyline will only change at the boundaries, O(n) to find the max, but also O(2n) = O(n) to complete the search.

### Data Structure Deployed:
* max heap as PriorityQueue, opitimized searching for max height
* List as ArrayList, optimized management of edge points.


### Code

[click here](https://github.com/yefeiw/coding_exercise/blob/master/week7/src/SkyLine.java)


### Complexity Analysis:

* Time O(nlgn) n is the number of buildings.
 * O(nlgn) sort of all edges and then O(n) search in the boundaries, each involving insert/removal of elements of O(lgn) and find max involving O(1)

* Space O(n)
 * Bottleneck is to store the edges of each buildings in a sorted order. Intuitively that is O(n)

### Other solutions:

* Hint: The proposed complexity is O(nlgn). Therefore, all binary-based algorithms are worth a try at this problem.
 * They are likely to be faster in real time since they do not require a sort before binary resolution. But it will be a little harder to read.
[One such very nice example is here](https://discuss.leetcode.com/topic/56750/java-divide-and-conquer-solution-beats-96/2)

### Possible Follow-ups:
This problem is so specific, I am not sure how we can change the description to form a meaningful follow-up. From solution-wise:
* Supposed you have K computers, where K is comparable to the number of buildings. How would you then derive a faster solution?


