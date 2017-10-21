# Week 7
This week has generally been about graph-related problems.

## HW: Remove Parentheses
### idea
BFS
### Notes
* Don't be afraid about this problem. Although it's hard, BFS alone will resolve it.
* Be careful on removing characters. substring will work more reliably than sb.

### Output
Accepted on second shot. sb has been removing a lot of characters without restoring them.

## HW: Restore IP address
### tags
BFS
### Notes
* IP addresses will take 15 chars max, 12 for numbers and 3 for dots. Don't get confused.
### Output
Accepted on second shot.

## HW: 01 Matrix
### tags
BFS, minimal-distance heuristic
### Notes
* Here having a visited hashset will be hard. I am interested in knowing how I should do it.
### Output
Accepted on second shot, HashMap on visited nodes will not work as is.


## HW: Smallest Good base
### tags
Binary Search, Math
### Notes
* Since the number is always <= n-1. We can see if we can try the answers out in linear time.
枚举法

记k的最高次幂为m，从上界 int(log(n)) 向下界 1 递减枚举m

问题转化为计算1 + k + k^2 + ... + k^m = n的正整数解

由n > k^m得： k < n ** 1/m

由n < (k + 1)^m得： k > n ** 1/m - 1，此处使用了二项式定理

因此k可能的解为：int(n ** 1/m)

最后验证1 + k + k^2 + ... + k^m 是否等于 n
### Tests

### Output
Accepted on first shot.

## HW: Skyline Problem
### tags
Heap, Merge Sort.
### Notes
* [Detailed Report Here](https://github.com/yefeiw/coding_exercise/blob/master/week7/Skyline.md)

### Output
Accepted on first shot

## HW: Contains Duplicate III
### tags
Binary Seach, Bucket Sort.
### Notes:
* Binary Search: Setup a treeset, and always search whether the closest neighbor has acceptable subscript.
* Bucket Sort: Setup buckets such that bucket size = t. Search whether this bucket, next larger bucket and next smaller bucket has acceptable subscript.
 * Note that we don't need to worry about >1 elements in a bucket. Because if there is such a scenario, by definition there is already a duplicate in that bucket. We should have returned earlier.

### Output
Accepted on first shot

## HW: PalinDromePair
### tags
HashMap
### Notes:
* The key properties to solving this problem is that, a palindrome of two strings must contains of a palindrome subset and two complimentary strings.
	* Therefore, we can divide the string by two parts, if one is palindrome, we search for reverse of another.
	* Note that the only possibility where duplicates may be found is whole string as palindrome. 
		* Hence, for first search, we need to check if the key found is self. for second search, we need to check both if the key found is self and whether the 
### Output
Accepted on first shot.

## HW: Maximum XOR of two numbers in an Array.
### tags
Math
### Notes:
* Key properties: a^b = c === a^c = b, therefore instead of trying out the numbers, we try if a solution could be constructed, decrementally.
	* Also we can't enumerate everything in 2^32. Therefore we do it in two steps:
		1. setup a decrementing mask only focusing on the higher bits.
		2. Try if a lower bits could be XOR'ed based on the higher bits.

### Output
Accepted on first shot.


