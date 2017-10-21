import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
//



class Solution {
   class Element {
        int x;
        int y;
        int direction;
        String moves;
        public Element(int x, int y, int direction, String moves) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.moves = moves;
        }
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (null == maze || null == ball || null == hole) {
            return "impossible";
        }
        String[] directions = {"d","l","r","u"};
        int[] deltay = {0,-1,1,0};
        int[] deltax = {1,0,0,-1};
        Deque<Element> queue = new ArrayDeque<Element>();
        //element will not work. Need to set directions
        boolean[][][] visited = new boolean[maze.length][maze[0].length][4];
        for(int i = 0;i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                for (int d =0; d < 4; d++) {
                    visited[i][j][d] = false;
                }
            }
        }

        int startx = ball[0];
        int starty = ball[1];
        int endx = hole[0];
        int endy = hole[1];
        for (int i = 0; i < 4; i++) {
            //add starting points to all directions
            int x = startx + deltax[i];
            int y = starty + deltay[i];
            Element start = new Element(x, y, i, directions[i]);
            if(isValid(start, maze) && isVacant(start,visited)) {
                queue.add(start);
                visited[x][y][i] = true;
            }
            
        }

        while (!queue.isEmpty()) {
            Element head = queue.remove();
            //System.out.println(head.moves);
            //System.out.println(head.x+","+head.y);
            visited[head.x][head.y][head.direction] = true;
            if(head.x == endx && head.y == endy) {
                return head.moves;
            }
            //Idea: trial and error
            Element next = new Element(head.x + deltax[head.direction], head.y + deltay[head.direction], head.direction, head.moves);
            if (isValid(next,maze)) {
                if (isVacant(next, visited)) {
                    //System.out.println("continuing " + next.x+","+next.y);
                    queue.add(next);
                    visited[next.x][next.y][next.direction] = true;

                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if(i  == head.direction) continue;
                    Element cand = new Element(head.x + deltax[i], head.y + deltay[i], i, head.moves + directions[i]);
                    //System.out.println("trying " + cand.x+","+cand.y);

                    if (isValid(cand,maze) && isVacant(cand, visited)) {
                        //System.out.println("adding " + cand.x+","+cand.y);
                        queue.add(cand);
                        visited[cand.x][cand.y][cand.direction] = true;

                    }
                }
            }
        }
        //reaching here means we have exhausted the searches and nothing has been found.
        return "impossible";
    }
    boolean isValid(Element e, int[][] maze) {
        int x = e.x;
        int y = e.y;
        if (x < 0 || x >= maze.length) return false;
        if (y < 0 || y >= maze[0].length) return false;
        if (maze[x][y] != 0) return false;
        return true;
    }
    boolean isVacant(Element e, boolean[][][] visited) {
        int x = e.x;
        int y = e.y;
        int direction = e.direction;
        return (visited[x][y][direction] != true);
    }
}

public class MazeIII {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        //Random testing
        for (int r = 0; r < testIteration; r++) {
            int[][] input = utils.generateRandomMatrix(testIteration,2);
            int[] ball = {utils.generateRandomInt(testIteration),utils.generateRandomInt(testIteration)};
            int[] hole = {utils.generateRandomInt(testIteration),utils.generateRandomInt(testIteration)};
            utils.printMatrix(input);
            String output =  sol.findShortestWay(input,ball,hole);
            //print
            System.out.println(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
