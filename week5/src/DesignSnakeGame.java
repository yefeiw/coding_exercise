import java.util.*;
class Position {
    int x;
    int y;
    public Position() {
        x = 0;
        y = 0;
    }
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Position others) {
        if (null == others) {
            return false;
        }
        return(this.x == others.x && this.y == others.y);
    }

}
class SnakeGame {
    //state variables for the map
    private int width;
    private int height;
    //state variable for the food;
    private final Deque<Position> food = new ArrayDeque<Position>();
    //state variable of the snake;
    private final Deque<Position> snake =  new ArrayDeque<Position>();
    private final HashSet<Integer> body = new HashSet<Integer>();

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        for (int[] f : food) {
            int x = f[0];
            int y = f[1];
            this.food.add(new Position(x,y));
        }
        snake.add(new Position(0,0));
        body.add(0);
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        //find the current head position of the snake
        Position head = snake.peek();
        //find the current food position;
        Position nextFood = food.peek();
        Position newHead = moveHead(head, direction);
        //If the new position is valid
        if(!isValid(newHead)) {
            //moved out of bounds, 
            return -1;
        }

        //If the new position contains food.
        if (newHead.equals(nextFood)) {
            food.pollFirst();
        } else {
            Position tail = snake.pollLast();
            body.remove(tail.x * width + tail.y);
        }
        //Whether the snake bites into itself.
        if (body.contains(newHead.x*width + newHead.y)) {
            return -1;
        } else {
            snake.addFirst(newHead);
            body.add(newHead.x * width + newHead.y);
        }
        //reaching here means that the snake is still alive.
        return snake.size()-1;

    }
////////////////////////////////////////util methods/////////////////////////////////////////

////////////////////////////////////////private methods///////////////////////////////////////
    boolean isValid(Position p) {
        if (p == null) {
            return false;
        }
        if (p.x < 0 || p.x >= height) return false;
        if (p.y < 0 || p.y >= width) return false;
        return true;
    }
    Position moveHead(Position head, String direction) {
        switch (direction) {
            case "U" :
            return new Position(head.x-1, head.y);
            case "L" :
            return new Position (head.x, head.y-1);
            case "R" :
            return new Position(head.x, head.y+1);
            case "D" :
            return new Position(head.x+1, head.y);
            default:
            return new Position();
        }
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

public class DesignSnakeGame {
    public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    int width = testInput.generateRandomInt()+1;
    int height = testInput.generateRandomInt()+1;
    int [][]food = testInput.generateRandomMatrix(testIteration, Math.min(width,height));
    SnakeGame game = new SnakeGame(width, height, food);
    String[] move = {"U","L","R","D"};
    for (int i = 0; i < testIteration; i++) {
       int m = Math.abs(testInput.generateRandomInt()) % 4;
       String curMove = move[m];
       int ret = game.move(curMove);
       System.out.println("CurMove is " +curMove + " and the score is " + ret);
    }
    System.out.println("Test executed without crashes, please manually verify input");
 }
}