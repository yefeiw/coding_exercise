import java.util.*;
public class TicTacToe {
    //Assumptions:
    //There will only be two players.
    //Player 1 will be designated as 1, and player 2 will be desingated as 2.
    //0 or any other number means the board is not used.

    //variables
    private int[][] grid;
    private HashSet<Integer> set;
    public TicTacToe() {
        //assumption: dimensions always 3x3
        grid = new int[3][3];
        set = new HashSet<Integer>();
    }

    public void add (int x, int y, int player) {
        //row[x][y] = player
        //sanity checks
        if (player != 1 && player != 2) {
            //return for now, TODO:exception handling
            return;
        }
        if (x < 0 || x >=3 || y < 0 || y >= 3) {
            //TODO:exception handling
            return;
        }

        grid[x][y] = player;
        set.add(3*x+y);
    }

    public void print () {
        for (int i = 0; i < 3; i++) {
            System.out.print(printGrid(i,0));
            System.out.print("|");
            System.out.print(printGrid(i,1));
            System.out.print("|");
            System.out.print(printGrid(i,2));
            System.out.println();
        }
    }
    public boolean isFull() {
        return (set.size() == 9);
    }

    public void move() {
        if(isFull()) {
            throw new EmptyStackException();
        }
        for (int i = 0; i < 9; i++) {
            if (!set.contains(i)) {
                int x = i / 3;
                int y = i % 3;
                add(x,y,2);
            }
            break;
        }
    }
    //private
    private String printGrid(int x, int y) {
        if (grid[x][y] == 0) {
            return "-";
        } else if (grid[x][y] == 1) {
            return "X";
        } else if (grid[x][y] == 2) {
            return "O";
        } else {
            //corruption!
            //TODO: exception
            return "";
        }
    }

    public static void main(String[] args) {
        TicTacToe board = new TicTacToe();
        board.add(0,1,1);
        board.print();
        try {
          while(true){  
            board.move();
            board.print();
          }
        } catch (EmptyStackException e) {
            //log.error and what nots
            e.printStackTrace();
        }
    }
    

}
