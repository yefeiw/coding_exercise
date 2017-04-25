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



