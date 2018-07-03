/*
 * [302] Smallest Rectangle Enclosing Black Pixels
 *
 * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/description/
 *
 * algorithms
 * Hard (47.01%)
 * Total Accepted:    18.2K
 * Total Submissions: 38.7K
 * Testcase Example:  '[["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]]\n0\n2'
 *
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as
 * a black pixel. The black pixels are connected, i.e., there is only one black
 * region. Pixels are connected horizontally and vertically. Given the location
 * (x, y) of one of the black pixels, return the area of the smallest
 * (axis-aligned) rectangle that encloses all black pixels.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠ "0010",
 * ⁠ "0110",
 * ⁠ "0100"
 * ]
 * and x = 0, y = 2
 * 
 * Output: 6
 * 
 * 
 */

 /**
  * BUGS:
  1. essed up the searching criteria, in binary search, if we are to find a certain index with or without bug, the best thing we can do is to check for the output and then tell.
  2. When searching, we need to tell dimensions clearly.
  */
class Solution {
    private int x;
    private int y;
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return -1;
        }
        this.x = x;
        this.y = y;
        int minx = binarySearchSmall(image, 0);
        if (minx == -1) {
            return 0;
        }
        int maxx = binarySearchLarge(image, 0);
        int miny = binarySearchSmall(image, 1);
        int maxy = binarySearchLarge(image, 1);
        //System.out.printf("minx %d, maxx %d, miny %d, maxy %d\n",
        //minx, maxx, miny,maxy);
        return (maxx - minx + 1) * (maxy - miny + 1);
    }

    private int binarySearchSmall(char[][] image, int dimension) {
        int start = 0;
        int end = (dimension == 0) ? x: y;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (containsOne(image, mid, dimension)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (containsOne(image, start, dimension)) {
            return start;
        } else if (containsOne(image, end, dimension)) {
            return end;
        } else {
            return -1;
        }
    }

    private int binarySearchLarge(char[][] image, int dimension) {
        int start = (dimension == 0) ? x: y;
        int end = (dimension == 0) ? image.length-1 : image[0].length-1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (containsOne(image, mid, dimension)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (containsOne(image, end, dimension)) {
            return end;
        } else if (containsOne(image, start, dimension)) {
            return start;
        } else {
            return -1;
        }
    }

    private boolean containsOne(char[][] image, int index, int dimension) {
        //System.out.println("index "+index + "dimension "+ dimension);
        int limit = (dimension == 0) ? image[0].length : image.length;
        for (int i = 0; i < limit; i++) {
            int cand = (dimension == 0) ? image[index][i] : image[i][index];
            if (cand == '1') {
                return true;
            }
        }
        return false;
    }

}
