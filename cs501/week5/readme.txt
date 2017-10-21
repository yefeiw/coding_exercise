####################  Week4 Notes #################
####  src: all source codes
####  tst: all testing scripts
###################################################

HW1: Game of Life
2D Array + Bit Operation.
Note: There are a couple of silly errors I have made in the program. Like mistaking i for j and so on.
TestCase:
[[0,0,0,0],[0,1,1,0],[0,1,1,0],[0,0,0,0]]
+ Random Manual Testing
Output:
Accepted after two retries, all silly mistakes.

HW2: Design Snake Game
Design
Note: thoughts:
1. food is only placed once -> queue for food
2. snake will never bite into itself -> hashmap for snake position.
3. snake will only update length and tail -> deque for snake position.
TestCase:

Too hard to manually test, relied on leetcode.
Output:
Accepted on second shot, redundant addition of head when food is eaten.

HW3: Logger Rate Limiter
Design
Note: 
1.O(1) access to any element ->hash table
2. No need to think about scalability -> no circular buffer
TestCase:
Relied on Leetcode
Output:
Accepted on second shot. Used the first test case to tell whether < 10 or <=10 is required.

HW4: LFU Cache
Design
Note:
1. O(1) addition, access and removal -> Hash Table.
2. O(1) access to any frequency -> array or Hash table for each frequency
3. O(1) increase and decrease of frequency, to tail + O(1) removal of head of lowest frequency -> LinkedHashMap inside the frequency list
4. No upper limit on max Frequency -> HashMap for frequency List.
TestCase:
1. Sanity testing manually.
2. Limited random testing manually.
3. Leetcode
Output:
Accepted on second shot, forgot to maintain frequency of cached node in the first shot.

HW5: Convert BST to Greater Tree
BST, recursion
Note:
1. For simplicity, it is better to store a static current sum in the class.
2. Each time, first visit right, add sum there, and then visit left.
TestCase:
Too hard to manually test, relied on leetcode.
Output:
Accepted on second shot, did not think through the whole data structure at first.

HW6: Palindrome Permutation
Backtrack + Math
Note:
1. My code is brutal backtrack, it will be correct but TLE.
2. I cannot understand the solution easily. I am waiting for some more inputs.
TestCase:
Leetcode
Output:
TLE

HW7: Group Anagrams
HashMap
TestCase:
Leetcode
Output:
Accepted on first shot.

HW8: RemoveDuplicateLetters
Stack
Note:
1. We need to build a stack, that is very hard to think of.
2. For each element in the stack, we check the following, if stack is empty, insert, if element found in the stack, continue and decrement ref. if element is smaller than stack.top(), pop until the stack.top() is the last reference, or element is now greater than stack.top()
TestCase:
1. Random test locally
2. Leetcode
Output:
Accepted on second shot. Forgot to decrement count if element in stack in the first shot.

HW9: Sort Tranformed Array
Two Pointers + Math
Notes:
1. My intuitive thoughts are two naive. We don't even need to calculate -b/2a.
2. a=0 will apply on both sides, just randomly pick one will do, instead of having a seperate case.
TestCase:
1. Random test locally
2. Leetcode.
Output: Accepted on first shot.

HW10: Largest Divisible Subset
DP
TestCase:1
1. Random test locally (occasional divby0 but ignorable)
2. Leetcode.
Output: Accepted on first shot.


