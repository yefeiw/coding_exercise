####################  Week3 Notes #################
HW1: Sort Frequency
Testcases:
"tree"
""
"1b2b3n3m4n5b6b6hj6j"
Output:
Accepted on first shot.

HW2: minHeap with Arrays
Note: idea is borrowed from coursera Algorithms class and bitTiger.
TestCases:
Ramdon test
Output:
Passing random test after many bugs. 
Note since this is a minHeap with both positive and negative numbers, memset with zero will introduce bugs.


HW3: Number of Islands I
Very basic BFS
Note:  Need to tell if the cell content is 1/0 or '1'/'0'. Need any test case to figure that out.
TestCases:
Randomized checking, see source code.
Output:
Accepted after 1 modifications.

HW4: Populating Next Right Pointers in Each Node 
Very basic level-order traversal
Note: forget to add null pointer checks when pushing the nodes to the queue. Caught on any basic test cases.
Output:
Accepcted after 1 modification.

HW5: Populatingg Next Right Pointers in Each Node II
Same as HW4
Output:
Direct copy of code after judgement that it will also work. Accepted on first shot.

HW6: Binary Tree Zigzag Level Order Traversal
Level Order Traversal + List Operations.
TestCases:
[3,9,20,null,null,15,7]
[1,2]
[1,2,3,4,5,6,7,8,9]
Output:
Accepted on first shot.
Note:
DFS is also easy as is here:
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/#/solutions


HW7: Sort List
Merge sort on list
Note: Spent much debugging time because I forgot to update left and right when dividing to sub problems.
Testcases:
[]
[1]
[2,1]
[3,2,4,1,5,6,9,7]
[3,2,4,1,5]
I have also done random testing to ensure correctness.
Output:
Accepted on first shot (after local testing)

HW8: Largest Number
Custom Sorting: Intuitively compare the string sum, just as they were in the final solution.
Note: Bogged down for quite a while for removing the header nodes.
Testcases:
[1]
[1,2,3,4]
[90,18,72,36,54]
[0,0,1,2]
[0,0]
I have also done random testing to ensure correctness.
Output:
Accepted after many trials, mainly testing the header zero removal.

HW9: Course Schedule II
Basic topological sorting
Note: There are many advanced usages about Java in the problem that I need to rememeber.
TestCases:
2
[1,0]
4, [[1,0],[2,0],[3,1],[3,2]]
Output:
Accepted in the first shot

HW10: Maximum Gap
Bucket sort
Notes:  Need special attention on the size of the buckets.
TestCases:
[1,2,1,1,1]
[1]
[]
[1,2,4,5,3,7,2,8,0,2]
[0,9999999]