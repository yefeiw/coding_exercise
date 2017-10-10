/*
 * [353] Design Snake Game
 *
 * https://leetcode.com/problems/design-snake-game
 *
 * algorithms
 * Medium (26.68%)
 * Total Accepted:    9.3K
 * Total Submissions: 34.7K
 * Testcase Example:  '["SnakeGame","move","move","move","move","move","move"]\n[[3,2,[[1,2],[0,1]]],["R"],["D"],["R"],["U"],["L"],["U"]]'
 *
 * Design a Snake game that is played on a device with screen size = width x
 * height. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length =
 * 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake
 * eats the food, its length and the game's score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food
 * will not appear until the first food was eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not
 * appear on a block occupied by the snake.
 *
 *
 * Example:
 *
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after
 * that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with
 * border)
 *
 *
 *
 *
 * Credits:Special thanks to @elmirap for adding this problem and creating all
 * test cases.
 */
class SnakeGame {
    //data structures
    Class Snake {
      Set<Integer> bodySet;
      List<Integer> bodyList;
      public Snake() {
        this.bodySet =  new HashSet<>();
        this.bodyList = new LinkedList<>();
      }
    }
    Class Util {
      int width;
      int height;
      public Util(int width, int height) {
        this.width  = width;
        this.height = height;
      }
      public int positionToHash(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
          return -1;
        }
        return x * height + y;
      }

      public int[] hashToPosition(int hash) {
        int x = hash / height;
        int y = hash % height;
        return new int[] {x,y};
      }
    }

    Snake snake;
    Util util;
    List<Integer[]> food;
    Map<String, Integer> directions;

    private int[] dx = {0,-1,1,0};
    private int[] dy = {1,0,0,-1};


    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        util = new Util(width, height);
        snake = new Snake();
        for (int[] foodLoc : food) {
          this.food.add(foodLoc);
        }
        directions.put("U",0);
        directions.put("L",1);
        directions.put("R",2);
        directions.put("D",3);

    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
        @return The game's score after the move. Return -1 if game over.
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (!directions.containsKey(direction)) {
          System.out.println("invalid direction " + direction);
          return -1;
        }
        int delta = directions.get(direction);
        int[] head = util.hashToPosition(snake.bodyList.get(0));
        int newX = head[0] + dx[delta];
        int newY = head[1] + dy[delta];
        if (isOutOfBound(newX, newY)) {
          return -1;
        }
        if (!isFruit(newX,newY)) {
          int[] tailX = util.hashToPosition(snake.bodyList.getLast());
          int tailHash = snake.bodyList.getLast();

        }

    }

    private boolean isOutOfBound(int x, int y) {

    }

}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
