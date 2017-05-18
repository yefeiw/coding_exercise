# Report: Maze III

Yefei Wang

## [Problem Statement](https://leetcode.com/problems/the-maze-iii/#/description):
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
Show Tags
Show Similar Problems

## Assumptions:
* No need to validate input. There is guarantee that there are two blank spots. Thus, the ball position is valid, hole position is valid, and also the maze cannot be empty.
* The ball will not stop unless hitting the wall -> special handling for advancement.
* Lexographically smaller answer first -> either sort or make sure Lexographically smaller candidates gets to the queue first.

## Key Problems in this problem set:
* How to advance the ball?
	* Hit wall? go through all possibilities
	* Not hit? follow preserved direction (meaning, need to preserve direction!)

## Solution:

### strategy:
* BFS
	* Special handling of data advancement -> deterring DP as an easy and readable solution.
	* Ask for (one of the) shortest paths -> BFS seems more intuitive.
		*DFS will resolve this problem as long as BFS will. This is a personal preference

### Would it work as-is? Would it have any obvious sub-optimizations?
It will functionally work and there is no optimizations that will change the asymptotical complexity.
* It will functionally work because it's a search algorithm solving a search problem.
* Since it requires reaching the end point, in the worst case is not reachable exhausting all possible paths, there is no asymptotical optimization over the typical BFS algorithm.

## Proposed solution
* Binary First Search:
 	* Pay special attention to the starting point. We will only put the viable advancements of starting points as the starting elements of the search queue.
 	* Each time when we are advancing, we will only explore possibilities when the ball hits the wall or boundaries. We do it by a trial and error strategy.
 	* Each time we explore possibilities, including the starting points and when we hit boundaries, we will always start from those whose lexographical order is smaller, hence in the end we don't have to find all possible solutions and then search for the shortest and smallest one. The first satisfying solution will be the final solution.

## Data Structure Deployed:
* queue as always needed in BFS.
* 3D arrays storing the records of visited nodes. It seems that the nodes needs to be hashed if both location and direction is the same. This is something experimentable.
* Custom node class named "Element", because we need to preserve the move records and last direction in each step of the BFS search.


## Code

[click here](https://github.com/yefeiw/coding_exercise/blob/master/week8/src/MazeIII.java)


### Complexity Analysis:

* Time O(n*m) n is maze.length, m is maze[0].length.
 	* O(n*m*numdirections) in our BFS solution, as for each direction we are exhaustively searching each node for a solution.
* Space O(n*m)
 	* Visited Structure and BFS queue is the bottleneck here. Both require O(n*m*numDirections) space to store internal states.

## Other solutions:

* [A typical DFS solution](https://discuss.leetcode.com/topic/77074/clear-java-accepted-dfs-solution-with-explanation)
	* It is very readable. Note that for DFS both complexities are exactly the same.
## Possible Follow-ups:
	* Problem-wise:
		* What if there are one-directional passages? (balls can pass in some direction but not others, forcing you to rebounce sometimes)
		* What if there are two balls? Each ball's movement depends on the location or direction of the other ball in certain situations
	* Solution-wise:
		* How can we parallelize this problem? Is there any foreseeable benefits of parallelization?
		* Suppose in embedded stuff you don't have enough memory, does that change your choice of best solution to go with? (usually think DFS first)
		* A geeky interviewer wants you to do it in DP, is it desirable/do-able? If so, solution? If not, reason?



