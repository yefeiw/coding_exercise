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


