# Week 4
Key points of this week has been stack operations.

## HW: Brick Wall
### idea
HashMap
### Notes
In order not to count the edge of the wall, just don't include the last element
### Tests
Basic sanity tests on testInput class.
Complete tests on leetcode.
### Output
Accepted on first shot.

## HW: Maximum Size Subarray Sum Equals k
### tags
HashMap, prefix sum
### Tests
Limited functional tests on testInput
Complete tests on leetcode.
### Output
Accepted on first shot.

## HW: Add Two Numbers II
### tags
Linked List, Stack
### Notes
* The idea is to do the addition from front to back, and push all digits to a stack.
* Pay special attention to head handling when there is only one digit
### Tests
Limited functional tests on testInput
Complete test on leetcode
### Output
Accepted on second shot, first sub failed 5 + 5


## HW: Array Partition I
### tags
Bucket Sort
### Notes
* There is a strong implication to do bucket sorting as input is bounded
### Tests
Limited functional tests on testInput
Complete test on leetcode.
### Output
Accepted on first shot.

## HW: First Missing Positive
### tags
In-place select
### Notes
* The idea is to put i in nums[i] if applicable.
* Iterative swapping is okay since once the number is ready, we only have n-1 numbers to worry about. It is still O(n) overall

### Tests
Limited functional tests on testInput
Complete test on leetcode
### Output
Accepted on first shot

## HW: Longest Word in Dictionary Through Deleting
### tags
Two pointers
### Notes:
* There might be better ways, but semi-brutal-force will AC.
* The trick here is in verification, two pointers will be useful
### Tests
Limited functional tests on testInput
Complete test on leetcode.
### Output
Accepted on first shot

## HW: Max Consecutive Ones II
### tags
Two Pointers, queue
### Notes:
* The only trick here is when to update left and when to update right
### Tests
Limited functional tests on testInput
Complete test on leetcode.
### Output
Accepted on first shot.

## HW: Remove K digits
### tage
Stack
### Notes:
* Typical stack problem. Determine how to construct the higher->lower sequence is the key here.
### Tests:
Limited functional tests on testInput
Complete test on leetcode.
### Output
Accepted on first shot.


